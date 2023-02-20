import { Departure } from "../departure/Departure"
import { Person } from "../person/Person"
import { PaymentMethod } from "../transaction/PaymentMethod"
import { BookingCancellation } from "./BookingCancellation"
import { BookingLine } from "./BookingLine"
import { BookingStatus } from "./BookingStatus"

export interface Booking {
    bookingId:string
    date:string
    seats:number[]
    price:number
    amount: number
    paymentMethod?:PaymentMethod
    paymentId?:string
    paymentDate:string
    description?:string
    status:BookingStatus
    waitConfirmUntil:string
    waitConfirmIn:number
    client: Person
    lines:BookingLine[]
    cancellation?: BookingCancellation

}