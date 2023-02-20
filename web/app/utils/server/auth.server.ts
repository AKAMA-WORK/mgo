import fetch from "@remix-run/web-fetch";
import { Authenticator } from "remix-auth";
import { Person } from "~/types/person/Person";
import { User } from "~/types/User";
import { KeycloakStrategy } from "./keycloak-auth";
import { searchPeople } from "./people.server";
import { MGO_SESSION_KEY, getSession, sessionStorage } from './session.server'

// Create an instance of the authenticator, pass a generic with what your
// strategies will return and will be stored in the session


export const authenticator = new Authenticator<User>(sessionStorage, { sessionKey: MGO_SESSION_KEY });


let keycloakStrategy = new KeycloakStrategy(
  {
    baseUrl: process.env.KEYCLOAK_BASE_URL as any,
    realm: process.env.KEYCLOAK_REALM as any,
    clientID: process.env.KEYCLOAK_CLIENT_ID as any,
    clientSecret: process.env.KEYCLOAK_CLIENT_SECRET as any,
    callbackURL: "/users/auth-callback",
  },
  async ({ accessToken, refreshToken, extraParams, profile }) => {
    // Get the user data from your DB or API using the tokens and profile
    const peopleResult = await searchPeople({
      filter: {
        userId: profile.id
      }
    }, accessToken);

    const person = peopleResult.items?.[0]

    return ({ accessToken, refreshToken, person, email: profile.emails[0].value, name: profile._json.name, userId: profile.id, phoneNumber: profile._json.preferred_username });
  }
);

authenticator.use(keycloakStrategy as any);



const logoutUrl = `${process.env.KEYCLOAK_BASE_URL}/realms/${process.env.KEYCLOAK_REALM}/protocol/openid-connect/logout`;
export const logoutUserFromAuthenticator = async ({ accessToken, refreshToken }: { accessToken: string, refreshToken: string }) => {


  const params = new URLSearchParams();
  params.set('client_id', process.env.KEYCLOAK_CLIENT_ID as any);
  params.set('client_secret', process.env.KEYCLOAK_CLIENT_SECRET as any);
  params.set('refresh_token', refreshToken);

  const authorization = `Bearer ${accessToken}`;

  return fetch(logoutUrl, {
    method: 'POST',
    headers: {
      Authorization: authorization,
      'Content-Type': 'application/x-www-form-urlencoded',
    }, body: params
  })



}


export const getAuthenticatedUser = async (request: Request): Promise<User | null> => {
  const userWithCredentials = await authenticator.isAuthenticated(request)

  const user: User | null = userWithCredentials ? {
    userId: userWithCredentials.userId,
    name: userWithCredentials.name,
    email: userWithCredentials.email,
    phoneNumber: userWithCredentials.phoneNumber,
    person: userWithCredentials.person
  } : null

  return user;
}

export const getAuthenticatedToken = async (request: Request): Promise<{ accessToken: string, refreshToken: string } | null> => {
  const session = (await getSession(request))?.getAuthenticationToken();
  if (session?.accessToken) {

    return { accessToken: session.accessToken, refreshToken: session.refreshToken }
  }

  return null;
}