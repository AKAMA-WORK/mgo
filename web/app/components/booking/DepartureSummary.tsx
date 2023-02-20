import { Typography } from "antd"
import moment from "moment"
import { DeparturesBookingLoaderData } from "~/route-containers/departures-booking/departures-booking.server"
import { City } from "~/types/City"
import CityPreview from "../City/City"




interface DepartureSummaryDateProps {
    title: string
    date: string
}
const DepartureSummaryDate = ({ title, date }: DepartureSummaryDateProps) => {
    return (<div>
        <div>{title}</div>
        <div>{date}</div>
    </div>)
}


interface DepartureSummaryProps {
    departuresBooking: DeparturesBookingLoaderData
}

const DepartureSummary = ({ departuresBooking }: DepartureSummaryProps) => {

    const departure = departuresBooking.departures[0]
    return <div className="app-departure-summary">
        <div className="app-departure-summary-cities">
            <CityPreview city={departure.departure.from} iconPosition="left" />
            <div className="app-departure-summary-cities-separator">
                <hr />
            </div>
            <CityPreview city={departure.departure.to} iconPosition="right" />
        </div>
        <div className="app-departure-summary-dates">
            <div>
                <svg width="41" height="40" viewBox="0 0 41 40" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M13.8333 17.3333H16.5V19.9999H13.8333V17.3333ZM19.1667 23.9999H21.8333V21.3333H19.1667V23.9999ZM19.1667 27.9999H21.8333V25.3333H19.1667V27.9999ZM13.8333 23.9999H16.5V21.3333H13.8333V23.9999ZM13.8333 27.9999H16.5V25.3333H13.8333V27.9999ZM24.5 27.9999H27.1667V25.3333H24.5V27.9999ZM24.5 19.9999H27.1667V17.3333H24.5V19.9999ZM33.8333 11.9999V30.6666C33.8312 31.3732 33.5496 32.0502 33.05 32.5499C32.5503 33.0495 31.8733 33.3312 31.1667 33.3333H9.83333C9.12674 33.3312 8.44969 33.0495 7.95005 32.5499C7.45041 32.0502 7.16878 31.3732 7.16667 30.6666V11.9999C7.16878 11.2933 7.45041 10.6163 7.95005 10.1167C8.44969 9.61701 9.12674 9.33538 9.83333 9.33327H12.5V7.99994C12.5 7.80531 12.5426 7.61303 12.6248 7.43662C12.707 7.2602 12.8269 7.10392 12.9759 6.97876C13.125 6.8536 13.2996 6.76258 13.4876 6.71211C13.6756 6.66164 13.8723 6.65294 14.064 6.68661C14.3773 6.74668 14.6594 6.91506 14.861 7.16224C15.0626 7.40943 15.1708 7.71966 15.1667 8.03861V9.33327H25.8333V7.99994C25.8333 7.80531 25.8759 7.61303 25.9581 7.43662C26.0403 7.2602 26.1602 7.10392 26.3092 6.97876C26.4583 6.8536 26.6329 6.76258 26.8209 6.71211C27.0089 6.66164 27.2056 6.65294 27.3973 6.68661C27.7106 6.74668 27.9927 6.91506 28.1943 7.16224C28.3959 7.40943 28.5041 7.71966 28.5 8.03861V9.33327H31.1667C31.8733 9.33538 32.5503 9.61701 33.05 10.1167C33.5496 10.6163 33.8312 11.2933 33.8333 11.9999V11.9999ZM31.1667 11.9999H28.5V13.3333C28.5 13.5279 28.4574 13.7202 28.3752 13.8966C28.293 14.073 28.1731 14.2293 28.0241 14.3545C27.875 14.4796 27.7004 14.5706 27.5124 14.6211C27.3244 14.6716 27.1277 14.6803 26.936 14.6466C26.6227 14.5865 26.3406 14.4182 26.139 14.171C25.9374 13.9238 25.8292 13.6136 25.8333 13.2946V11.9999H15.1667V13.3333C15.1667 13.5279 15.1241 13.7202 15.0419 13.8966C14.9597 14.073 14.8398 14.2293 14.6908 14.3545C14.5417 14.4796 14.3671 14.5706 14.1791 14.6211C13.9911 14.6716 13.7944 14.6803 13.6027 14.6466C13.2894 14.5865 13.0073 14.4182 12.8057 14.171C12.6041 13.9238 12.4959 13.6136 12.5 13.2946V11.9999H9.83333V30.6666H31.1667V11.9999ZM24.5 23.9999H27.1667V21.3333H24.5V23.9999ZM19.1667 19.9999H21.8333V17.3333H19.1667V19.9999Z" fill="#1276A1" />
                </svg>

            </div>
            {
                departuresBooking.type === 'return' ?
                    <>
                        <DepartureSummaryDate title="Aller" date={moment(departuresBooking.departures.find(d => d.direction === 'go')?.departure.date).format('DD/MM/YYYY')} />
                        <DepartureSummaryDate title="Retour" date={moment(departuresBooking.departures.find(d => d.direction === 'return')?.departure.date).format('DD/MM/YYYY')} />
                    </> :
                    (
                        <DepartureSummaryDate title="Date" date={moment(departure.departure.date).format('DD/MM/YYYY')} />
                    )
            }

        </div>
    </div>

}
export default DepartureSummary