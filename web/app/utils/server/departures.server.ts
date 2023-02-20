import { Moment } from "moment";
import { Departure } from "~/types/departure/Departure";
import { SearchDepartureFilter } from "~/types/departure/SearchDepartureFilter";
import { SearchResult } from "~/types/SearchResult";
import { convertSort, executeApiRequest, executeApiSearchRequest } from "./api.server";


/*const convertDepartureFilter = (filter?: SearchDepartureFilter) => {
  if (!filter) {
      return filter
  }

  let newFilter = {} as any
  if (filter.from) {
      newFilter = {
          ...newFilter,
          from: {
              eq: filter.from
          }
      };
  }

  if (filter.to) {
      newFilter = {
          ...newFilter,
          to: {
              eq: filter.to
          }
      };
  }

  if (filter.seats) {
      newFilter = {
          ...newFilter,
          availableSeats: {
              gte: filter.seats
          }
      };
  }

  if (filter.outwardDate) {
      newFilter = {
          ...newFilter,
          date: {
              eq: filter.outwardDate
          }
      };
  }



  return newFilter

}*/


export const searchDepartures = async (params: Record<string, any>, token?: string): Promise<SearchResult<Departure>> => {

    /*if (params?.sort) {
        params = {
            ...params,
            sort: convertSort(params.sort)
        }
    }
  
    if (params?.filter) {
        params = {
            ...params,
            filter: convertDepartureFilter(params.filter)
        }
    }
  
    const searchQuery = toURLSearchParams(nestedObjectKeysToUrlSquareBrackets(params))?.toString();
    return executeApiRequest(`/departures${searchQuery ? `?${searchQuery}` : ''}`, token, { method: 'GET' });*/


    return executeApiSearchRequest<SearchDepartureFilter, Departure>('/departures', params, token, {
        'outwardDate': {
            field: 'date',
            format: (date: Moment) => {
                return date ? date.format('YYYY-MM-DD') : undefined
            }
        },
        'seats': {
            field: 'availableSeats',
            op: 'gte'
        },
        organizations: {
            op: 'in',
            field: 'organizationId'
        },
        vehiculeTypes: {
            op: 'in',
            field: 'vehicleTypeId'

        }
    })

}
