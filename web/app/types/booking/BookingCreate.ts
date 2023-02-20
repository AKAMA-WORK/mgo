import { PaymentMethod } from "../transaction/PaymentMethod"
import { BookingLineCreate } from "./BookingLineCreate"
import { BookingStatus } from "./BookingStatus"

export interface BookingCreate{
    paymentId?:string
    paymentMethod?: PaymentMethod
    clientIdPerson: string
    status: BookingStatus
    lines: BookingLineCreate[]
}