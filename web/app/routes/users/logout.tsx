
import { ActionFunction, json, LoaderFunction, redirect } from "@remix-run/node";
import { useTransition } from "@remix-run/react";
import { Spin } from "antd";
import { authenticator, getAuthenticatedToken, logoutUserFromAuthenticator } from "~/utils/server/auth.server";
import { getSession } from "~/utils/server/session.server";
// Second, we need to export an action function, here we will use the
// `authenticator.authenticate method`
export let action: ActionFunction = async ({ request }) => {
   
   const token = await getAuthenticatedToken(request) 

   if(!token){
      return redirect('/');
   }
   

   await logoutUserFromAuthenticator(token);


  const response =  await authenticator.logout(request, {
    redirectTo: "/",
  });



  return response
};


export let loader: LoaderFunction = () => redirect("/");