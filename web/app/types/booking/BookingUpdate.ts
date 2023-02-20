import { BookingStatus } from "./BookingStatus"

export interface BookingUpdate{
    paymentId?:string
    paymentMethod?:string
    status:BookingStatus
}