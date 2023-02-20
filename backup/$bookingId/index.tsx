import { LoaderFunction } from "@remix-run/node";
import { useLoaderData } from "@remix-run/react";
import BookingDetails  from "~/components/booking/BookingDetails";
import { Booking } from "~/types/booking/Booking";
import { searchBookings } from "~/utils/server/bookings.server";
import { callWithAuthenticationTokenIfAuthenticated } from "~/utils/server/callable-with-token-util";


export const loader: LoaderFunction = async ({ params, request }) => {

    return callWithAuthenticationTokenIfAuthenticated<Booking>(request, async (token) => {

        const bookingResult = await searchBookings({
            filter: {
                bookingId: params.bookingId
            }
        }, token?.accessToken);




        return bookingResult.items[0]
        

    });


}




export default function Validation() {
    const data = useLoaderData<Booking>();

    return (
        <BookingDetails booking={data}/>
    );
}
