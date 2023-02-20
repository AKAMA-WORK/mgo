import { Collapse, Form, Checkbox } from "antd"
import Title from "~/components/typography/Title";
import { City } from "~/types/City";
import { Organization } from "~/types/Organization";
import { SearchResultAggregate } from "~/types/SearchResultAggregate";
import { VehicleType } from "~/types/vehicle/VehicleType";
import { PeriodOfDay } from "~/types/PeriodOfDay";
import DepartureFilterPanel from "~/components/departures/DepartureFilter/DepartureFilterPanel";
import CheckBoxRect from "~/components/form/CheckBoxRect";

const { Panel } = Collapse;

interface BookingesFilterProps {
    from?: SearchResultAggregate<City>[]
    to?: SearchResultAggregate<City>[]
    organizations?: SearchResultAggregate<Organization>[]
    vehicleTypes?: SearchResultAggregate<VehicleType>[]
    periodOfDays?: SearchResultAggregate<PeriodOfDay>[]
}

const BookingsFilter = ({ from, to, organizations, vehicleTypes, periodOfDays }: BookingesFilterProps) => {
    const onChange = (key: string | string[]) => {
        console.log(key);
    };

    return (
        <Collapse key="filter" className="app-bookings-filter-menu-title " ghost expandIconPosition="end" defaultActiveKey={['from', 'to', 'organizations']}

            onChange={onChange}>

            <Panel header={<Title level={4} textTransform=''>Départs</Title>} key="from">
                <Form.Item name="from">
                    <DepartureFilterPanel data={
                        (from || []).map(items => ({
                            id: items.cityId,
                            name: `${items.name}`,
                            label: items.name,
                            count: items.totalCount || 0
                        }))

                    } key="from" total={from?.length || 0} />
                </Form.Item>

            </Panel>

            <Panel header={<Title level={4} textTransform=''>Destinations</Title>} key="to">
                <Form.Item name="to">
                    <DepartureFilterPanel data={
                        (to || []).map(items => ({
                            id: items.cityId,
                            name: `${items.name}`,
                            label: items.name,
                            count: items.totalCount || 0
                        }))

                    } key="to" total={to?.length || 0} />
                </Form.Item>

            </Panel>


            <Panel header={<Title level={4} textTransform=''>Coopératives</Title>} key="organizations" >
                <Form.Item name="organizations" >
                    <CheckBoxRect/>
                    <DepartureFilterPanel data={
                        (organizations || []).map(type => ({
                            id: type.organizationId,
                            name: `${type.name}`,
                            label: type.name,
                            count: type.totalCount || 0
                        }))
                    } key="organizations" total={organizations?.length || 0}
                        loadMoreMessage="Voir 5 coopératives en plus"
                    />
                </Form.Item>
            </Panel>

        </Collapse>
    )
}

export default BookingsFilter;