import { PaymentMethod } from "../transaction/PaymentMethod"

export interface BookingLineExtraLuggage {
    bookingLineExtraLuggageId:string
    weight:number
    amount:number
    paymentId:number
    paymentMethod:PaymentMethod
    paymentDate:string
}