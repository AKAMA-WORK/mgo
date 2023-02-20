import type { ActionArgs, HeadersFunction, LoaderArgs } from "@remix-run/node";
import { json, redirect } from "@remix-run/node";
import { addSeats, deleteSeats, removeSeatSelectionItem, updateSeatSelectionItem } from "~/utils/seat-selection";
import { validateRedirect } from "~/utils/server/redirect.server";
import { getSession } from "~/utils/server/session.server";


export let headers: HeadersFunction = ({ actionHeaders }) => {
    return actionHeaders;
};

export async function action({ request }: ActionArgs) {
    let [body, session] = await Promise.all([
        request.text(),
        getSession(request),
    ]);

    let formData = new URLSearchParams(body);
    let redirectTo = validateRedirect(formData.get("redirect"), "/booking/validation");
    let action = formData.get("_action");

    try {
        let seats = session.getSeatsSelection();

        switch (action) {
            case "add-seat": {
                let departureIdStr = formData.get("departureId");
                let seatStr = formData.get("seat");
                if (!departureIdStr) {
                    break;
                }
                let seat = seatStr ? Number.parseInt(seatStr, 10) : undefined;
                let departureId = departureIdStr;
                seats = addSeats(seats, { departureId, seats: seat ? [seat] : [] });
                break;
            }

            case "add-seat-selection": {
                let departureIdStr = formData.get("departureId");
                if (!departureIdStr) {
                    break;
                }
                let departureId = departureIdStr;
                seats = addSeats(seats, { departureId, seats: [] });
                session.setSeatsSelection(seats);


                // TODO : Redirect
                return redirect('/booking/validation', {
                    headers: {
                        "Set-Cookie": await session.commitSession(),
                    },
                });

            }

            case "delete-seat-selection": {
                let departureIdStr = formData.get("departureId");
                if (!departureIdStr) {
                    break;
                }
                let departureId = departureIdStr
                seats = removeSeatSelectionItem(seats, departureId);
                break;
            }


            case "delete-seat": {
                let departureIdStr = formData.get("departureId");
                let seatStr = formData.get("seat");
                if (!departureIdStr || !seatStr) {
                    break;
                }
                let seat = Number.parseInt(seatStr, 10);
                let departureId =departureIdStr
                seats = deleteSeats(seats, { departureId, seats: [seat] });
                break;
            }


        }

        session.setSeatsSelection(seats);
        return redirect(redirectTo, {
            headers: {
                "Set-Cookie": await session.commitSession(),
            },
        });
    } catch (error) {
        console.error(error);
    }

    return redirect(redirectTo);
}
