
package voyage.mgo;

import org.keycloak.Config;
import org.keycloak.authentication.FormAction;
import org.keycloak.authentication.FormActionFactory;
import org.keycloak.authentication.FormContext;
import org.keycloak.events.Details;
import org.keycloak.events.Errors;
import org.keycloak.forms.login.LoginFormsProvider;
import org.keycloak.models.*;
import org.keycloak.models.utils.FormMessage;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.services.ServicesLogger;
import org.keycloak.services.messages.Messages;
import org.keycloak.services.validation.Validation;
import org.keycloak.userprofile.UserProfile;
import org.keycloak.userprofile.UserProfileContext;
import org.keycloak.userprofile.UserProfileProvider;
import org.keycloak.userprofile.ValidationException;

import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static voyage.mgo.AppRegistrationUserCreation.*;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class AppRegistrationProfile implements FormAction, FormActionFactory {
    public static final String PROVIDER_ID = "mgo-registration-profile-action";
    private static ServicesLogger log = ServicesLogger.LOGGER;

    @Override
    public String getHelpText() {
        return "Validates email, first name, and last name attributes and stores them in user data.";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return null;
    }

    @Override
    public void validate(org.keycloak.authentication.ValidationContext context) {
        MultivaluedMap<String, String> formData = context.getHttpRequest().getDecodedFormParameters();

        context.getEvent().detail(Details.REGISTER_METHOD, "form");


        List<FormMessage> errors = new ArrayList<>();
        preValidateForm(formData,errors);
        UserProfileProvider profileProvider = context.getSession().getProvider(UserProfileProvider.class);
        UserProfile profile = profileProvider.create(UserProfileContext.REGISTRATION_PROFILE, formData);

        try {
            profile.validate();
        } catch (ValidationException pve) {
            errors.addAll(getFormErrorsFromValidation(pve));

            if(!errors.isEmpty()) {

                if (hasValidationErrors(errors,Messages.EMAIL_EXISTS, Messages.INVALID_EMAIL)) {
                    context.getEvent().detail(Details.EMAIL, profile.getAttributes().getFirstValue(UserModel.EMAIL));
                }

                if (hasValidationErrors(errors,Messages.EMAIL_EXISTS)) {
                    context.error(Errors.EMAIL_IN_USE);
                } else
                    context.error(Errors.INVALID_REGISTRATION);

                context.validationError(formData, errors);

                return;
            }
        }

        context.success();
    }

    @Override
    public void success(FormContext context) {
        UserModel user = context.getUser();
        UserProfileProvider profileProvider = new AppDeclarativeUserProfileProvider(context.getSession(), new HashMap<>(),"{}");
        profileProvider.create(UserProfileContext.REGISTRATION_PROFILE, context.getHttpRequest().getDecodedFormParameters(), user).update();
    }

    @Override
    public void buildPage(FormContext context, LoginFormsProvider form) {
        // complete
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
        return "MGO Profile Validation";
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
