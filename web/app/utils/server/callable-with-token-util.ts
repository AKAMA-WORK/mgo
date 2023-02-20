import { authenticator, getAuthenticatedToken } from "./auth.server"

interface Callable<R> {
    (token?: {
        accessToken: string;
        refreshToken: string;
    } | null): Promise<R>
}


export const callWithAuthenticationTokenIfAuthenticated = async <R>(
    request: Request,
    cl: Callable<R>
) => {

    const token = await getAuthenticatedToken(request);
    try {
        return await cl(token);
    }
    catch (error) {
        if ((error as any).message === '401') {
            await authenticator.logout(request, {
                redirectTo: request.url,
            });
        }

        return cl(token);
    }



}