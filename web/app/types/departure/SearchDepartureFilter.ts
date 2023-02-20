import { Moment } from 'moment'


export interface SearchDepartureFilter {
    from?: string
    to?: string
    transportation?: string
    seats?: number
    type?: string
    outwardDate?: Moment
    returnDate?: Moment
    periodOfDays?:string[]
    vehiculeTypes?:string[]
    organizations?:string[]

}
