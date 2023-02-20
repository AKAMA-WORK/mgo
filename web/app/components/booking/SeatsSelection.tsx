import { Button, Typography } from "antd"
import { Departure } from "~/types/departure/Departure"
import DeparturePreview from "../departures/Departure"
import Title from "../typography/Title"
import Text from "../typography/Text"
import { Form, Link, useFetcher } from "@remix-run/react"


export interface SeatsSelectionDeparture {
    direction?: 'go' | 'return'
    departure: Departure
}

export interface SeatsSelectionProps {
    type: 'one-way' | 'return' | 'multi-stop'
    departures: SeatsSelectionDeparture[]
}

const SeatsSelection = ({ departures, type }: SeatsSelectionProps) => {


    return (
        <>
            <div className="app-booking-selection-title">
                <Title level={2} align="center">Sélection des places</Title>
            </div>

            {
                departures.map((selection) => (<div key={selection.departure.departureId} className="app-booking-selection-section">
                    {selection.direction ? <Title level={3} color="color2">{selection.direction === "go" ? 'voyage aller' : 'voyage retour'}</Title> : null}
                    <DeparturePreview showConfirmButton={false} seatsSelection departure={selection.departure} />
                </div>))
            }
            <div className="app-booking-selection-actions-container-text">
            <Text>
                <strong>Les informations affichées ci-dessus sont dynamiques et sujettes à des modifications.</strong> Votre réservation effective ne sera prise en compte qu’une fois le paiement effectué. Après la confirmation de la booking, vous aurez un délai de 5 minutes pour effectuer le paiement. Passé ce délai, notre système réinitialisera votre réservation.

            </Text>
            </div>
            <div className="app-booking-selection-actions-container-button">
                <Form action="/booking/payment/init" method="post">
                    <Button htmlType="submit" type="primary">Confirmer ma réservation</Button>
                </Form>
            </div>
           
        </>
    )

}

export default SeatsSelection;