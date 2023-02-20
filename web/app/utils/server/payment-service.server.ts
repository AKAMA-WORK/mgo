import fetch from "@remix-run/web-fetch";
import { Payment } from "~/types/transaction/Payment";
import { Transaction } from "~/types/transaction/Transaction";


const url = process.env.PAYMENT_SERVICE_URL;

const executeRequest = async (path: string, token?: string, init?: RequestInit) => {
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
export const getTransaction = async (id: string, token?: string): Promise<Transaction> => {
    return executeRequest(`/transactions/${id}`, token, { method: 'GET' });
}


export const pay = async ({ payment, wait }: { payment: Payment, wait: boolean }, token?: string): Promise<Transaction> => {
    return executeRequest(`/transactions/pay?wait=${wait}`, token, {
        method: 'POST', body: JSON.stringify(payment), headers: {
            'Content-Type': 'application/json'
        }
    });
}