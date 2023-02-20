import { createCookieSessionStorage } from "@remix-run/node";
import { SeatSelectionItem } from "~/types/SeatSelectionItem";

// export the whole sessionStorage object

export const MGO_SESSION_KEY = '_mgo';

export const sessionStorage = createCookieSessionStorage({
  cookie: {
    name: MGO_SESSION_KEY, // use any name you want here
    sameSite: "lax", // this helps with CSRF
    path: "/", // remember to add this so the cookie will work in all routes
    httpOnly: true, // for security reasons, make this cookie http only
    secrets: [process.env.SESSION_SECRET as any], // replace this with an actual secret
    secure: process.env.NODE_ENV === "production", // enable this in prod only
  },
});

//let authSessionKey = "auth"
let seatsSelectionSessionKey = "seatsSelection";

export const getSession = async (
  input: Request | string | null | undefined,
) => {
  let cookieHeader =
    !input || typeof input === "string" ? input : input.headers.get("Cookie");
  let session = await sessionStorage.getSession(cookieHeader);

  /**accessToken: string, refreshToken: string } | null */
  return {
    commitSession() {
      return sessionStorage.commitSession(session);
    },
    getAuthenticationToken: (): { accessToken: string, refreshToken: string } | null => {
      const authSession = session.get(MGO_SESSION_KEY)
      const token = authSession //? JSON.parse(MGO_SESSION_KEY) : undefined
      return token
    },
    getSeatsSelection: (): SeatSelectionItem[] => {
      return JSON.parse(session.get(seatsSelectionSessionKey) || "[]");
    },
    setSeatsSelection(seats: SeatSelectionItem[]) {
      session.set(seatsSelectionSessionKey, JSON.stringify(seats));
    },
  }
}

// you can also export the methods individually for your own usage
//export  const { getSession, commitSession, destroySession } = sessionStorage;