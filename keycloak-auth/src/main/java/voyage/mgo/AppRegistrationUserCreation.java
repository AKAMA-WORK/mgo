    package voyage.mgo;

    import com.google.i18n.phonenumbers.NumberParseException;
    import com.google.i18n.phonenumbers.PhoneNumberUtil;
    import com.google.i18n.phonenumbers.Phonenumber;
    import org.keycloak.Config;
    import org.keycloak.authentication.FormAction;
    import org.keycloak.authentication.FormActionFactory;
    import org.keycloak.authentication.FormContext;
    import org.keycloak.authentication.ValidationContext;
    import org.keycloak.connections.jpa.JpaConnectionProvider;
    import org.keycloak.events.Details;
    import org.keycloak.events.Errors;
    import org.keycloak.events.EventType;
    import org.keycloak.forms.login.LoginFormsProvider;
    import org.keycloak.models.*;
    import org.keycloak.models.utils.FormMessage;
    import org.keycloak.protocol.oidc.OIDCLoginProtocol;
    import org.keycloak.provider.ProviderConfigProperty;
    import org.keycloak.services.ServicesLogger;
    import org.keycloak.services.messages.Messages;
    import org.keycloak.services.validation.Validation;
    import org.keycloak.userprofile.UserProfile;
    import org.keycloak.userprofile.UserProfileContext;
    import org.keycloak.userprofile.UserProfileProvider;
    import org.keycloak.userprofile.ValidationException;

    import javax.persistence.EntityManager;
    import javax.persistence.EntityManagerFactory;
    import javax.persistence.EntityTransaction;
    import javax.ws.rs.core.MultivaluedMap;
    import java.util.*;
    import java.util.stream.Collectors;


    public class AppRegistrationUserCreation implements FormAction, FormActionFactory {
        private static ServicesLogger log = ServicesLogger.LOGGER;

        public static PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        public static final String PROVIDER_ID = "mgo-registration-user-creation";

        public  static final List<String> IGNORE_REGISTRATION_ERRORS= Arrays.asList(Messages.MISSING_EMAIL,Messages.MISSING_LAST_NAME);

        public  static  List<FormMessage> getFormErrorsFromValidation(ValidationException pve){
            return Validation.getFormErrorsFromValidation(pve.getErrors()
                    .stream().filter(error -> !IGNORE_REGISTRATION_ERRORS.contains(error.getMessage())).collect(Collectors.toList())
            );
        }

        public static boolean hasValidationErrors(List<FormMessage> errors,String ...types){
                if(types.length==0){
                    return !errors.isEmpty();
                }

            for (String type : types) {
                if (errors.stream().anyMatch(error->error.getMessage().equals(type))) {
                    return true;
                }
            }
            return false;

        }


        public  static void preValidateForm(MultivaluedMap<String, String> formData,List<FormMessage> errors) {
            String rawUsername = formData.getFirst(UserModel.USERNAME);
            try {

                if(rawUsername!=null && !rawUsername.trim().isBlank()) {
                    Phonenumber.PhoneNumber phoneNumberProto = phoneUtil.parse(rawUsername, "MG");
                    String phoneNumber = phoneUtil.format(phoneNumberProto, PhoneNumberUtil.PhoneNumberFormat.E164);
                    log.info("[AppRegistrationUserCreation]  Validate phone number : " + phoneNumber);
                    formData.put(UserModel.USERNAME,List.of(phoneNumber));
                }
                else {
                    errors.add(new FormMessage(UserModel.USERNAME,"missingUsernameMessage"));
                }
            }
            catch (NumberParseException e){
                log.info("[AppRegistrationUserCreation] Validate Invalid phone number: " + rawUsername);
                errors.add(new FormMessage(
                        UserModel.USERNAME,
                        "invalidPhoneNumber"
                ));
            }


        }




        public static boolean postValidateForm (MultivaluedMap<String, String> formData,ValidationContext context,ValidationException pve,List<FormMessage> errors ) {
            log.info("[AppRegistrationUserCreation] postValidateForm");

            Validation.getFormErrorsFromValidation(pve.getErrors()).forEach(error->{
                log.info("[AppRegistrationUserCreation] Validate postValidateForm all " + error.getMessage());
            });

            errors.addAll(getFormErrorsFromValidation(pve));


            errors.forEach(error->{
                log.info("[AppRegistrationUserCreation] Validate postValidateForm " + error.getMessage());
            });
            if(!errors.isEmpty()) {

                if (hasValidationErrors(errors,Messages.EMAIL_EXISTS)) {
                    context.error(Errors.EMAIL_IN_USE);
                } else if (hasValidationErrors(errors, Messages.MISSING_USERNAME, Messages.INVALID_EMAIL)) {
                    context.error(Errors.INVALID_REGISTRATION);
                } else if (hasValidationErrors(errors,Messages.USERNAME_EXISTS)) {
                    context.error(Errors.USERNAME_IN_USE);
                }

                context.validationError(formData, errors);
                return true;
            }

            return false;
        }




        @Override
        public String getHelpText() {
            return "This action must always be first! Validates the phone number of the user in validation phase.  In success phase, this will create the user in the database.";
        }

        @Override
        public List<ProviderConfigProperty> getConfigProperties() {
            return null;
        }

        @Override
        public void validate(ValidationContext context) {
            log.info("[AppRegistrationUserCreation] Validate");

            MultivaluedMap<String, String> formData = context.getHttpRequest().getDecodedFormParameters();
            context.getEvent().detail(Details.REGISTER_METHOD, "form");

            // KeycloakSession session = context.getSession();
            UserProfileProvider profileProvider = context.getSession().getProvider(UserProfileProvider.class);

            log.info("[AppRegistrationUserCreation] UserProfileProvider "+profileProvider.getClass().getName());


            List<FormMessage> errors = new ArrayList<>();
            preValidateForm(formData,errors);

            UserProfile profile = profileProvider.create(UserProfileContext.REGISTRATION_USER_CREATION, formData);
            String email = profile.getAttributes().getFirstValue(UserModel.EMAIL);

            String username = profile.getAttributes().getFirstValue(UserModel.USERNAME);
            String firstName = profile.getAttributes().getFirstValue(UserModel.FIRST_NAME);
            String lastName = profile.getAttributes().getFirstValue(UserModel.LAST_NAME);

            if(email!=null && ! email.trim().isBlank()){
                context.getEvent().detail(Details.EMAIL, email);
            }

            context.getEvent().detail(Details.USERNAME, username);
            context.getEvent().detail(Details.FIRST_NAME, firstName);
            context.getEvent().detail(Details.LAST_NAME, lastName);

            if (context.getRealm().isRegistrationEmailAsUsername()) {
                context.getEvent().detail(Details.USERNAME, email);
            }

            try {
                profile.validate();
            } catch (ValidationException pve) {
               boolean hasError = postValidateForm(formData,context,pve,errors);
               if(hasError){
                   return;
               }
            }
            context.success();
        }

        @Override
        public void buildPage(FormContext context, LoginFormsProvider form) {

        }

        @Override
        public void success(FormContext context) {

            log.info("[AppRegistrationUserCreation]  Success");

            MultivaluedMap<String, String> formData = context.getHttpRequest().getDecodedFormParameters();

            String email = formData.getFirst(UserModel.EMAIL);
            String username = formData.getFirst(UserModel.USERNAME);

            try {
                Phonenumber.PhoneNumber phoneNumberProto = phoneUtil.parse(username, "MG");

                String phoneNumber = phoneUtil.format(phoneNumberProto, PhoneNumberUtil.PhoneNumberFormat.E164);
                log.info("[AppRegistrationUserCreation]  Success phone number : " + phoneNumber);
                username = phoneNumber;
            }
            catch (NumberParseException e){
                log.info("[AppRegistrationUserCreation] Success Invalid phone number: " + username);
            }


            if (context.getRealm().isRegistrationEmailAsUsername()) {
                username = email;
            }

            context.getEvent().detail(Details.USERNAME, username)
                    .detail(Details.REGISTER_METHOD, "form")
                    .detail(Details.EMAIL, email);

            KeycloakSession session = context.getSession();

            UserProfileProvider profileProvider = new AppDeclarativeUserProfileProvider(context.getSession(), new HashMap<>(),"{}");            UserProfile profile = profileProvider.create(UserProfileContext.REGISTRATION_USER_CREATION, formData);
            UserModel user = profile.create();
            user.setLastName(formData.getFirst(UserModel.LAST_NAME));// WHY : last name is empty

            user.setEnabled(true);

            context.setUser(user);

            context.getAuthenticationSession().setClientNote(OIDCLoginProtocol.LOGIN_HINT_PARAM, username);

            context.getEvent().user(user);
            context.getEvent().success();
            context.newEvent().event(EventType.LOGIN);
            context.getEvent().client(context.getAuthenticationSession().getClient().getClientId())
                    .detail(Details.REDIRECT_URI, context.getAuthenticationSession().getRedirectUri())
                    .detail(Details.AUTH_METHOD, context.getAuthenticationSession().getProtocol());
            String authType = context.getAuthenticationSession().getAuthNote(Details.AUTH_TYPE);
            if (authType != null) {
                context.getEvent().detail(Details.AUTH_TYPE, authType);
            }

           registerPerson(user,context);
        }

        private PersonEntity registerPerson(UserModel user,FormContext context){
            JpaConnectionProvider jpa = context.getSession().getProvider(JpaConnectionProvider.class);

            EntityManagerFactory emf = jpa.getEntityManager().getEntityManagerFactory();

            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();

            PersonEntity person = new PersonEntity();
            person.setFirstName(user.getFirstName());
            person.setLastName(user.getLastName());
            person.setUserId(user.getId());
            person.setEmail(user.getEmail());
            person.setPhoneNumber(user.getUsername());
            person.setCreationDate(new Date(user.getCreatedTimestamp()));

            try {
                tx.begin();
                em.persist(person);
                tx.commit();
            } finally {
                em.close();
            }

            return person;
        }

        @Override
        public boolean requiresUser() {
            return false;
        }

        @Override
        public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
            return true;
        }

        @Override
        public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {

        }

        @Override
        public boolean isUserSetupAllowed() {
            return false;
        }


        @Override
        public void close() {

        }

        @Override
        public String getDisplayType() {
            return "MGO Registration User Creation";
        }

        @Override
        public String getReferenceCategory() {
            return null;
        }

        @Override
        public boolean isConfigurable() {
            return false;
        }

        private static AuthenticationExecutionModel.Requirement[] REQUIREMENT_CHOICES = {
                AuthenticationExecutionModel.Requirement.REQUIRED,
                AuthenticationExecutionModel.Requirement.DISABLED
        };
        @Override
        public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
            return REQUIREMENT_CHOICES;
        }
        @Override
        public FormAction create(KeycloakSession session) {
            return this;
        }

        @Override
        public void init(Config.Scope config) {

        }

        @Override
        public void postInit(KeycloakSessionFactory factory) {

        }

        @Override
        public String getId() {
            return PROVIDER_ID;
        }
    }
