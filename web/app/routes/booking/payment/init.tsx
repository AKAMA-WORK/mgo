import { ActionFunction, LoaderFunction, redirect } from "@remix-run/node";
import { getAuthenticatedToken, getAuthenticatedUser } from "~/utils/server/auth.server";
import { createBooking } from "~/utils/server/bookings.server";
import { searchPeople } from "~/utils/server/people.server";
import { getSession } from "~/utils/server/session.server";

export const action: ActionFunction = async ({ request }) => {
    const session = await getSession(request);
    const seatSelections = session.getSeatsSelection();
    const [user, token] = await Promise.all([getAuthenticatedUser(request),
    getAuthenticatedToken(request)
    ]);

    const peopleResult = await searchPeople({
        filter: {
            userId: user?.userId
        }
    }, token?.accessToken);

    const personId = peopleResult.items[0].personId;

    const createdBooking = await createBooking({
        clientIdPerson: personId,
        status: 'INITIATED',
        lines: seatSelections
    }, token?.accessToken);


    if(createdBooking.bookingId){
        return redirect(`/booking/validation/${createdBooking.bookingId}`);
    }


    return(`/booking/validation`)
}

export const loader = () => redirect('/', {status: 404})


export default function MarkRead() {
    return <div>Oops... You should not see this.</div>
  }
  