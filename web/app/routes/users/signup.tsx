
import { ActionFunction, json, LoaderFunction, redirect } from "@remix-run/node";
import { useTransition } from "@remix-run/react";
import { Spin } from "antd";
import { authenticator } from "~/utils/server/auth.server";
// Second, we need to export an action function, here we will use the
// `authenticator.authenticate method`
export let action: ActionFunction = async ({ request }) => {
    // we call the method with the name of the strategy we want to use and the
    // request object, optionally we pass an object with the URLs we want the user
    // to be redirected to after a success or a failure

   // const formData = await request.formData();
    const successRedirect ='/' // formData.get('successRedirect')?.toString() || '/'
    return await authenticator.authenticate("keycloak", request, {
      successRedirect,
      failureRedirect: "/users/login",
    });
  };

  export let loader: LoaderFunction = () => redirect("/");

