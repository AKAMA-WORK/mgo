import { LoaderFunction } from "@remix-run/node";
import { Outlet, useLoaderData } from "@remix-run/react";
import { Col, Layout } from "antd";

import BookingDetails from "~/components/booking/BookingDetails";
import DetailsSummary from "~/components/booking/DetailsSummary";
import RowLayout from "~/components/RowLayout";
import { Booking } from "~/types/booking/Booking";
import { getAuthenticatedToken } from "~/utils/server/auth.server";
import { searchBookings } from "~/utils/server/bookings.server";


export const loader: LoaderFunction = async ({ params, request }) => {

    const token = await getAuthenticatedToken(request);

    const bookingResult = await searchBookings({
        filter: {
            bookingId: params.bookingId
        }
    }, token?.accessToken);

    return bookingResult.items[0]
 
}



const ResetvationBooking = () => {
    const data = useLoaderData<Booking>();

    return (
        <div className="app-booking-validation">
            <div className="app-booking-validation-header-container" >
            <RowLayout className="app-booking-details-header" wrap={true}>
                <Col span={24}>
                    <DetailsSummary booking={data}/>
                </Col>
            </RowLayout>
            </div>
            <div className="app-booking-validation-content-container" >
            <RowLayout className="app-booking-validation-content" wrap={true}>
                <Col span={24}>
                <BookingDetails booking={data}/>
                </Col>
            </RowLayout>
            </div>

        </div>
    )

}


export default ResetvationBooking;