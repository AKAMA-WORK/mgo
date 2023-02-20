import { Booking } from "~/types/booking/Booking"
import { BookingCreate } from "~/types/booking/BookingCreate"
import { BookingUpdate } from "~/types/booking/BookingUpdate"
import { SearchBookingFilter } from "~/types/booking/SearchBookingFilter"
import { SearchResult } from "~/types/SearchResult"
import { executeApiRequest, executeApiSearchRequest } from "./api.server"

export const createBooking = (
    booking: BookingCreate,
    token?: string
): Promise<Booking> => {

    return executeApiRequest('/bookings', token, {
        method: 'POST',
        headers: {
            'content-type': 'application/json',
            'accept': 'application/json'
        },
        body: JSON.stringify(booking)
    })

}


export const updateBooking = (
    bookingId: string,
    booking: BookingUpdate,
    token?: string
): Promise<Booking> => {

    return executeApiRequest(`/bookings/${bookingId}`, token, {
        method: 'PUT',
        headers: {
            'content-type': 'application/json',
            'accept': 'application/json'
        },
        body: JSON.stringify(booking)
    })

}



export const deleteBooking = (
    bookingId: string,
    token?: string
): Promise<void> => {
    return executeApiRequest(`/bookings/${bookingId}`, token, {
        method: 'DELETE',
    })

}


export const getBooking = (
    bookingId: string,
    token?: string
): Promise<Booking> => {
    return executeApiRequest(`/bookings/${bookingId}`, token, {
        headers: {
            'accept': 'application/json'
        },
        method: 'GET',
    })

}


export const searchBookings = async (params: Record<string, any>, token?: string): Promise<SearchResult<Booking>> => {
    return executeApiSearchRequest<SearchBookingFilter,Booking>('/bookings',params,token,{
        organizations:{
            op:'in',
            field:'organizationId'
        },
        vehiculeTypes:{
            op:'in',
            field:'vehicleTypeId'
            
        }
    })
}