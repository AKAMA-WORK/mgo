import { useFetcher } from "@remix-run/react"
/* eslint-disable no-sparse-arrays */
import { Button, Divider, Popconfirm, Table, Typography } from "antd"
import { ColumnsType } from "antd/lib/table"
import moment from "moment"
import { BookingLineMinimal } from "~/types/booking/BookingLineMinimal"
import { Departure } from "~/types/departure/Departure"
import { PaymentMethod } from "~/types/transaction/PaymentMethod"
import { formatAmount } from "~/utils/amount-util"
import DeparturePreview from "../departures/Departure"
import PaymentMethodGroup from "../PaymentMethodGroup/PaymentMethodGroup"
import Title from "../typography/Title"
import H2 from "../typography/Title"
import PaymentTimer from "./PaymentTimer"






export interface BookingPaymentProps {
    lines: BookingLineMinimal[]
    waitConfirmIn: number
}


const BookingPayment = ({ lines, waitConfirmIn }: BookingPaymentProps) => {

    const fetcher = useFetcher();


    const handleDelete = (key: React.Key) => {
    };

    const renderTotal = (record: BookingLineMinimal) => {
        return <div className="app-booking-payment-recaputilatif-total"><span className="app-booking-payment-recaputilatif-total-title">Total : </span> <span className="app-booking-payment-recaputilatif-total-value">{formatAmount(record.amount)} Ar</span></div>
    }

    const renderDescription = (label: string, value: string) => {

        return (
            <div className="app-booking-payment-recaputilatif-description">
                <div className="app-booking-payment-recaputilatif-description-label">{label}</div>
                <div className="app-booking-payment-recaputilatif-description-value">{value}</div>
            </div>
        )

    }

    const columns: ColumnsType<BookingLineMinimal> = [
        {
            title: 'Détail',
            render: (record: BookingLineMinimal) => {

                if (record.bookingId === undefined) {
                    return renderTotal(record)
                }
                return <>

                    {renderDescription(record.departure.organization?.name, `${record.departure.from?.name} - ${record.departure.to?.name} / ${moment(record.departure.date).format('dddd DD MMMM YYYY')} à ${record.departure.hour}`)}
                    <Divider />
                    {renderDescription('P.U.', `${formatAmount(record.price)} Ar`)}
                    <Divider />
                    {renderDescription('Qté', record.seats.length.toString())}
                    <Divider />
                    {renderDescription('Montant', `${formatAmount(record.amount)} Ar`)}
                </>

            },
            responsive: ['xs']
        },
        {
            title: 'Descriptif',
            render: (record: BookingLineMinimal) => {
                return record.bookingId === undefined ? (
                    renderTotal(record)
                ) :
                    renderDescription(record.departure.organization?.name, `${record.departure.from?.name} - ${record.departure.to?.name} / ${moment(record.departure.date).format('dddd DD MMMM YYYY')}${record.departure.hour ? ` à ${record.departure.hour}` : ''}`)
            },
            responsive: ['sm'],
            onCell: (record, index) => {
                return record.bookingId === undefined ? { colSpan: 4 } : { colSpan: 1 }

            }
        },
        {
            align: 'right',
            title: 'P.U.',
            render: (record) => {
                return `${formatAmount(record.price)} Ar`
            },

            onCell: (record, index) => {
                return record.bookingId === undefined ? { colSpan: 0 } : { colSpan: 1 }

            },
            responsive: ['sm']
        },
        {
            align: 'right',
            title: 'Qté',
            render: (record) => {
                return record?.seats?.length
            },
            onCell: (record, index) => {
                return record.bookingId === undefined ? { colSpan: 0 } : { colSpan: 1 }

            },
            responsive: ['sm']
        },
        ,
        {
            align: 'right',
            title: 'Montant',
            render: (record: BookingLineMinimal) => {
                return `${formatAmount(record.amount)} Ar`
            },
            onCell: (record, index) => {
                return record.bookingId === undefined ? { colSpan: 0 } : { colSpan: 1 }

            },
            responsive: ['sm']
        },

        /*{
            title: '',
            width: 100,
            render: (record) => {
                return (
                    <Popconfirm title="Êtes-vous sûr ?" onConfirm={() => handleDelete(record.key)}>
                        <a>Supprimer</a>
                    </Popconfirm>
                )
            },
            responsive: ['sm']
        },*/

    ]

    const handlePay = (method: PaymentMethod) => {
        const urlParams = new URLSearchParams();
        urlParams.append('bookingId', lines[0].bookingId.toString());
        urlParams.append('paymentMethod', method);
        fetcher.submit(urlParams,{
            method:'post',
            action:'/booking/payment/process'
        });
    }

    return (
        <div className="app-booking-payment">
            <div>
                <Title level={2} align="center">VOTRE RÉSERVATION</Title>
            </div>
            <div className="app-booking-paymentn-section">
                <div className="app-booking-payment-timer-container">
                    <PaymentTimer waitConfirmIn={waitConfirmIn} onTimeElapsed={() => {

                    }} />
                </div>
            </div>

            <div className="app-booking-paymentn-section">
                <Title level={3} color="color2">Récapitulatif de la réservation</Title>

                <Table
                    dataSource={lines}
                    pagination={false}
                    className="app-booking-payment-recaputilatif"
                    columns={columns}
                />

            </div>

            <div className="app-booking-payment-section">
                <Title level={3} color="color2">Paiement</Title>

                <div>
                    <fetcher.Form method="post" action="/booking/payment/process">
                        <PaymentMethodGroup
                            onClick={handlePay}
                            title="Paiement par mobile money"
                            items={[
                                {
                                    name: 'Orange Money',
                                    logo: '/images/payment-methods/orange-money.png',
                                    code: 'ORANGE_MONEY'
                                },
                                {
                                    name: 'Mvola',
                                    logo: '/images/payment-methods/mvola.png',
                                    code: 'MVOLA'
                                },
                                {
                                    name: 'Airtel Money',
                                    logo: '/images/payment-methods/airtel-money.png',
                                    code: 'AIRTEL_MONEY'
                                }
                            ]}
                        />

                        <PaymentMethodGroup
                            title="Paiement par carte"
                            onClick={handlePay}
                            items={[
                                /*{
                                    name: 'Visa',
                                    logo: '/images/payment-methods/visa.png',
                                    code:'CREDIT_CARD'
                                },*/
                                {
                                    code: 'CREDIT_CARD',
                                    name: 'MasterCard',
                                    logo: '/images/payment-methods/master-card.png'
                                }
                            ]}
                        />
                    </fetcher.Form>
                </div>
            </div>
        </div>
    )

}

export default BookingPayment;