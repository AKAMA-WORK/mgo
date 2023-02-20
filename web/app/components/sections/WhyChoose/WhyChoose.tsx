import { Col, List, Row, Typography } from "antd";
import React from "react";
import { useTranslation } from "react-i18next";
import { BestOfferIcon } from "~/components/icons/best-offer-icon";
import { QuickBookingIcon } from "~/components/icons/quick-booking-icon";
import { ReliableIcon } from "~/components/icons/reliable-icon";
import RowLayout from "~/components/RowLayout";
import Title from "~/components/typography/Title";
import { Departure } from "~/types/departure/Departure";
import WhyChooseItem from "./WhyChooseItem";


const dataSource = [
    {
        title: 'Les meilleurs offres',
        icon: <BestOfferIcon />,
        description: 'Donec porttitor, velit eget vestibulum lacinia, augue nibh pharetra nulla, in gravida sapien risus ac eros. Aliquam varius tempor tellus'

    },
    {
        title: 'Réservation rapide',
        icon: <QuickBookingIcon />,
        description: 'Proin pretium consectetur ex eu ullamcorper. Nulla viverra diam sit amet rutrum ullamcorper.',
    },
    {
        title: 'Fiable',
        icon: <ReliableIcon />,
        description: 'Vivamus posuere aliquam mollis. Curabitur sit amet mollis diam. Aliquam arcu arcu, feugiat sit amet porta in.'
    }
]

interface WhyChooseProps{
    className?:string
}
const WhyChoose = ({className=''}: WhyChooseProps) => {
    const { t } = useTranslation()




    return (
        <RowLayout className={`app-why-choose ${className}`} containerClassName="app-why-choose-container">

            <Col span={24}>
                 <Title level={2} align="center">POURQUOI CHOISIR M-GO</Title>   </Col>
            <Col span={24}>
                <Row>

                    {
                        dataSource.map(item => (
                            <Col key={item.title} span={24} lg={{span:8 }}>
                                <WhyChooseItem {...item} />
                            </Col>
                        ))
                    }
                </Row>
            </Col>
        </RowLayout>

    )
}

export default WhyChoose