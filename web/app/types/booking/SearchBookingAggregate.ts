import { Category } from '../Category'
import { City } from '../City'
import { Organization } from '../Organization'
import { PeriodOfDay } from '../PeriodOfDay'
import { SearchResultAggregate } from '../SearchResultAggregate'
import { VehicleType } from '../vehicle/VehicleType'


export interface SearchBookingAggregate {
    vehicleTypes: SearchResultAggregate<VehicleType>[],
    organizations: SearchResultAggregate<Organization>[],
    periodOfDays: SearchResultAggregate<PeriodOfDay>[],
    from:SearchResultAggregate<City>[],
    to:SearchResultAggregate<City>[],
    categories:SearchResultAggregate<Category>[],
}   