import { useLoaderData } from "@remix-run/react";
import BookingPayment from "~/components/booking/Payment";
import { DeparturesBookingLoaderData } from "~/route-containers/departures-booking/departures-booking.server";
import { BookingLineMinimal } from "~/types/booking/BookingLineMinimal";

export { loader } from "~/route-containers/departures-booking/departures-booking.server"

export default function Payment() {
    const data = useLoaderData<DeparturesBookingLoaderData>();

    const bookings = data.departures.map((departure, index) => {

        const seats = departure.departure.seatSelection?.seats || [];
        const amount = departure.departure.price * seats.length;
        return {
            departure: departure.departure,
            bookingId: index,
            seats: departure.departure.seatSelection?.seats || [],
            price: departure.departure.price,
            amount

        } as BookingLineMinimal
    })
    return (
        <BookingPayment lines={bookings} waitConfirmIn={100}/>
    );
}
