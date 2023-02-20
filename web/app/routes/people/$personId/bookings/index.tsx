import { useCatch, useLoaderData, useParams, useSubmit } from "@remix-run/react";
import { LoaderFunction } from "@remix-run/node";
import BookingsList from "~/components/bookings/BookingsList";
import { Button, Result } from "antd";
import { createSearchBookingLoader, SearchBookingLoaderData } from "~/route-containers/bookings/bookings";
import { Paging } from "~/types/Paging";
import { nestedObjectKeysToUrlSquareBrackets, toURLSearchParams } from "~/utils/url-utils";


export const loader: LoaderFunction = createSearchBookingLoader();

export default function Bookings() {
    const data = useLoaderData<SearchBookingLoaderData>();
    const params = useParams();
    const submit = useSubmit();

    const handleChangePaging = (paging:Paging) => {
        submit(toURLSearchParams(nestedObjectKeysToUrlSquareBrackets({...(data.query || {}),paging: {limit: paging.limit,offset:paging.offset }})), { method: 'get', action: `/people/${params.personId}/bookings` })
    }

    return (
        <BookingsList onPagingChange={handleChangePaging} paging={data.paging} bookings={data.bookings} />
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