import { json, LoaderFunction } from "@remix-run/node"
import { callWithAuthenticationTokenIfAuthenticated } from "~/utils/server/callable-with-token-util";
import { searchCities } from "~/utils/server/cities.server";

export const loader: LoaderFunction = async ({ request }) => {
  return json(callWithAuthenticationTokenIfAuthenticated(request, async (token) => {
    return await searchCities(token?.accessToken);
  }));


}



export default function MarkRead() {
  return <div>Oops... You should not see this.</div>
}