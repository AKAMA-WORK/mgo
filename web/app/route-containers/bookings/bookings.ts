import { LoaderFunction } from "@remix-run/node";
import qs from "qs";
import { Booking } from "~/types/booking/Booking";
import { SearchBookingAggregate } from "~/types/booking/SearchBookingAggregate";
import { Paging } from "~/types/Paging";
import { SearchResult } from "~/types/SearchResult";
import { parseSearchBookingParams } from "~/utils/booking-util";
import { computePaging } from "~/utils/offset-paging-util";
import { getAuthenticatedToken } from "~/utils/server/auth.server";
import { searchBookings } from "~/utils/server/bookings.server";
import { getParamPersonId } from "~/utils/server/params.server";


export interface SearchBookingLoaderData {
    bookings: SearchResult<Booking, SearchBookingAggregate>
    query: {
        filter?: Record<string, any>
    },
    paging: Paging
}







export const createSearchBookingLoader = (): LoaderFunction => {


    return async ({ request, params }) => {
        const url = new URL(request.url);
        const query = qs.parse(url.search?.substring(1)) as any;

        const token = await getAuthenticatedToken(request);
        const clientIdPerson = await getParamPersonId(request, params);


        const bookingParams = parseSearchBookingParams(query);
        const bookings = await searchBookings({
            ...(bookingParams || {}),
            filter: {
                ...(bookingParams?.filter || {}),
                clientIdPerson,
            }

        }, token?.accessToken);


        const paging = computePaging({ ...bookingParams.paging, totalCount: bookings?.totalCount })

        return {
            bookings,
            query,
            paging

        }
    }

}