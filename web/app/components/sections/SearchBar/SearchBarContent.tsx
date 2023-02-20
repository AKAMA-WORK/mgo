import { AutoComplete, Button, Col, Dropdown, Form, Row, Space, DatePicker, Menu, Input, Typography, DatePickerProps } from 'antd'
import { DownOutlined } from '@ant-design/icons';
import PlaceNumberInput from '~/components/form/PlaceNumberInput';
import { SearchIcon } from '~/components/icons/search-icon';
import { CityInput } from '~/components/City/CityInput';
import { City } from '~/types/City';
import Text from '~/components/typography/Text';
import { FormInstance } from 'antd/es/form/Form';

const dateFormat = 'DD/MM/YYYY';

interface DateFormatFactory {
    (prefix: string): DatePickerProps['format']
}

const customFormat: DateFormatFactory = (prefix: string) => {

    return value =>
        `${prefix} ${value.format(dateFormat)}`;
}

interface SearchBarContentProps {
    full?: boolean
    cities: City[]
    form: FormInstance<any>
    onSearch:()=>void
}

const transportations = [
    {
        key: 'all',
        label: 'Tous véhicules'
    },
    {
        key: 'bus',
        label: 'Voitures'
    }
]

const types = [
    {
        key: 'one-way',
        label: 'Aller simple'
    },
    {
        key: 'return',
        label: 'Aller-retour'
    }/*,
    {
        key: 'multi-stop',
        label: 'Multi-destinations'
    }*/
]


function SearchBarContent(props: SearchBarContentProps) {
    const form = props.form

    const isReturn = form.getFieldValue('type') === 'return';
    const getClassNames = (cls: string) => {
        return props?.full ? `${cls} ${cls}-full` : cls
    }

    return (
        <>
            <Row>
                <Col lg={{ span: 12 }} className={getClassNames(`app-search-bar-filter`)}>
                    <Form.Item
                        name="type"
                    >
                        <Dropdown
                            overlay={<Menu
                                items={types}
                                onClick={(event) => {
                                    form.setFieldsValue({ ...form.getFieldsValue(true), type: event.key })
                                }}
                            />}
                        >
                            <Space className='app-search-bar-filter-menu-item'>
                                {types.find(t => t.key === form.getFieldValue('type'))?.label}
                                <DownOutlined />
                            </Space>



                        </Dropdown>
                    </Form.Item>


                    <Dropdown
                        overlay={<div className='app-search-bar-seats'>

                            <Form.Item
                                name="seats"
                                label="Nombre de Places"
                            >
                                <PlaceNumberInput min={1} controls={true} />
                            </Form.Item>


                        </div>}
                    >
                        <Space className='app-search-bar-filter-menu-item'>
                            {form.getFieldValue('seats')} voyageur-euse(s)
                            <DownOutlined />
                        </Space>



                    </Dropdown>

                    <Form.Item
                        name="transportation"
                    >
                        <Dropdown
                            overlay={<Menu
                                items={transportations}
                                onClick={(event) => {
                                    form.setFieldsValue({ ...form.getFieldsValue(true), transportation: event.key })
                                }}
                            />}
                        >

                            <Space className='app-search-bar-filter-menu-item'>

                                {transportations.find(t => t.key === form.getFieldValue('transportation'))?.label}

                                <DownOutlined />
                            </Space>

                        </Dropdown>
                    </Form.Item>


                </Col>
            </Row>
            <Row className={getClassNames('app-search-bar-form-container')} >
                <Col span={24} lg={{ span: 12 }}>
                    <Row>
                        <Col span={12} className="app-search-bar-form-field">
                            <CityInput
                                name="from"
                                label="DEPART"
                                cities={props?.cities || []}
                            />
                        </Col>
                        <Col span={12} className="app-search-bar-form-field">
                            <CityInput
                                name="to"
                                label="DESTINATION"
                                cities={form.getFieldValue('from') ? (props?.cities || []).filter(city => city.cityId !== form.getFieldValue('from')) : props?.cities || []}
                            />

                        </Col>


                    </Row>

                </Col>
                <Col span={24} lg={{ span: 12 }}>
                    <Row>
                        <Col span={props?.full ? 24 : 22} className="app-search-bar-form-field app-search-bar-form-date-picker-field">
                            <Text color="color1" className="app-search-bar-form-date-label">DATE</Text>
                            <Row>
                                <Col span={isReturn ? 12 : 24}>
                                    <Form.Item name="outwardDate">
                                        <DatePicker className="app-search-bar-form-date-field" format={isReturn ? customFormat('Aller') : dateFormat} placeholder={isReturn ? "Aller" : "Départ"} />
                                    </Form.Item>
                                </Col>

                                {isReturn && <Col span={12}>
                                    <Form.Item name="returnDate">
                                        <DatePicker className="app-search-bar-form-date-field" format={customFormat('Retour')} placeholder="Retour" />
                                    </Form.Item>
                                </Col>}


                            </Row>


                            {/* <Form.Item
                                    name="date"
                                    label="DATE"
                                >
                                    <RangePicker
                                        className='app-search-bar-date-ranger-picker' />

                                </Form.Item>*/}
                        </Col>
                        {!props?.full && <Col span={2}>
                            <Button onClick={props.onSearch} type='primary' className='app-search-bar-button' icon={<SearchIcon />} />
                        </Col>}
                    </Row>
                </Col>
            </Row>
        </>
    )
}

export default SearchBarContent 
