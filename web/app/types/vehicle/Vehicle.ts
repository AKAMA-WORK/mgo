import { VehicleSeat } from "./VehicleSeat"
import { VehicleType } from "./VehicleType"

export interface Vehicle {
    vehicleId: string
    carRegistrationNumber:string
    vehicleType: VehicleType
    lines:number
    columns:number
    seats: VehicleSeat[]
}