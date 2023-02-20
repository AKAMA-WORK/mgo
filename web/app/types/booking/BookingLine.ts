import { Departure } from "../departure/Departure"
import { BookingCancellation } from "./BookingCancellation"
import { BookingLineExtraLuggage } from "./BookingLineExtraLuggage"
import { BookingLineLuggage } from "./BookingLineLuggage"
import { BookingLineSeat } from "./BookingLineSeat"
import { BookingStatus } from "./BookingStatus"

export interface BookingLine{
    bookingLineId:string
    price:number
    amount:number
    description?:string
    status:BookingStatus
    departure: Departure
    boardingDate:string
    cancellation?:BookingCancellation
    luggages: BookingLineLuggage[]
    extraLuggages: BookingLineExtraLuggage[]
    seats: BookingLineSeat[]


}