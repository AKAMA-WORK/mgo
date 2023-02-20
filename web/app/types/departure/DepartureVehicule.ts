import { DepartureVehiculePlace } from "./DepartureVehiculePlace"

export interface DepartureVehicule {
    name: string
    lines: number
    columns:number
    availablePlaces:number
    totalPlaces:number
    seats: DepartureVehiculePlace[]

}
