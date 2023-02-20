import * as React from 'react'
import type {ActionFunction} from '@remix-run/node'
import {json, redirect} from '@remix-run/node'
import { i18nCookie } from '~/utils/server/cookie.server';

export const action: ActionFunction = async ({request}) => {
  const cookieHeader = request.headers.get("Cookie");
  const cookie =
    (await i18nCookie.parse(cookieHeader)) || {};
  const bodyParams = await request.formData();

  if (bodyParams.get("locale")) {
    cookie.i18n = bodyParams.get('locale');
  }

  return redirect("/", {
    headers: {
      "Set-Cookie": await i18nCookie.serialize(cookie),
    },
  });
}

export const loader = () => redirect('/', {status: 404})

export default function MarkRead() {
  return <div>Oops... You should not see this.</div>
}
