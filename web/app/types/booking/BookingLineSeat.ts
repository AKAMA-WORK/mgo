import { BookingCancellation } from "./BookingCancellation"
import { BookingLineSeatStatus } from "./BookingLineSeatStatus"

export interface BookingLineSeat{
    bookingLineSeatId:string
    seatNumber:number
    status:BookingLineSeatStatus
    cancellation?:BookingCancellation
}