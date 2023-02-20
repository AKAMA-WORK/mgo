import { DepartureVehicleSeatStatus } from "./DepartureVehicleSeatStatus"

export interface DepartureVehicleSeat {
    departureVehicleSeatId: string
    x: number
    y: number
    seatNumber: number
    status: DepartureVehicleSeatStatus
    lockUntil: string
}
