import React, { useState, useEffect } from 'react';
import { useCatch, useLoaderData, useSubmit } from "@remix-run/react";
import { LoaderFunction } from "@remix-run/node";
import { Departure } from "~/types/departure/Departure";

import DeparturesList from "~/components/departures/DeparturesList";
import qs from 'qs'
import { getAuthenticatedToken } from "~/utils/server/auth.server";
import { City } from "~/types/City";
import { callWithAuthenticationTokenIfAuthenticated } from "~/utils/server/callable-with-token-util";
import { SearchResult } from "~/types/SearchResult";
import ErrorPage from "~/Error";
import { Button, Result, Skeleton } from "antd";
import { SearchDepartureAggregate } from "~/types/departure/SearchDepartureAggregate";
import { getSession } from "~/utils/server/session.server";
import { searchDepartures } from "~/utils/server/departures.server";
import { Paging } from "~/types/Paging";
import { parseSearchDepartureParams } from "~/utils/departure-util";
import { computePaging } from "~/utils/offset-paging-util";
import { nestedObjectKeysToUrlSquareBrackets, toURLSearchParams } from "~/utils/url-utils";



interface LoaderData {
    departures: SearchResult<Departure, SearchDepartureAggregate>
    paging: Paging
    query: {
        filter?: Record<string, any>
    }

}
export const loader: LoaderFunction = async ({ params, request }) => {
    const url = new URL(request.url);
    const query = qs.parse(url.search?.substring(1));
    const session = await getSession(request);
    const departureParams = parseSearchDepartureParams(query);

    return callWithAuthenticationTokenIfAuthenticated<LoaderData>(request, async (token) => {
        const [departuresResult, seatSelections] = await Promise.all([searchDepartures(departureParams as any, token?.accessToken),
        session.getSeatsSelection()
        ]);

        const paging = computePaging({ ...departureParams.paging, totalCount: departuresResult?.totalCount || 0 })


        return {
            departures: seatSelections?.length ? {
                ...departuresResult,
                items: departuresResult.items.map(item => ({
                    ...item,
                    seatSelection: seatSelections.find(s => s.departureId === item.departureId)
                }))
            } : departuresResult,
            query,
            paging
        }

    });
}


export default function Departure() {
    const data = useLoaderData<LoaderData>();

    const submit = useSubmit();

    const handleChangePaging = (paging: Paging) => {
        submit(toURLSearchParams(nestedObjectKeysToUrlSquareBrackets({ ...(data.query || {}), paging: { limit: paging.limit, offset: paging.offset } })), { method: 'get', action: `/departures` })
    }
    

    return (
        <>            
            <DeparturesList onPagingChange={handleChangePaging} paging={data.paging} departures={data.departures}/>            
        </>
            
    );
}




export function ErrorBoundary({ error }: { error: Error }) {
    console.error(error);
    return (
        <Result
            status="500"
            title="Erreur de chargement des départs"
            subTitle="Sorry, something went wrong."
            extra={<Button type="primary" href='#'>Actualiser la page</Button>}
        />)
}
