import { OffsetPaging } from '../OffsetPaging'
import { SearchBookingFilter } from './SearchBookingFilter'
import { SearchBookingSort } from './SearchBookingSort'


export interface SearchBookingParams {
    filter?: SearchBookingFilter
    paging: OffsetPaging
    sort?: SearchBookingSort
    aggregate?: boolean
}   