import { Category } from "../Category"
import { City } from "../City"

export interface OrganizationConfig{
    organizationConfigId:string
    authorizedLuggage:number
    priceExtraLuggage:number
    bookingCancellationPenaltyAmount:number
    bookingCancellationPenaltyPercent:number
    transferCommission:number
    from?:City
    to?:City
    category?: Category

}