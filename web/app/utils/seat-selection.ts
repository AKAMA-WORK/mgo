import { SeatSelectionItem } from "~/types/SeatSelectionItem";


export const addSeats = (seats: SeatSelectionItem[],
    newSeat: SeatSelectionItem
) => {
    let added = false;
    for (let seat of seats) {
        if (seat.departureId === newSeat.departureId) {
            if (newSeat.seats) {
                seat.seats = [...(seat.seats || []), ...newSeat.seats]
            }
            added = true;
            break;
        }
    }
    if (!added) {
        seats.push(newSeat);
    }

    return seats;
}

export const deleteSeats = (seats: SeatSelectionItem[],
    newSeat: Omit<SeatSelectionItem, 'quantity'>
) => {
    for (let seat of seats) {
        if (seat.departureId === newSeat.departureId) {
            seat.seats = seat.seats?.filter(s => !newSeat.seats?.includes(s));
            break;
        }
    }

    return seats;
}


export function updateSeatSelectionItem(
    seats: SeatSelectionItem[],
    updatedSeat: SeatSelectionItem
) {
    let updated = false;
    for (let item of seats) {
        if (item.departureId === updatedSeat.departureId) {

            item.seats = updatedSeat.seats
            updated = true;
            break;
        }
    }
    if (!updated) {
        seats.push(updatedSeat);
    }
    return seats;
}

export function removeSeatSelectionItem(seats: SeatSelectionItem[], departureId: string) {
    return seats.filter((item) => item.departureId !== departureId);
}