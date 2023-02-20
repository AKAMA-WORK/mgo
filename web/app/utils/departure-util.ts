import { FormInstance } from "antd";
import moment from "moment";
import { SearchDepartureFilter } from "~/types/departure/SearchDepartureFilter";
import { SearchDepartureParams } from "~/types/departure/SearchDepartureParams";
import { SearchDepartureSort } from "~/types/departure/SearchDepartureSort";
import { extractSearchParamsFromForm } from "./form-util";
import { parseSearchOffsetPaging } from "./offset-paging-util";
import { parseSearchSort } from "./sort-util";

export const DEPARTURE_DEFAULT_LIMIT=5;


export const parseSearchDepartureParams = (params: Record<string, any>): SearchDepartureParams => {

    return {
        filter: parseSearchDepartureFilter(params?.filter),
        sort: parseSearchSort<SearchDepartureSort>(params?.sort, 'price_asc'),
        paging: parseSearchOffsetPaging(params?.paging,DEPARTURE_DEFAULT_LIMIT),
        seatsSelection: params.seatsSelection === 'true',
    }
}

export const searchDepartureParams2FormValues = (params: Record<string, any>): any => {

    return {
        ... (parseSearchDepartureFilter(params?.filter) || {}),
        seatsSelection: params.seatsSelection === 'true',
        sort: parseSearchSort<SearchDepartureSort>(params?.sort, 'price_asc'),
        ... (parseSearchOffsetPaging(params?.paging,DEPARTURE_DEFAULT_LIMIT) || {})
    }
}


const parseSearchDepartureFilter = (filter?: Record<string, any>): SearchDepartureFilter | undefined => {
    if (filter) {
        return {
            from: filter.from ? filter.from : undefined,
            to: filter.to ? filter.to : undefined,
            transportation: filter.transportation,
            type: filter.type,
            outwardDate: filter.outwardDate ? moment.utc(filter.outwardDate, 'YYYY-MM-DD') : undefined,
            returnDate: filter.returnDate ? moment.utc(filter.returnDate, 'YYYY-MM-DD') : undefined,
            seats: filter.seats ? parseInt(`${filter.seats}`, 10) : undefined,
            periodOfDays: filter.periodOfDays,
            vehiculeTypes: filter.vehiculeTypes ? filter.vehiculeTypes: undefined,
            organizations: filter.organizations ? filter.organizations : undefined

        }
    }

    return undefined;
}





export const extractSearchDepartureParamsFromForm = (
    form: FormInstance<any>
) => {
    /*const from = form.getFieldValue('from');
    const to = form.getFieldValue('to');
    const transportation = form.getFieldValue('transportation');
    const seats = form.getFieldValue('seats');
    const type = form.getFieldValue('type');
    const outwardDate = form.getFieldValue('outwardDate');
    const returnDate = form.getFieldValue('returnDate');
    const periodOfDay = form.getFieldValue('periodOfDay');
    const vehiculeTypes = form.getFieldValue('vehiculeTypes');
    const organizations = form.getFieldValue('organizations');

    const limit = form.getFieldValue('limit');
    const offset = form.getFieldValue('offset');

    const sort = form.getFieldValue('sort') || 'price_desc';
    const seatsSelection = form.getFieldValue('seatsSelection');



    const isReturn = form.getFieldValue('type') === 'return';

    let filter = {} as SearchDepartureFilter
    let paging = {} as OffsetPaging



    if (offset) {
        paging = {
            ...paging,
            offset
        }
    }

    if (limit) {
        paging = {
            ...paging,
            limit
        }
    }






    if (from) {
        filter = {
            ...filter,
            from,
        }
    }

    if (to) {

        filter = {
            ...filter,
            to
        }
    }

    if (transportation) {
        filter = {
            ...filter,
            transportation
        }
    }

    if (seats) {
        filter = {
            ...filter,
            seats
        }
    }

    if (type) {
        filter = {
            ...filter,
            type

        }

        if (outwardDate) {
            filter = {
                ...filter,
                outwardDate: outwardDate.format('YYYY-MM-DD')

            }
        }

        if (returnDate && isReturn) {
            filter = {
                ...filter,
                returnDate: returnDate.format('YYYY-MM-DD')

            }
        }



    }

    if (periodOfDay) {
        filter = {
            ...filter,
            periodOfDay,
        }
    }

    if (vehiculeTypes) {
        filter = {
            ...filter,
            vehiculeTypes,
        }
    }

    if (organizations) {
        filter = {
            ...filter,
            organizations,
        }
    }



    let params = {
        filter
    } as any

    if(sort){
        params={
            ...params,
            sort
        }
    }

    if(paging){
        params={
            ...params,
            paging
        }
    }

    params={
        ...params,
        seatsSelection
    }

    const searchQuery = toURLSearchParams(nestedObjectKeysToUrlSquareBrackets(params));

    return searchQuery;*/

    return extractSearchParamsFromForm<SearchDepartureFilter, SearchDepartureSort>({
        form,
        rootParams: ["seatsSelection"],
        defaultSort: 'price_asc'
    })
} 