import { FormInstance } from "antd";
import { SearchBookingFilter } from "~/types/booking/SearchBookingFilter";
import { SearchBookingParams } from "~/types/booking/SearchBookingParams";
import { SearchBookingSort } from "~/types/booking/SearchBookingSort";
import { extractSearchParamsFromForm } from "./form-util";
import { parseSearchOffsetPaging } from "./offset-paging-util";
import { parseSearchSort } from "./sort-util";


export const BOOKING_DEFAULT_LIMIT=5;


export const parseSearchBookingParams = (params: Record<string, any>): SearchBookingParams => {

    return {
        filter: parseSearchBookingFilter(params?.filter),
        sort: parseSearchSort<SearchBookingSort>(params?.sort, 'bookingDate_desc'),
        paging: parseSearchOffsetPaging(params?.paging,BOOKING_DEFAULT_LIMIT),
    }
}

export const searchBookingParams2FormValues = (params: Record<string, any>): any => {

    return {
        ... (parseSearchBookingFilter(params?.filter) || {}),
        sort: parseSearchSort<SearchBookingSort>(params?.sort, 'bookingDate_desc'),
        paging: parseSearchOffsetPaging(params?.paging,BOOKING_DEFAULT_LIMIT)
    }
}


const parseSearchBookingFilter = (filter?: Record<string, any>): SearchBookingFilter | undefined => {
    if (filter) {
        return {
            from: filter.from ? filter.from: undefined,
            to: filter.to ? filter.to  : undefined,
            periodOfDays: filter.periodOfDays,
            vehiculeTypes: filter.vehiculeTypes ? filter.vehiculeTypes: undefined,
            organizations: filter.organizations ? filter.organizations : undefined,
            categories: filter.categories ? filter.categories : undefined,
        }
    }

    return undefined;
}





export const extractSearchBookingParamsFromForm = (
    form: FormInstance<any>
) => {
   
    return extractSearchParamsFromForm<SearchBookingFilter, SearchBookingSort>({
        form,
        rootParams:[],
        defaultSort: 'bookingDate_desc'
    })
} 