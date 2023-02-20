/* eslint-disable no-sparse-arrays */
import { Button, Divider, Popconfirm, Table, Typography } from "antd"
import { ColumnsType } from "antd/lib/table"
import moment from "moment"
import { Booking } from "~/types/Booking"
import { Departure } from "~/types/departure/Departure"
import { formatAmount } from "~/utils/amount-util"
import DeparturePreview from "../departures/Departure"
import PaymentMethodGroup from "../PaymentMethodGroup/PaymentMethodGroup"
import Title from "../typography/Title"
import H2 from "../typography/Title"



export interface DetailsPaymentProps {
    bookings: Booking[]
}


const DetailsPayment = ({ bookings }: DetailsPaymentProps) => {


    const handleDelete = (key: React.Key) => {
    };

    const renderTotal = (record: Booking) => {
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

    const columns: ColumnsType<Booking> = [
        {
            title: 'Détail',
            render: (record: Booking) => {

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
                    {renderDescription('Total', `${formatAmount(record.amount)} Ar`)}
                </>

            },
            responsive: ['xs']
        },
        {
            title: 'Descriptif',
            render: (record: Booking) => {
                return record.bookingId === undefined ? (
                    renderTotal(record)
                ) :
                    renderDescription(record.departure.organization?.name, `${record.departure.from?.name} - ${record.departure.to?.name} / ${moment(record.departure.date).format('dddd DD MMMM YYYY')}${record.departure.hour?` à ${record.departure.hour}`:''}`)
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
            render: (record: Booking) => {
                return `${formatAmount(record.amount)} Ar`
            },
            onCell: (record, index) => {
                return record.bookingId === undefined ? { colSpan: 0 } : { colSpan: 1 }

            },
            responsive: ['sm']
        },


    ]

    return (
        <div className="app-booking-payment">
            <div className="app-booking-paymentn-section">
                <Title level={2}  align="center">Récapitulatif de la réservation</Title>

                <Table
                    dataSource={bookings}
                    pagination={false}
                    className="app-booking-payment-recaputilatif"
                    columns={columns}
                />

            </div>
        </div>
    )

}

export default DetailsPayment;