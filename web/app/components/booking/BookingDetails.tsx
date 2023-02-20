import DeparturePreview from "../departures/Departure"
import Title from "../typography/Title"
import { Booking } from "~/types/booking/Booking";
import moment from "moment";


export interface BookingDetailsProps {
    booking: Booking
}


const renderDetails = (booking:Booking) => {
    const isOneWay = booking.lines.length === 1;

    if (isOneWay) {
        return <DeparturePreview showConfirmButton={false} seatsSelection={false} departure={booking.lines[0].departure} />
    }

    const isReturn = booking.lines.length === 2
        &&
        booking.lines[0].departure.from.cityId === booking.lines[1].departure.to.cityId
        && booking.lines[0].departure.to.cityId === booking.lines[1].departure.from.cityId

    if (isReturn) {

        const sortedLines = booking.lines.sort((a, b) => {
            return moment.utc(a.departure.date).isBefore(moment.utc(b.departure.date)) ? -1 : 1
        })


        return (
            <>
                <div className="app-seats-selection-title">
                    <Title level={3} align="left" color="color2">voyage Aller</Title>
                </div>

                <div className="app-seats-selection-section">
                    <Title level={3} color="color2">voyage aller</Title>
                    <DeparturePreview showConfirmButton={false} seatsSelection={false} departure={sortedLines[0].departure} />
                </div>

                <div className="app-seats-selection-title">
                    <Title level={3} align="left" color="color2">voyage retour</Title>
                </div>
                <div className="app-seats-selection-section">
                    <Title level={3} color="color2">Voyage retour</Title>
                    <DeparturePreview showConfirmButton={false} seatsSelection={false} departure={sortedLines[1].departure} />
                </div>
            </>
        )

    }


    const sortedLines = booking.lines.sort((a, b) => {
        return moment.utc(a.departure.date).isBefore(moment.utc(b.departure.date)) ? -1 : 1
    })

    return sortedLines.map((line) => (
        <div className="app-seats-selection-section">
            <DeparturePreview showConfirmButton={false} seatsSelection={false} departure={line.departure} />
        </div>

    ))

}



const BookingDetails = ({ booking }: BookingDetailsProps) => {

    return (
        <div className="app-seats-selection">
            <div className="app-seats-selection-title">
                <Title level={2} align="center">Détails du voyage</Title>
            </div>
            {renderDetails(booking)}
            {/*<DetailsPayment bookings={bookings}/>*/}
        </div>
    )

}

export default BookingDetails;