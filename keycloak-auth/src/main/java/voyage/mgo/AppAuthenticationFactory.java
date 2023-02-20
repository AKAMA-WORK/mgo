package voyage.mgo;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.authenticators.browser.UsernamePasswordFormFactory;
import org.keycloak.models.KeycloakSession;

public class AppAuthenticationFactory extends UsernamePasswordFormFactory {
    public static final String PROVIDER_ID = "mgo-authenticator";
    public static final AppAuthenticator SINGLETON = new AppAuthenticator();

    @Override
    public Authenticator create(KeycloakSession session) {
        return SINGLETON;
    }

    @Override
    public void init(Config.Scope scope) {
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getDisplayType() {
        return "MGO User Form";
    }

    @Override
    public String getHelpText() {
        return "Use phone number as username";
    }
}
