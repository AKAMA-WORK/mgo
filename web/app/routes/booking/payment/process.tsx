import * as React from 'react'
import type { ActionFunction } from '@remix-run/node'
import { json, redirect } from '@remix-run/node'
import { i18nCookie } from '~/utils/server/cookie.server';
import { PaymentMethod } from '~/types/transaction/PaymentMethod';
import invariant from "tiny-invariant";
import { getAuthenticatedToken } from '~/utils/server/auth.server';
import { getDomainUrl } from '~/utils/server/domain.server';
import { pay } from '~/utils/server/payment-service.server';
import { getBooking } from '~/utils/server/bookings.server';

export const action: ActionFunction = async ({ request }) => {
    const bodyParams = await request.formData();
    const token = await getAuthenticatedToken(request);
    const baseUrl = getDomainUrl(request);

    const paymentMethod = bodyParams.get('paymentMethod') as PaymentMethod;
    const bookingId = bodyParams.get('bookingId')?.toString();

    invariant(paymentMethod, 'Required payment method');
    invariant(bookingId, 'Required booking id');


    const booking = await getBooking(bookingId, token?.accessToken);

    if (paymentMethod === 'CREDIT_CARD') {

        const transaction = await pay({
            wait: false,
            payment: {
                externalTransactionId: `${booking.bookingId}`,
                amount: booking.amount,
                label: 'BOOKING',
                currency: 'MGA',
                method: paymentMethod,
                redirectUrl: `${baseUrl}/booking/payment/notify`
            }
        }, token?.accessToken);

        if (transaction.checkoutUrl) {
            return redirect(transaction.checkoutUrl);
        }

    }


    return redirect("/");
}

export const loader = () => redirect('/', { status: 404 })

export default function MarkRead() {
    return <div>Oops... You should not see this.</div>
}
