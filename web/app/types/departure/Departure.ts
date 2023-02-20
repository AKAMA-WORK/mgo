import { Category } from "../Category"
import { City } from "../City"
import { Organization } from "../Organization"
import { DepartureVehicule } from "./DepartureVehicule"
import { SeatSelectionItem } from "../SeatSelectionItem"
import { DepartureVehicleSeat } from "./DepartureVehicleSeat"
import { VehicleType } from "../vehicle/VehicleType"
import { OrganizationConfig } from "../organization/OrganizationConfig"
import { Vehicle } from "../vehicle/Vehicle"

export interface Departure {
    departureId: string
    date: string
    time?: string
    estimatedArrivalTime?:string
    price: number
    status: "ONDATE" | "CANCELLED" | "TRANSFERED"
    from: City
    to: City
    vehicleType?: VehicleType
    lines: number
    columns:number
    availableSeats:number
    totalSeats:number
    cities: City 
    category?: Category
    organization: Organization
    vehicle?: Vehicle
    seatSelection?: SeatSelectionItem
    seats: DepartureVehicleSeat[]
    config?:OrganizationConfig
}
