import { LoaderFunction } from "@remix-run/node";
import  { SeatsSelectionDeparture } from "~/components/booking/SeatsSelection";
import { callWithAuthenticationTokenIfAuthenticated } from "~/utils/server/callable-with-token-util";
import { searchDepartures } from "~/utils/server/departures.server";
import { getSession } from "~/utils/server/session.server";

export interface DeparturesBookingLoaderData {
    departures: SeatsSelectionDeparture[]
    type: 'one-way' | 'return' | 'multi-stop'
}


export const loader: LoaderFunction = async ({ params, request }) => {
    const session = await getSession(request);


    return callWithAuthenticationTokenIfAuthenticated<DeparturesBookingLoaderData>(request, async (token) => {

        const seatSelections = session.getSeatsSelection()
        const departuresResult = await searchDepartures({
            filter: {
                departureId: {
                    in: seatSelections.map(selection => selection.departureId)
                }
            }
        }, token?.accessToken);




        return {
            type: 'one-way',
            departures: (departuresResult.items || []).map((item) => ({ departure: { ...item, seatSelection: seatSelections.find(s => s.departureId === item.departureId) } }))

        }

    });


}
