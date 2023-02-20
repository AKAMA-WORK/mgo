import { Organization } from "~/types/Organization"
import { executeApiSearchRequest } from "./api.server"



export const searchOrganizations = async (token?: string) => {
    return executeApiSearchRequest<any, Organization>('/organizations', undefined, token)
}


