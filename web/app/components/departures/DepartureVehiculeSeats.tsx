
import driverImg from '~/assets/images/seats/driver.svg'
import unavailableImg from '~/assets/images/seats/unavailable.svg'

import availableImg from '~/assets/images/seats/available.svg'
import selectedImg from '~/assets/images/seats/selected.svg'
import { Departure } from '~/types/departure/Departure'
import { useFetcher, useLocation } from '@remix-run/react'
import { Button } from 'antd'
import { MouseEventHandler } from 'react'



interface DepartureVehiculeSeatsProps {
    departure: Departure
    selected: number[]
    showConfirmButton?: boolean

}
const DepartureVehiculeSeats = ({ departure, selected,showConfirmButton }: DepartureVehiculeSeatsProps) => {
    let fetcher = useFetcher();
    let location = useLocation();

    if (!departure.seats) {
        return null
    }

    const rows = [...new Array(departure.lines)]
    const columns = [...new Array(departure.columns)]
    const redirectUrl = location.pathname + location.search


    return (
        <div className="app-departure-vehicule-seats">
            <div className='app-departure-vehicule-seats-table-container'>
                <table>
                    <tbody>
                        {
                            rows.map((_, indexLine) => {
                                const line = indexLine + 1

                                return <tr key={line}>
                                    {columns.map((__, indexColumn) => {
                                        const column = indexColumn + 1

                                        let tdContent = null
                                        const place = departure.seats.find(p => p.x === column && p.y === line);
                                        if (place) {
                                            if (place.seatNumber === -2) {
                                                tdContent = (
                                                    <div className='app-departure-vehicule-seats-driver'
                                                    >
                                                    </div>
                                                )
                                            }
                                            else if (place.seatNumber === -1) {
                                                // Empty
                                            }
                                            else if (place.status === 'BOOKED' || place.status === 'LOCKED') {

                                                tdContent = (
                                                    <div className='app-departure-vehicule-seats-unavailable'>
                                                        <span>{place.seatNumber}</span>
                                                    </div>
                                                )
                                            }
                                            else if (place.seatNumber > 0) {

                                                if (selected.includes(place.seatNumber)) {
                                                    tdContent = (
                                                        <fetcher.Form method="post" action="/seat">
                                                            <input type="hidden" name="_action" defaultValue="delete-seat" />
                                                            <input
                                                                key={place.seatNumber}
                                                                type="hidden"
                                                                name="seat"
                                                                defaultValue={place.seatNumber}
                                                            />
                                                            <input
                                                                key={redirectUrl}
                                                                type="hidden"
                                                                name="redirect"
                                                                defaultValue={redirectUrl}
                                                            />
                                                            <input
                                                                key={departure.departureId}
                                                                type="hidden"
                                                                defaultValue={departure.departureId}
                                                                name="departureId"
                                                            />
                                                            <div className='app-departure-vehicule-seats-selected'>
                                                                <Button type="text" htmlType="submit">{place.seatNumber}</Button>
                                                            </div>
                                                        </fetcher.Form>
                                                    )
                                                }
                                                else {
                                                    tdContent = (
                                                        <fetcher.Form method="post" action="/seat">
                                                            <input type="hidden" name="_action" defaultValue="add-seat" />
                                                            <input
                                                                key={place.seatNumber}
                                                                type="hidden"
                                                                name="seat"
                                                                defaultValue={place.seatNumber}
                                                            />
                                                            <input
                                                                key={redirectUrl}
                                                                type="hidden"
                                                                name="redirect"
                                                                defaultValue={redirectUrl}
                                                            />
                                                            <input
                                                                key={departure.departureId}
                                                                type="hidden"
                                                                defaultValue={departure.departureId}
                                                                name="departureId"
                                                            />
                                                            <div className='app-departure-vehicule-seats-available'>
                                                                <Button type="text" htmlType="submit">{place.seatNumber}</Button>
                                                            </div>
                                                        </fetcher.Form>

                                                    )
                                                }

                                            }
                                        }


                                        return <td key={column}>{tdContent}</td>

                                    })}
                                </tr>




                            })
                        }
                    </tbody>
                </table>
            </div>

            {showConfirmButton && <fetcher.Form method="post" action="/seat">
                <input type="hidden" name="_action" defaultValue={"add-seat-selection"} />
                <input
                    key={redirectUrl}
                    type="hidden"
                    name="redirect"
                    defaultValue={redirectUrl}
                />
                <input
                    key={departure.departureId}
                    type="hidden"
                    defaultValue={departure.departureId}
                    name="departureId"
                />
                <Button type="primary" htmlType="submit">Réserver</Button>
            </fetcher.Form>}


        </div>
    )
}

export default DepartureVehiculeSeats