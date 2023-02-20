import { useFetcher } from "@remix-run/react"
import { Form, FormItemProps, Select } from "antd"
import { City } from "~/types/City"
const { Option } = Select;



export const CityInput = (props: FormItemProps<any> & {cities: City[] }) => {


 
    return (<Form.Item
            {...props}
        >
            <Select
                showSearch
                placeholder="Choisir une ville"
                optionFilterProp="children"
                >
                {
                    (props.cities || [])
                        .map(city => (<Option key={city.cityId} value={city.cityId}>{`${city.trigram} - ${city.name}`}</Option>))
                }
            </Select>
        </Form.Item>)


}