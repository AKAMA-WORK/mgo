import { OffsetPaging } from './../OffsetPaging'
import { SearchDepartureFilter } from './SearchDepartureFilter'
import { SearchDepartureSort } from './SearchDepartureSort'


export interface SearchDepartureParams {
    filter?: SearchDepartureFilter
    paging: OffsetPaging
    sort?: SearchDepartureSort
    aggregate?: boolean
    seatsSelection?: boolean
}   