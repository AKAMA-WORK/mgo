import React, { useEffect, useState } from 'react';
import {
  json,
  LinksFunction,
  LoaderFunction,
  MetaFunction,
} from "@remix-run/node";
import {
  Outlet,
  useCatch,
  useLoaderData,
} from "@remix-run/react";
import antdStyles from "./styles/theme/antd.css";
import appStyles from "./styles/app.css";
import i18next from "~/i18next.server";
import { i18nCookie } from './utils/server/cookie.server';
import Document, { LoaderData } from './Document';
import ErrorPage from './Error'
import { authenticator, getAuthenticatedUser } from './utils/server/auth.server';
import { User } from './types/User';


export const meta: MetaFunction = () => ({
  charset: "utf-8",
  title: "M-GO",
  viewport: "width=device-width,initial-scale=1",
});
export const links: LinksFunction = () => {
  return [{ rel: "stylesheet", href: antdStyles }, { rel: "stylesheet", href: appStyles },
  { rel: "stylesheet", href: 'https://fonts.googleapis.com/css?family=Montserrat' }
  ];
};
export let loader: LoaderFunction = async ({ request }) => {
  let locale = await i18next.getLocale(request);
  const socketIoUrl = process.env.SOCKETIO_URL as any;

  const user= await getAuthenticatedUser(request);

  return json<LoaderData>({ locale, socketIoUrl, user }, {
    headers: { "Set-Cookie": await i18nCookie.serialize(locale) }
  });
};


export let handle = {
  // In the handle export, we can add a i18n key with namespaces our route
  // will need to load. This key can be a single string or an array of strings.
  // TIP: In most cases, you should set this to your defaultNS from your i18n config
  // or if you did not set one, set it to the i18next default namespace "translation"
  i18n: "common",
};


export default function App() {
  const loaderData = useLoaderData<LoaderData>()
  return (<Document loaderData={loaderData}>
    <Outlet />
  </Document>)
}
export function CatchBoundary() {
  let caught = useCatch();
  switch (caught.status) {
     case 401:
      return (<ErrorPage
        code="403"
        title={`${caught.status} ${caught.statusText}`}
        subTitle="Not authorized"
      />)

    case 404:
      return (<ErrorPage
        code={caught.status}
        title={`${caught.status} ${caught.statusText}`}
        subTitle="Sorry, the page you visited does not exist."
      />
      );
    default:
      throw new Error(
        `Unexpected caught response with status: ${caught.status}`
      );
  }
}
export function ErrorBoundary({ error }: { error: Error }) {
  console.error(error);
  return (
    <ErrorPage
      code={500}
      title="500"
      subTitle="Sorry, something went wrong."
    />)
}
