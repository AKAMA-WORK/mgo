import { useSearchParams } from "@remix-run/react";
import React from "react";
import { SelectSeatsMessageData } from "~/socket/selectSeats/SelectSeatsMessageData";
import { useSocketMessaging } from "~/socket/useSocketMessaging";
import { Booking } from "~/types/booking/Booking";
import { SearchBookingAggregate } from "~/types/booking/SearchBookingAggregate";
import { Departure } from "~/types/departure/Departure";
import { Paging } from "~/types/Paging";
import { SearchResult } from "~/types/SearchResult";
import DeparturesPagination from "../departures/DeparturesListPagination";
import BookingPreview from "./Bookings";
// import BookingsPagination from "./BookingsListPagination";

interface BookingssListProps {
    bookings?: SearchResult<Booking, SearchBookingAggregate>
    paging: Paging
    onPagingChange: (paging:Paging) => void
}

const BookingsList = ({ bookings, paging, onPagingChange }: BookingssListProps) => {
    return (
        <div className="app-departures-list">
            <div>
                {
                    bookings?.items.map((booking) => (<BookingPreview showConfirmButton seatsSelection={false} key={booking.bookingId} booking={booking} />))
                }
            </div>
            <DeparturesPagination onPagingChange={onPagingChange} paging={paging} />
        </div>
    )
}

export default BookingsList;