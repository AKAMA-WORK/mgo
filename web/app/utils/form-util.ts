import { FormInstance } from "antd";
import moment from "moment";
import { OffsetPaging } from "~/types/OffsetPaging";
import { nestedObjectKeysToUrlSquareBrackets, toURLSearchParams } from "./url-utils";


export type SearchParamMapping<T> = {
    [K in keyof T]: {
        getValue: (form: FormInstance<any>) => any
    }
}


const getValue = <T>(
    key: string,
    values: any,
    form: FormInstance<any>,
    mapping?: SearchParamMapping<T>
) => {

    const valueMapping = (mapping as any)?.[key];
    let value = valueMapping ? valueMapping.getValue(form) : values[key];
    if (!value) {
        return undefined;
    }

    if (moment.isMoment(value)) {
        value = value.format('YYYY-MM-DD')
    }

    return value;
}

const NO_FILTER = ['limit', 'offset', 'sort'];
export const extractSearchParamsFromForm = <F,S>(
    {
        form,
        rootParams= [],
        mapping,
        defaultSort
    }:{
        form: FormInstance<any>,
        rootParams: string[] ,
        mapping?: SearchParamMapping<F>,
        defaultSort?: S 
    }
   
) => {

    const values = form.getFieldsValue();
    const filter = Object.keys(values).reduce((prev, key) => {
        if (!NO_FILTER.includes(key) && !rootParams.includes(key)) {
            const value = getValue(key, values, form, mapping);

            return value ? {
                ...prev,
                [key]: value
            } : prev

        }

        return prev;

    }, ({} as F))

    const limit = form.getFieldValue('limit');
    const offset = form.getFieldValue('offset');
    const sort = form.getFieldValue('sort') || defaultSort;


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


    let params = {
        filter
    } as any

    if (sort) {
        params = {
            ...params,
            sort
        }
    }

    if (paging) {
        params = {
            ...params,
            paging
        }
    }



    rootParams.forEach(key => {
        if (values[key]) {

            const value = getValue(key, values, form, mapping);

            params = {
                ...params,
                [key]: value
            }
        }
    })

    const searchQuery = toURLSearchParams(nestedObjectKeysToUrlSquareBrackets(params));
    return searchQuery;
} 