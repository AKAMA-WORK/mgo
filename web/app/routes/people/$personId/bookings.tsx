import { LoaderFunction } from "@remix-run/node";
import { Outlet, useLoaderData, useParams, useSubmit } from "@remix-run/react";
import { Affix, Button, Checkbox, Col, Drawer, Dropdown, Form, Layout, Menu, Space, Typography } from "antd";
import { useState } from "react";
import BookingsFilter from "~/components/bookings/BookingsFilter/BookingsFilter";
import RowLayout from "~/components/RowLayout";
import { DownOutlined } from '@ant-design/icons';
import Title from "~/components/typography/Title";
import Text from "~/components/typography/Text";
import { Booking } from "~/types/booking/Booking";
import { extractSearchBookingParamsFromForm, searchBookingParams2FormValues } from "~/utils/booking-util";
import { createSearchBookingLoader, SearchBookingLoaderData } from "~/route-containers/bookings/bookings";

const { Header, Sider, Content } = Layout;




const SORT_OPTIONS = [
    /*{
        key: 'price_asc',
        label: 'Prix croissant'
    },
    {
        key: 'price_desc',
        label: 'Prix décroissant'
    },

    {
        key: 'departureDate_asc',
        label: 'Date de départ croissant'
    },
    {
        key: 'departureDate_desc',
        label: 'Date de départ décroissant'
    },*/
    {
        key: 'bookingDate_asc',
        label: 'Date de réservation croissant'
    },
    {
        key: 'bookingDate_desc',
        label: 'Date de réservation décroissant'
    }
]
export const loader: LoaderFunction = createSearchBookingLoader();

export default function Booking() {
    const data = useLoaderData<SearchBookingLoaderData>();
    const params = useParams();

    const [form] = Form.useForm();
    Form.useWatch('periodOfDays', form)
    Form.useWatch('vehiculeTypes', form)
    Form.useWatch('organizations', form)
    Form.useWatch('categories', form)
    Form.useWatch('sort', form);
    Form.useWatch('from', form);
    Form.useWatch('to', form);

    const submit = useSubmit();


    const [visible, setVisible] = useState(false);

    const showDrawer = () => {
        setVisible(true);
    };

    const onClose = () => {
        setVisible(false);
    };

    const onFinish = () => {
    };

    const onFinishFailed = () => {
    };

    const onChange = () => {
        submit(extractSearchBookingParamsFromForm(form), { method: 'get', action: `/people/${params.personId}/bookings` })
    }

    const initialValues = searchBookingParams2FormValues(data?.query)
    const filterTitle = <Title level={3} color="color2">Filtres</Title>

    const filterComponent = (
        <BookingsFilter
            from={data.bookings.aggregate?.from}
            to={data.bookings.aggregate?.to}
            organizations={data.bookings.aggregate?.organizations || []}
        />
    )

    const isOneWay = initialValues.type === 'one-way'
    return (
        <>
            <Form
                form={form}
                onFinish={onFinish}
                onChange={onChange}
                onFinishFailed={onFinishFailed}
                initialValues={initialValues}
                autoComplete="off"
                layout='horizontal'
            >
                <div>
                    <RowLayout className="app-departures" wrap={true}>
                        <Col span={24}>
                            <Drawer placement="right" closable={true} title={filterTitle} onClose={onClose} open={visible}
                                mask={false}
                                className="app-departures-section app-departures-filtre-drawer"
                            >
                                {filterComponent}
                            </Drawer>

                            <Layout className="app-departures-container">
                                <Sider
                                    width={250}
                                    trigger={null} className="app-departures-section app-departures-filtre-sider">
                                    {filterTitle}
                                    {filterComponent}

                                </Sider>
                                <Layout className="site-layout">
                                    <Header className="app-departures-section app-departures-result-header">
                                        <div>
                                            <Title level={3}>
                                                Historique de réservations
                                            </Title>
                                        </div>
                                        <div className="app-departures-result-header-detail">
                                            <Text>
                                                <strong className="app-departures-result-header-total">{data?.bookings.totalCount || 0} réservations </strong> <span className="app-departures-header-total-text">{isOneWay ? "" : "enregistrées"}</span>
                                            </Text>
                                            <div className="app-departures-result-header-sorting">
                                                <Button type="link" onClick={showDrawer} className="app-departures-header-filter-open">Filtre</Button>
                                                <Form.Item name="sort">
                                                    <Dropdown
                                                        overlay={<Menu
                                                            onClick={(event) => {
                                                                form.setFieldsValue({ ...form.getFieldsValue(true), sort: event.key })
                                                                onChange();
                                                            }}

                                                            items={SORT_OPTIONS}
                                                        />}
                                                    >
                                                        <Space className='app-search-bar-filter-menu-item'>
                                                            {SORT_OPTIONS.find(t => t.key === form.getFieldValue('sort'))?.label}

                                                            <DownOutlined />
                                                        </Space>



                                                    </Dropdown>
                                                </Form.Item>
                                            </div>
                                        </div>
                                    </Header>
                                    <Content
                                        className="app-departures-section"
                                    >
                                        <Outlet />
                                    </Content>
                                </Layout>
                            </Layout>
                        </Col>
                    </RowLayout>
                </div>
            </Form>
        </>
    )
}  