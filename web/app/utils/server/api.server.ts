import fetch from "@remix-run/web-fetch";
import { SearchResult } from "~/types/SearchResult";
import { nestedObjectKeysToUrlSquareBrackets, toURLSearchParams } from "../url-utils";


const url = process.env.API_URL;

export const executeApiRequest = async (path: string, token?: string, init?: RequestInit) => {
    let requestInit: RequestInit | undefined = init;

    if (token) {
        if (requestInit) {
            requestInit = {
                ...requestInit,
                headers: requestInit.headers ? {
                    ...requestInit.headers,
                    Authorization: `Bearer ${token}`
                } : {
                    Authorization: `Bearer ${token}`
                }
            }
        }
        else {
            requestInit = {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            }
        }

    }


    return fetch(`${url}${path}`, requestInit).then(response => {
        if (!response.ok) {
            throw Error(`${response.status}`)
        }
        return response.json();
    });

}


// exemple : price_desc => price:'DESC'
export const convertSort = (sort: string) => {
    const parts = sort.split('_');


    return {
        [parts[0]]: parts[1].toUpperCase()
    }
}


export type MappingFilter<T> = {
    [K in keyof T]?: {
        op?: string
        field?: string
        format?: (value: any) => any
    }
}
export const convertFilter = <F>(filter?: Record<string, any>, mapping?: MappingFilter<F>) => {
    if (!filter) {
        return filter
    }

    return Object.keys(filter).reduce((converted, key) => {
        const value = filter[key];

        if (value) {
            const fieldMapping: any = mapping ? (mapping as any)[key] : undefined;
            const field: string = fieldMapping?.field || key
            const op = fieldMapping?.op || (Array.isArray(value) ? 'in' : 'eq');

            const formattedValue = fieldMapping?.format ? fieldMapping.format(value) : value

            return formattedValue ? {
                ...converted,
                [field]: {
                    [op]: formattedValue

                }
            } : converted

        }

        return converted;


    }, ({}))


}

export const executeApiSearchRequest = <F, R>(path: string, params?: Record<string, any>, token?: string, mapping?: MappingFilter<F>): Promise<SearchResult<R>> => {

    if (params?.sort) {
        params = {
            ...params,
            sort: convertSort(params.sort)
        }
    }

    if (params?.filter) {
        params = {
            ...params,
            filter: convertFilter(params.filter, mapping)
        }
    }


    const searchQuery = toURLSearchParams(nestedObjectKeysToUrlSquareBrackets(params))?.toString();
    return executeApiRequest(`${path}${searchQuery ? `?${searchQuery}` : ''}`, token, { method: 'GET' });
}

