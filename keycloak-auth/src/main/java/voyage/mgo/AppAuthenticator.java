package voyage.mgo;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.authenticators.browser.UsernamePasswordForm;
import org.keycloak.events.Errors;
import org.keycloak.services.managers.AuthenticationManager;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.Arrays;

import static org.keycloak.services.validation.Validation.FIELD_PASSWORD;
import static org.keycloak.services.validation.Validation.FIELD_USERNAME;

public class AppAuthenticator extends UsernamePasswordForm implements Authenticator {
   private PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    @Override
    public boolean validateUserAndPassword(AuthenticationFlowContext context, MultivaluedMap inputData) {

        log.info("[AppAuthenticator] Validate user and password");
        String username = (String) inputData.getFirst(AuthenticationManager.FORM_USERNAME);
        log.info("[AppAuthenticator] Validate user and password : "+username);

        if(username!=null && !username.trim().isBlank()){
            if(!username.contains("@")){
                log.info("[AppAuthenticator] Validate user and password is phone number: "+username);

                try {
                    Phonenumber.PhoneNumber phoneNumberProto = phoneUtil.parse(username, "MG");

                    String phoneNumber = phoneUtil.format(phoneNumberProto, PhoneNumberUtil.PhoneNumberFormat.E164);
                    log.info("[AppAuthenticator] Validate user and password format phone number: "+phoneNumber);

                    inputData.put(AuthenticationManager.FORM_USERNAME, Arrays.asList(phoneNumber));

                }
                catch (NumberParseException e){
                    context.getEvent().error("invalid_phone_number");

                    Response challengeResponse = challenge(context, "invalidPhoneNumber", FIELD_USERNAME);
                    context.forceChallenge(challengeResponse);

                    return false;

                }




            }
        }




        return super.validateUserAndPassword(context,inputData);

    }
}
