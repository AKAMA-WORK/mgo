import { Card, Col, Divider, List, Row, Space, Typography } from 'antd';
import React from 'react'
import facebook from '~/assets/images/facebook.svg'
import instagram from '~/assets/images/instagram.svg'
import whatsapp from '~/assets/images/whatsapp.svg'
import logoWhite from '~/assets/images/logo-white.png'
import RowLayout from '~/components/RowLayout';
import { PhoneIcon } from '~/components/icons/phone-icon';
import { EmailIcon } from '~/components/icons/email-icon';
import Logo from '~/components/Logo/Logo';
import Title from '~/components/typography/Title';
import Text from '~/components/typography/Text';

const phoneNumbers = ['034 12 345 67', '034 12 345 67', '034 12 345 67']
const emailAddresses = ['contact@m-go.com']

const links = [{
    title: 'Mentions légales',
    href: '/legal-notice'
}, {
    title: 'CGU',
    href: '/terms-of-use'
}, {
    title: 'FAQ',
    href: '/faq'
}]

const Footer = () => {



    return (
        <RowLayout containerClassName='app-footer' wrap={false}>
            <Row>
                <Col span={24} md={{ span: 8 }}>
                    <Card title={(
                        <Title level={3} color="color3">SUIVEZ-NOUS</Title>
                    )} bordered={false} className="app-footer-group">
                        <Space>
                            <a href='#'>
                                <img src={facebook} alt="Facebook" />
                            </a>
                            <a href='#'>
                                <img src={instagram} alt="Instagram" />
                            </a>
                            <a href='#'>
                                <img src={whatsapp} alt="Whatsapp" />
                            </a>
                        </Space>
                    </Card>
                </Col>
                <Col span={24} md={{ span: 8 }}>
                    <Card
                        title={(
                            <Title level={3} color="color3">CONTACTS</Title>
                        )}

                        bordered={false} className="app-footer-group">
                        <Row>
                            <Col span={4} className="app-footer-contact-icon-container">
                                <PhoneIcon />
                            </Col>
                            <Col span={20}>
                                <List
                                    itemLayout="vertical"
                                    dataSource={phoneNumbers}
                                    renderItem={phone => (
                                        <List.Item className='app-footer-contact-item'>
                                            <a href={`tel:${phone}`}>
                                                <Text color="color6">
                                                    {phone}
                                                </Text>


                                            </a>
                                        </List.Item>
                                    )}
                                />
                            </Col>
                        </Row>
                        <Row>
                            <Col span={4} className="app-footer-contact-icon-container">
                                <EmailIcon />
                            </Col>
                            <Col span={20}>
                                <List
                                    itemLayout="vertical"
                                    dataSource={emailAddresses}
                                    renderItem={address => (
                                        <List.Item className='app-footer-contact-item'>
                                            <a href={`mailto:${address}`}>
                                                <Text color="color6">
                                                    {address}
                                                </Text>

                                            </a>
                                        </List.Item>
                                    )}
                                />
                            </Col>
                        </Row>

                    </Card>
                </Col>
                <Col span={24} md={{ span: 8 }}>
                    <Card
                        title={(
                            <Title level={3} color="color3">LIENS UTILES</Title>
                        )}

                        bordered={false} className="app-footer-group">

                        <List
                            itemLayout="vertical"
                            dataSource={links}
                            renderItem={link => (
                                <List.Item className='app-footer-contact-item'>
                                    <a className='app-footer-contact-item' href={link.href}>
                                        <Text color="color6">
                                            {link.title}
                                        </Text>


                                    </a>
                                </List.Item>
                            )}
                        />
                    </Card>
                </Col>
            </Row>
            <Row gutter={16}>
                <Col span={24}>
                    <hr className='app-footer-divider' />
                </Col>
            </Row>
            <Row gutter={16}>
                <Col span={12}>
                    <Logo type='white' />
                </Col>
                <Col span={12} className='app-footer-copyright'>
                    <Typography.Text className='color6'>Copyright © {new Date().getFullYear()} - M-GO</Typography.Text>
                </Col>
            </Row>
        </RowLayout>

    )

}

export default Footer;