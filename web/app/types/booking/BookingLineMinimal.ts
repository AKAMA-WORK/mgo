import { Departure } from "../departure/Departure"

export interface BookingLineMinimal {
    bookingId: string
    departure: Departure
    seats: number[]
    price: number
    amount: number
}