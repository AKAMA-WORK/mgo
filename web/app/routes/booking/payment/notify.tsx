import { LoaderFunction, redirect } from "@remix-run/node";
import { TransactionStatus } from "~/types/transaction/TransactionStatus";
import { getAuthenticatedToken } from "~/utils/server/auth.server";
import { deleteBooking, updateBooking } from "~/utils/server/bookings.server";
import { getTransaction } from "~/utils/server/payment-service.server";

export const loader: LoaderFunction = async ({ params, request }) => {
    const token = await getAuthenticatedToken(request);
    const url = new URL(request.url);


    const transactionId =  url.searchParams.get("id") as string;
    const status =  url.searchParams.get("status") as TransactionStatus
    const bookingId =  url.searchParams.get("external_transaction_id")  as string;

    if (status === 'FAILED') {
        await deleteBooking(bookingId, token?.accessToken)
    }


    if (status === 'COMPLETED') {
        const transaction = await getTransaction(transactionId, token?.accessToken);

        await updateBooking(bookingId, {
            paymentId: transaction.id,
            paymentMethod: transaction.method,
            status: 'CONFIRMED'
        },token?.accessToken);
    }



    return redirect('/');
}