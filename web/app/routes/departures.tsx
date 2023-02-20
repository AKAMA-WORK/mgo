import { LoaderFunction } from "@remix-run/node";
import { Outlet, useLoaderData, useSubmit } from "@remix-run/react";
import { Affix, Button, Checkbox, Col, Drawer, Dropdown, Form, Layout, Menu, Skeleton, Space, Typography } from "antd";
import { FormEvent, useState } from "react";
import DepartureFilter from "~/components/departures/DepartureFilter/DepartureFilter";
import RowLayout from "~/components/RowLayout";
import SearchBar from "~/components/sections/SearchBar/SearchBar";
import useBreakpoint from "~/hooks/useBreakpoint";
import { Organization } from "~/types/Organization";
import { VehicleType } from "~/types/vehicle/VehicleType";
import { DownOutlined } from '@ant-design/icons';
import Title from "~/components/typography/Title";
import Text from "~/components/typography/Text";
import { SearchResult } from "~/types/SearchResult";
import { City } from "~/types/City";
import { callWithAuthenticationTokenIfAuthenticated } from "~/utils/server/callable-with-token-util";
import qs from "qs";
import { extractSearchDepartureParamsFromForm, parseSearchDepartureParams, searchDepartureParams2FormValues } from "~/utils/departure-util";
import { Departure } from "~/types/departure/Departure";
import { SearchDepartureAggregate } from "~/types/departure/SearchDepartureAggregate";
import SearchBarContent from "~/components/sections/SearchBar/SearchBarContent";
import { searchDepartures } from "~/utils/server/departures.server";
import { searchCities } from "~/utils/server/cities.server";

const { Header, Sider, Content } = Layout;

interface LoaderData {
  departures: SearchResult<Departure, SearchDepartureAggregate>


  cities: City[]
  query: {
    filter?: Record<string, any>
  }
}

const SORT_OPTIONS = [
  {
    key: 'price_asc',
    label: 'Prix croissant'
  },
  {
    key: 'price_desc',
    label: 'Prix décroissant'
  }
]
export const loader: LoaderFunction = ({ request }) => {

  return callWithAuthenticationTokenIfAuthenticated<LoaderData>(request, async (token) => {
    const url = new URL(request.url);
    const query = qs.parse(url.search?.substring(1)) as any;
    const departureParams = parseSearchDepartureParams(query);

    const [departures, citiesResult] = await Promise.all([searchDepartures(departureParams, token?.accessToken), searchCities(token?.accessToken)]);


    return {
      query,
      cities: citiesResult.items,
      departures
    }
  })



}



export default function Departure() {
  const data = useLoaderData<LoaderData>();

  const breakPoint = useBreakpoint('xs');
  const [form] = Form.useForm();
  Form.useWatch('seats', form);
  Form.useWatch('periodOfDays', form)
  Form.useWatch('vehiculeTypes', form)
  Form.useWatch('organizations', form)
  Form.useWatch('sort', form);
  Form.useWatch('transportation', form);
  Form.useWatch('type', form);
  Form.useWatch('from', form);
  Form.useWatch('to', form);
  Form.useWatch('outwardDate', form);
  Form.useWatch('returnDate', form);
  Form.useWatch('seatsSelection', form);



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
    submit(extractSearchDepartureParamsFromForm(form), { method: 'get', action: `/departures` })
  }

  const wrapWithAffix = breakPoint !== 'xs' && breakPoint !== 'sm' && breakPoint !== 'md';

  const initialValues = searchDepartureParams2FormValues(data?.query)

  const header = (<div className="app-departure-header">
    <RowLayout wrap={true}>
      <div className="app-search-bar-full">
        <SearchBarContent form={form} onSearch={onChange} cities={data?.cities || []} full={true} />
      </div>
    </RowLayout>
  </div>)

  const filterTitle = <Title level={3} color="color2">Filtres avancés</Title>

  const filterComponent = (
    <DepartureFilter vehicleTypes={data.departures.aggregate?.vehicleTypes || []} organizations={data.departures.aggregate?.organizations || []} periodOfDays={data.departures.aggregate?.periodOfDays || []} />
  )

  const isOneWay = initialValues.type === 'one-way'
  const [collapsed, setCollapsed] = useState(true);
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
        {wrapWithAffix ? (
          <Affix offsetTop={52}>
            {header}
          </Affix>
        ) : header}
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
                  trigger={null} className="app-departures-section app-departures-filtre-sider"
                >
                  {filterTitle}
                  {filterComponent}

                </Sider>

                <Layout className="site-layout">
                  <Header className="app-departures-section app-departures-result-header">
                    <div>
                      <Title level={3}>
                        {isOneWay ? "Résultats de recherche" : "Résultats de recherche pour l’aller"}
                      </Title>
                    </div>
                    <div className="app-departures-result-header-detail">
                      <Text>
                        <strong className="app-departures-result-header-total">{data?.departures?.totalCount || 0} départs</strong> <span className="app-departures-header-total-text">{isOneWay ? "" : "prévus pour la date d’aller demandée"}</span>
                      </Text>

                      <div className="app-departures-result-header-sorting">
                        <Button type="link" onClick={showDrawer} className="app-departures-header-filter-open">Filtre</Button>
                        <Form.Item name="seatsSelection">
                          <Checkbox checked={form.getFieldValue('seatsSelection')} onChange={(event) => {
                            form.setFieldsValue({ ...form.getFieldsValue(true), seatsSelection: event.target.checked })
                            onChange();
                          }}
                          >Afficher les places</Checkbox>
                        </Form.Item>
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
                    <Outlet/>  
                  </Content>
                </Layout>

              </Layout>

            </Col>
          </RowLayout>
        </div>
      </Form>

    </>
  );
}
