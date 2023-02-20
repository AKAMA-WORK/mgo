import { City } from "~/types/City";
import { executeApiRequest, executeApiSearchRequest } from "./api.server";

export const searchCities = async(token?: string) => {
    return executeApiSearchRequest<any,City>('/cities',undefined,token)
}

