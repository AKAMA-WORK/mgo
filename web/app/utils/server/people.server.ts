import { Organization } from "~/types/Organization"
import { Person } from "~/types/person/Person"
import { executeApiSearchRequest } from "./api.server"



export const searchPeople = async (params: Record<string, any>, token?: string) => {
    return executeApiSearchRequest<any, Person>('/people', params, token)
}


