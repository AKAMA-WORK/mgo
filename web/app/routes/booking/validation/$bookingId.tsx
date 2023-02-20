import { LoaderFunction } from "@remix-run/node";
import { useLoaderData } from "@remix-run/react";
import BookingPayment from "~/components/booking/Payment";
import { BookingLineMinimal } from "~/types/booking/BookingLineMinimal";
import { getAuthenticatedToken } from "~/utils/server/auth.server";
import invariant from "tiny-invariant";
import { getBooking } from "~/utils/server/bookings.server";


interface LoaderData {
    lines: BookingLineMinimal[];
    waitConfirmIn: number
}

export const loader: LoaderFunction = async ({ params, request }): Promise<LoaderData> => {
    const token = await getAuthenticatedToken(request);
    const bookingId = params.bookingId

    invariant(bookingId, "Expected bookingId");

    return getBooking(bookingId, token?.accessToken)
        .then(booking => {
            const lines = booking.lines.map(line => {
                return {
                    bookingId: booking.bookingId,
                    departure: line.departure,
                    seats: line.seats.map(lineSeat => lineSeat.seatNumber),
                    price: line.price,
                    amount: line.amount
                }
            });
            return { lines, waitConfirmIn: booking.waitConfirmIn };

        });



}

export default function Payment() {
    const data = useLoaderData<LoaderData>();

    return (
        <BookingPayment lines={data?.lines || []} waitConfirmIn={data?.waitConfirmIn || 0} />
    );
}
