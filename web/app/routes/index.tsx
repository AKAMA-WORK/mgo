import { json, LoaderFunction, MetaFunction, redirect } from "@remix-run/node";
import { useTranslation } from "react-i18next";
import i18n from "~/i18next.server";
import { Departure } from "~/types/departure/Departure";
import { useLoaderData } from "@remix-run/react";
import Companies from "~/components/sections/Companies/Companies";
import { Organization } from "~/types/Organization";
import SearchBar from "~/components/sections/SearchBar/SearchBar";
import RecentSearchResult from "~/components/sections/RecentSearchResult/RecentSearchResult";
import WhyChoose from "~/components/sections/WhyChoose/WhyChoose";
import JoinUs from "~/components/sections/JoinUs/JoinUs";
import { City } from "~/types/City";
import { callWithAuthenticationTokenIfAuthenticated } from "~/utils/server/callable-with-token-util";
import { searchCities } from "~/utils/server/cities.server";
import { searchOrganizations } from "~/utils/server/organizations.server";


export const handle = {
  displayBanner: true
}
interface LoaderData {
  departures: Departure[]
  organizations: Organization[]
  cities: City[]
  title: string
}

export let loader: LoaderFunction = async ({ request }) => {
  let t = await i18n.getFixedT(request);
  let title = t("title");




  return json( await callWithAuthenticationTokenIfAuthenticated<LoaderData>(request, async (token) => {

    const [citiesResult, organizationsResult] = await Promise.all([searchCities(token?.accessToken), searchOrganizations(token?.accessToken)])

    return { title, departures:[], organizations: organizationsResult.items, cities: citiesResult.items };


  }))

  /*const token = await getAuthenticatedToken(request);

  
  
  try{
    const [citiesResult, organizationsResult ] = await Promise.all([searchCities(token?.accessToken), searchOrganizations(token?.accessToken)])

    return json({ title, departures, organizations: organizationsResult.items, cities: citiesResult.items });
  }
  catch(error){


    if((error as any).message==='401'){
      await authenticator.logout(request, {
        redirectTo: request.url,
      });
    }

 

    

    const [citiesResult, organizationsResult ] = await Promise.all([searchCities(token?.accessToken), searchOrganizations(token?.accessToken)])

    return json({ title, departures, organizations: organizationsResult.items, cities: citiesResult.items });*/


}




export let meta: MetaFunction = ({ data }) => {
  return { title: data?.title || 'M-GO' };
};


export default function Index() {
  const { t } = useTranslation()
  const data = useLoaderData<LoaderData>()
  return (
    <>
      <SearchBar className="app-search-bar" cities={data.cities} />
      <RecentSearchResult className="pvn" departures={data?.departures || []} />
      <Companies className="pvn" companies={data?.organizations || []} />
      <WhyChoose className="pvn" />
      <JoinUs className="pvn" />
    </>
  );
}
