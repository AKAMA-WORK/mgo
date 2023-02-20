import { City } from '../City'
import { Organization } from '../Organization'
import { PeriodOfDay } from '../PeriodOfDay'
import { SearchResultAggregate } from '../SearchResultAggregate'
import { VehicleType } from '../vehicle/VehicleType'


export interface SearchDepartureAggregate {
    vehicleTypes: SearchResultAggregate<VehicleType>[],
    organizations: SearchResultAggregate<Organization>[],
    periodOfDays: SearchResultAggregate<PeriodOfDay>[],
    city:SearchResultAggregate<City>[],
}   