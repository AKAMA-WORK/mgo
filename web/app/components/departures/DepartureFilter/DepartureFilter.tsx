import { Collapse, Form,Checkbox } from "antd"
import CheckBoxRect from "~/components/form/CheckBoxRect";
import CountableCheckBox from "~/components/form/CountableCheckBox";
import PlaceNumberInput from "~/components/form/PlaceNumberInput";
import Title from "~/components/typography/Title";
import { Organization } from "~/types/Organization";
import { PeriodOfDay } from "~/types/PeriodOfDay";
import { SearchResult } from "~/types/SearchResult";
import { SearchResultAggregate } from "~/types/SearchResultAggregate";
import { VehicleType } from "~/types/vehicle/VehicleType";
import DepartureFilterPanel, { DepartureFilterItem } from "./DepartureFilterPanel";




const { Panel } = Collapse;

interface DepartureFilterProps {
    vehicleTypes?: SearchResultAggregate<VehicleType>[]
    organizations?: SearchResultAggregate<Organization>[]
    periodOfDays?:  SearchResultAggregate<PeriodOfDay>[]
}

const DepartureFilter = ({ vehicleTypes, organizations,periodOfDays }: DepartureFilterProps) => {

    const onChange = (key: string | string[]) => {
        console.log(key);
    };



    return (
        <Collapse key="filter" className="app-departures-filter-menu-title" ghost expandIconPosition="end" defaultActiveKey={['horaire', 'seats', 'vehicules', 'companies']}

            onChange={onChange}>

            <Panel header={<Title level={4} textTransform=''>Horaires de départ</Title>}

                key="horaire">
                <Form.Item name="periodOfDays" >
                    <DepartureFilterPanel data={(periodOfDays || []).map(type => ({
                            id: type.id,
                            label: type.label,
                            count: type.totalCount || 0
                        }))} total={periodOfDays?.length || 0} />
                </Form.Item>

            </Panel>

            <Panel header={<Title level={4} textTransform=''>Places disponibles</Title>} key="seats">
                <Form.Item name="seats" >
                    <PlaceNumberInput controls />
                </Form.Item>
            </Panel>


            <Panel header={<Title level={4} textTransform=''>Types de véhicule</Title>} key="vehicules" >
                <Form.Item name="vehiculeTypes" >

                    <DepartureFilterPanel
                    
                    data={
                        (vehicleTypes || []).map(type => ({
                            id: type.vehicleTypeId,
                            name: `${type.vehicleTypeId}`,
                            label: type.name,
                            count: type.totalCount || 0
                        }))
                    } key="vehicules" total={vehicleTypes?.length || 0} />
                </Form.Item>
            </Panel>

            <Panel header={<Title level={4} textTransform=''>Coopératives</Title>} key="companies" >
                <Form.Item name="organizations" >
                    <CheckBoxRect/>
                    <DepartureFilterPanel 
                     
                    data={
                        (organizations || []).map(type => ({
                            id: type.organizationId,
                            name: `${type.name}`,
                            label: type.name,
                            count: type.totalCount || 0
                        }))
                    } key="companies" total={organizations?.length || 0}
                        loadMoreMessage="Voir 5 coopératives en plus"
                    />
                </Form.Item>
            </Panel>




        </Collapse>
    )
}

export default DepartureFilter