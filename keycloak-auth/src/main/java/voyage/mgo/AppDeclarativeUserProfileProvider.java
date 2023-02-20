

package voyage.mgo;

import org.keycloak.Config;
import org.keycloak.common.Profile;
import org.keycloak.common.util.MultivaluedHashMap;
import org.keycloak.component.AmphibianProviderFactory;
import org.keycloak.component.ComponentModel;
import org.keycloak.component.ComponentValidationException;
import org.keycloak.models.ClientModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.protocol.oidc.OIDCLoginProtocol;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.services.ServicesLogger;
import org.keycloak.services.messages.Messages;
import org.keycloak.sessions.AuthenticationSessionModel;
import org.keycloak.userprofile.*;
import org.keycloak.userprofile.config.*;
import org.keycloak.userprofile.validator.*;
import org.keycloak.validate.AbstractSimpleValidator;
import org.keycloak.validate.ValidatorConfig;
import org.keycloak.validate.validators.EmailValidator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.keycloak.common.util.ObjectUtil.isBlank;
import static org.keycloak.protocol.oidc.TokenManager.getRequestedClientScopes;
import static org.keycloak.userprofile.DefaultAttributes.READ_ONLY_ATTRIBUTE_KEY;
import static org.keycloak.userprofile.config.UPConfigUtils.readConfig;

public class AppDeclarativeUserProfileProvider extends AbstractUserProfileProvider<UserProfileProvider>
        implements AmphibianProviderFactory<UserProfileProvider> {

    private static ServicesLogger log = ServicesLogger.LOGGER;

    public static final String ID = "mgo-declarative-user-profile";
    public static final int PROVIDER_PRIORITY = 1;
    public static final String UP_PIECES_COUNT_COMPONENT_CONFIG_KEY = "mgo-config-pieces-count";
    private static final String PARSED_CONFIG_COMPONENT_KEY = "mgo.kc.user.profile.metadata";
    private static final String UP_PIECE_COMPONENT_CONFIG_KEY_BASE = "mgo-config-piece-";



    private static boolean requestedScopePredicate(AttributeContext context, Set<String> configuredScopes) {
        KeycloakSession session = context.getSession();
        AuthenticationSessionModel authenticationSession = session.getContext().getAuthenticationSession();

        if (authenticationSession == null) {
            return false;
        }

        String requestedScopesString = authenticationSession.getClientNote(OIDCLoginProtocol.SCOPE_PARAM);
        ClientModel client = authenticationSession.getClient();

        return getRequestedClientScopes(requestedScopesString, client).map((csm) -> csm.getName()).anyMatch(configuredScopes::contains);
    }

    private String defaultRawConfig;

    public AppDeclarativeUserProfileProvider() {
        defaultRawConfig = UPConfigUtils.readDefaultConfig();
    }

    public AppDeclarativeUserProfileProvider(KeycloakSession session, Map<UserProfileContext, UserProfileMetadata> metadataRegistry, String defaultRawConfig) {
        super(session, metadataRegistry);
        this.defaultRawConfig = defaultRawConfig;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    protected UserProfileProvider create(KeycloakSession session, Map<UserProfileContext, UserProfileMetadata> metadataRegistry) {
        return new DeclarativeUserProfileProvider(session, metadataRegistry, defaultRawConfig);
    }

    @Override
    protected Attributes createAttributes(UserProfileContext context, Map<String, ?> attributes,
            UserModel user, UserProfileMetadata metadata) {
        log.info("[AppDeclarativeUserProfileProvider] createAttributes");

            attributes.forEach((name, attr)->{
                 log.info("[AppDeclarativeUserProfileProvider] createAttributes "+name);
             });

        UserProfileContext attributeContext = metadata==null? UserProfileContext.REGISTRATION_USER_CREATION : metadata.getContext();

            return new DefaultAttributes(attributeContext, attributes, user, configureUserProfile( metadata), session);
    }

    @Override
    protected UserProfileMetadata configureUserProfile(UserProfileMetadata metadata, KeycloakSession session) {

        UserProfileContext context = metadata==null? UserProfileContext.REGISTRATION_USER_CREATION : metadata.getContext();

        //if(context.equals(UserProfileContext.REGISTRATION_USER_CREATION)){
                return this.createDefaultProfile(context);
        //    }
     //   UserProfileMetadata decoratedMetadata = metadata==null ? new UserProfileMetadata(context): metadata.clone();


       /* if (!context.equals(UserProfileContext.USER_API)) {
                decoratedMetadata.addAttribute(UserModel.FIRST_NAME, 1, new AttributeValidatorMetadata(BlankAttributeValidator.ID, BlankAttributeValidator.createConfig(
                        Messages.MISSING_FIRST_NAME, metadata.getContext() == UserProfileContext.IDP_REVIEW))).setAttributeDisplayName("${firstName}");
                decoratedMetadata.addAttribute(UserModel.LAST_NAME, 2, new AttributeValidatorMetadata(BlankAttributeValidator.ID, BlankAttributeValidator.createConfig(Messages.MISSING_LAST_NAME, metadata.getContext() == UserProfileContext.IDP_REVIEW))).setAttributeDisplayName("${lastName}");
                
                //add email format validator to legacy profile
                List<AttributeMetadata> em = decoratedMetadata.getAttribute(UserModel.EMAIL);
                for(AttributeMetadata e: em) {
                    e.addValidator(new AttributeValidatorMetadata(EmailValidator.ID, ValidatorConfig.builder().config(EmailValidator.IGNORE_EMPTY_VALUE, true).build()));
                }
                
                return decoratedMetadata;
            }

            return decoratedMetadata;*/

    }

    @Override
    public String getHelpText() {
        return null;
    }

    private UserProfileMetadata createDefaultProfile(UserProfileContext context) {
        UserProfileMetadata metadata = new UserProfileMetadata(context);

        metadata.addAttribute(UserModel.USERNAME, -2,
                new AttributeValidatorMetadata(UsernameHasValueValidator.ID),
                new AttributeValidatorMetadata(UsernameIDNHomographValidator.ID),
                new AttributeValidatorMetadata(DuplicateUsernameValidator.ID),
                new AttributeValidatorMetadata(UsernameMutationValidator.ID)).setAttributeDisplayName("${username}");

        metadata.addAttribute(UserModel.EMAIL, -1,
                        new AttributeValidatorMetadata(DuplicateEmailValidator.ID,ValidatorConfig.builder().config(EmailValidator.IGNORE_EMPTY_VALUE, true).build()),
                        new AttributeValidatorMetadata(EmailExistsAsUsernameValidator.ID))
                .setAttributeDisplayName("${email}");

        metadata.addAttribute(UserModel.FIRST_NAME, 1, new AttributeValidatorMetadata(BlankAttributeValidator.ID, BlankAttributeValidator.createConfig(
                Messages.MISSING_FIRST_NAME, context == UserProfileContext.IDP_REVIEW))).setAttributeDisplayName("${firstName}");



        return metadata;
    }

    @Override
    public void validateConfiguration(KeycloakSession session, RealmModel realm, ComponentModel model) throws ComponentValidationException {
        String upConfigJson = getConfigJsonFromComponentModel(model);

        if (!isBlank(upConfigJson)) {
            try {
                UPConfig upc = parseConfig(upConfigJson);
                List<String> errors = UPConfigUtils.validate(session, upc);

                if (!errors.isEmpty()) {
                    throw new ComponentValidationException(errors.toString());
                }
            } catch (IOException e) {
                throw new ComponentValidationException(e.getMessage(), e);
            }
        }

        // delete cache so new config is parsed and applied next time it is required
        // throught #configureUserProfile(metadata, session)
        if (model != null) {
            model.removeNote(PARSED_CONFIG_COMPONENT_KEY);
        }
    }

    @Override
    public String getConfiguration() {
            return null;

    }

    @Override
    public void setConfiguration(String configuration) {
        ComponentModel component = getComponentModel();

        removeConfigJsonFromComponentModel(component);

        RealmModel realm = session.getContext().getRealm();

        if (!isBlank(configuration)) {
            // store new parts
            List<String> parts = UPConfigUtils.getChunks(configuration, 3800);
            MultivaluedHashMap<String, String> config = component.getConfig();

            config.putSingle(UP_PIECES_COUNT_COMPONENT_CONFIG_KEY, "" + parts.size());

            int i = 0;

            for (String part : parts) {
                config.putSingle(UP_PIECE_COMPONENT_CONFIG_KEY_BASE + (i++), part);
            }

            realm.updateComponent(component);
        } else {
            realm.removeComponent(component);
        }
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return Collections.emptyList();
    }

    @Override
    public void init(Config.Scope config) {
        super.init(config);
    }

    @Override
    public int order() {
        return PROVIDER_PRIORITY;
    }

    public ComponentModel getComponentModel() {
        return getComponentModelOrCreate(session);
    }

    /**
     * Decorate basic metadata provided from {@link AbstractUserProfileProvider} based on 'per realm' configuration.
     * This method is called for each {@link UserProfileContext} in each realm, and metadata are cached then and this
     * method is called again only if configuration changes.
     *
     * @param decoratedMetadata base to be decorated based on configuration loaded from component model
     * @param model component model to get "per realm" configuration from
     * @return decorated metadata
     */
    protected UserProfileMetadata decorateUserProfileForCache(UserProfileMetadata decoratedMetadata, ComponentModel model) {
        UserProfileContext context = decoratedMetadata.getContext();
        UPConfig parsedConfig = getParsedConfig(model);

        // do not change config for REGISTRATION_USER_CREATION context, everything important is covered thanks to REGISTRATION_PROFILE
        if (parsedConfig == null || context == UserProfileContext.REGISTRATION_USER_CREATION) {
            return decoratedMetadata;
        }

        Map<String, UPGroup> groupsByName = asHashMap(parsedConfig.getGroups());
        int guiOrder = 0;
        
        for (UPAttribute attrConfig : parsedConfig.getAttributes()) {
            String attributeName = attrConfig.getName();
            List<AttributeValidatorMetadata> validators = new ArrayList<>();
            Map<String, Map<String, Object>> validationsConfig = attrConfig.getValidations();

            if (validationsConfig != null) {
                for (Map.Entry<String, Map<String, Object>> vc : validationsConfig.entrySet()) {
                    validators.add(createConfiguredValidator(vc.getKey(), vc.getValue()));
                }
            }

            UPAttributeRequired rc = attrConfig.getRequired();
            Predicate<AttributeContext> required = AttributeMetadata.ALWAYS_FALSE;

            if (rc != null && !isUsernameOrEmailAttribute(attributeName)) {
                // do not take requirements from config for username and email as they are
                // driven by business logic from parent!
                if (rc.isAlways() || UPConfigUtils.isRoleForContext(context, rc.getRoles())) {
                    required = AttributeMetadata.ALWAYS_TRUE;
                } else if (UPConfigUtils.canBeAuthFlowContext(context) && rc.getScopes() != null && !rc.getScopes().isEmpty()) {
                    // for contexts executed from auth flow and with configured scopes requirement
                    // we have to create required validation with scopes based selector
                    required = (c) -> requestedScopePredicate(c, rc.getScopes());
                }

                validators.add(new AttributeValidatorMetadata(AttributeRequiredByMetadataValidator.ID));
            }

            Predicate<AttributeContext> writeAllowed = AttributeMetadata.ALWAYS_FALSE;
            Predicate<AttributeContext> readAllowed = AttributeMetadata.ALWAYS_FALSE;
            UPAttributePermissions permissions = attrConfig.getPermissions();

            if (permissions != null) {
                Set<String> editRoles = permissions.getEdit();

                if (!editRoles.isEmpty()) {
                    writeAllowed = ac -> UPConfigUtils.isRoleForContext(ac.getContext(), editRoles);
                }

                Set<String> viewRoles = permissions.getView();

                if (viewRoles.isEmpty()) {
                    readAllowed = writeAllowed;
                } else {
                    readAllowed = createViewAllowedPredicate(writeAllowed, viewRoles);
                }
            }

            Predicate<AttributeContext> selector = AttributeMetadata.ALWAYS_TRUE;
            UPAttributeSelector sc = attrConfig.getSelector();
            if (sc != null && !isUsernameOrEmailAttribute(attributeName) && UPConfigUtils.canBeAuthFlowContext(context) && sc.getScopes() != null && !sc.getScopes().isEmpty()) {
                // for contexts executed from auth flow and with configured scopes selector
                // we have to create correct predicate
                selector = (c) -> requestedScopePredicate(c, sc.getScopes());
            }

            Map<String, Object> annotations = attrConfig.getAnnotations();
            String attributeGroup = attrConfig.getGroup();
            AttributeGroupMetadata groupMetadata = toAttributeGroupMeta(groupsByName.get(attributeGroup));

            if (isUsernameOrEmailAttribute(attributeName)) {
                // make sure username and email are writable if permissions are not set
                if (permissions == null || permissions.isEmpty()) {
                    writeAllowed = AttributeMetadata.ALWAYS_TRUE;
                }

                List<AttributeMetadata> atts = decoratedMetadata.getAttribute(attributeName);

                if (atts.isEmpty()) {
                    // attribute metadata doesn't exist so we have to add it. We keep it optional as Abstract base
                    // doesn't require it.
                    decoratedMetadata.addAttribute(attributeName, guiOrder++, writeAllowed, validators)
                            .addAnnotations(annotations)
                            .setAttributeDisplayName(attrConfig.getDisplayName())
                            .setAttributeGroupMetadata(groupMetadata);
                } else {
                    final int localGuiOrder = guiOrder++;
                    // only add configured validators and annotations if attribute metadata exist
                    atts.stream().forEach(c -> c.addValidator(validators)
                            .addAnnotations(annotations)
                            .setAttributeDisplayName(attrConfig.getDisplayName())
                            .setGuiOrder(localGuiOrder)
                            .setAttributeGroupMetadata(groupMetadata));
                }
            } else {
                // always add validation for immutable/read-only attributes
                validators.add(new AttributeValidatorMetadata(ImmutableAttributeValidator.ID));
                decoratedMetadata.addAttribute(attributeName, guiOrder++, validators, selector, writeAllowed, required, readAllowed)
                        .addAnnotations(annotations)
                        .setAttributeDisplayName(attrConfig.getDisplayName())
                        .setAttributeGroupMetadata(groupMetadata);
            }
        }

        return decoratedMetadata;

    }

    private Map<String, UPGroup> asHashMap(List<UPGroup> groups) {
        return groups.stream().collect(Collectors.toMap(g -> g.getName(), g -> g));
    }
    
    private AttributeGroupMetadata toAttributeGroupMeta(UPGroup group) {
        if (group == null) {
            return null;
        }
        return new AttributeGroupMetadata(group.getName(), group.getDisplayHeader(), group.getDisplayDescription(), group.getAnnotations());
    }

    private boolean isUsernameOrEmailAttribute(String attributeName) {
        return UserModel.USERNAME.equals(attributeName) || UserModel.EMAIL.equals(attributeName);
    }

    private Predicate<AttributeContext> createViewAllowedPredicate(Predicate<AttributeContext> canEdit,
            Set<String> viewRoles) {
        return ac -> UPConfigUtils.isRoleForContext(ac.getContext(), viewRoles) || canEdit.test(ac);
    }

    /**
     * Get parsed config file configured in model. Default one used if not configured.
     *
     * @param model to take config from
     * @return parsed configuration
     */
    protected UPConfig getParsedConfig(ComponentModel model) {
        String rawConfig = getConfigJsonFromComponentModel(model);

        if (!isBlank(rawConfig)) {
            try {
                UPConfig upc = parseConfig(rawConfig);

                //validate configuration to catch things like changed/removed validators etc, and warn early and clearly about this problem
                List<String> errors = UPConfigUtils.validate(session, upc);
                if (!errors.isEmpty()) {
                    throw new RuntimeException("UserProfile configuration for realm '" + session.getContext().getRealm().getName() + "' is invalid: " + errors.toString());
                }
                return upc;

            } catch (IOException e) {
                throw new RuntimeException("UserProfile configuration for realm '" + session.getContext().getRealm().getName() + "' is invalid:" + e.getMessage(), e);
            }
        }

        return null;
    }

    private UPConfig parseConfig(String rawConfig) throws IOException {
        return readConfig(new ByteArrayInputStream(rawConfig.getBytes("UTF-8")));
    }

    /**
     * Get component to store our "per realm" configuration into.
     *
     * @param session to be used, and take realm from
     * @return component
     */
    private ComponentModel getComponentModelOrCreate(KeycloakSession session) {
        RealmModel realm = session.getContext().getRealm();
        return realm.getComponentsStream(realm.getId(), UserProfileProvider.class.getName()).findAny().orElseGet(() -> realm.addComponentModel(createComponentModel()));
    }

    /**
     * Create the component model to store configuration
     * @return component model
     */
    protected ComponentModel createComponentModel() {
        return new DeclarativeUserProfileModel(getId());
    }

    /**
     * Create validator for validation configured in the user profile config.
     *
     * @param validator id to create validator for
     * @param validatorConfig of the validator
     * @return validator metadata to run given validation
     */
    protected AttributeValidatorMetadata createConfiguredValidator(String validator, Map<String, Object> validatorConfig) {
        return new AttributeValidatorMetadata(validator, ValidatorConfig.builder().config(validatorConfig).config(AbstractSimpleValidator.IGNORE_EMPTY_VALUE, true).build());
    }

    private String getConfigJsonFromComponentModel(ComponentModel model) {
        if (model == null)
            return null;

        int count = model.get(UP_PIECES_COUNT_COMPONENT_CONFIG_KEY, 0);
        if (count < 1) {
            return defaultRawConfig;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            String v = model.get(UP_PIECE_COMPONENT_CONFIG_KEY_BASE + i);
            if (v != null)
                sb.append(v);
        }

        return sb.toString();
    }

    private void removeConfigJsonFromComponentModel(ComponentModel model) {
        if (model == null)
            return;

        int count = model.get(UP_PIECES_COUNT_COMPONENT_CONFIG_KEY, 0);
        if (count < 1) {
            return;
        }

        for (int i = 0; i < count; i++) {
            model.getConfig().remove(UP_PIECE_COMPONENT_CONFIG_KEY_BASE + i);
        }
        model.getConfig().remove(UP_PIECES_COUNT_COMPONENT_CONFIG_KEY);
    }

}
