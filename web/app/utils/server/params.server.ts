import { getAuthenticatedUser } from "./auth.server";

export const getParamPersonId = async (request: Request, params: any) => {

    if (params?.personId) {

        if (params.personId === 'me') {
            const user = await getAuthenticatedUser(request);
            return user?.person?.personId;
        }

        return params.personId;

    }


    return undefined;

}