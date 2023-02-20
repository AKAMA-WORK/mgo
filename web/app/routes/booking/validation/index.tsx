import { useLoaderData } from "@remix-run/react";
import SeatsSelection, { SeatsSelectionDeparture } from "~/components/booking/SeatsSelection";
import { DeparturesBookingLoaderData } from "~/route-containers/departures-booking/departures-booking.server";


export { loader } from "~/route-containers/departures-booking/departures-booking.server"
export default function Validation() {
    const data = useLoaderData<DeparturesBookingLoaderData>();

    return (
        <SeatsSelection departures={data.departures} type={data.type} />
    );
}
