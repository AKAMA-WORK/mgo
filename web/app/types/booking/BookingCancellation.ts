import { PaymentMethod } from "../transaction/PaymentMethod"
import { BookingCancellationStatus } from "./BookingCancellationStatus"

export interface BookingCancellation{
    bookingCancellationId:string
    date:string
    amountReimbursed:number
    paymentId:string
    paymentMethod: PaymentMethod
    paymentDate:string
    status:BookingCancellationStatus
}