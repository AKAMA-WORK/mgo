import { AutoComplete, Button, Col, Dropdown, Form, Row, Select, Space, DatePicker, Typography, Image } from 'antd'
import React from 'react'
import Title from '~/components/typography/Title'
import Text from '~/components/typography/Text'
import {  Organization } from '~/types/Organization'
import RowLayout from '../../RowLayout'


interface CompaniesProps {
    className?: string
    companies: Organization[]
}

function Companies({companies, className=''}: CompaniesProps) {

    return (
        <RowLayout className={`app-companies ${className}`} wrap={false} containerClassName="app-companies-container">
            <Row>
                <Col span={24}>
                    <Title level={2} align="center">
                        ILS COLLABORENT AVEC NOUS
                    </Title>
                </Col>

            </Row>
            <Row>
                <Col span={22} offset={1} lg={{
                    span: 16,
                    offset: 4
                }}>
                    <Text align="center">
                    Pour vous assurer un large choix pour vos voyages, nous collaborons avec un grand nombre de coopératives. Proin nunc mi, pellentesque sed condimentum sed, iaculis in massa. Donec facilisis arcu posuere, porta tortor eu, elementum sem.

                    </Text>
                </Col>
            </Row>
            <Row>
                <Col span={22} offset={1} lg={{
                    span: 16,
                    offset: 4
                }} className="app-companies-list ptn">
                    {
                        companies.map((company) => (

                            <Image key={company.organizationId} src={company.logo} />
                        ))
                    }
                </Col>
            </Row>
        </RowLayout>

    )
}

export default Companies 
