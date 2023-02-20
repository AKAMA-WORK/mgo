import { Button, Col, Form, Input, Row, Space, Typography } from 'antd'
import React from 'react'
import RowLayout from '~/components/RowLayout';
import Title from '~/components/typography/Title';
import Text from '~/components/typography/Text';

interface JoinUsProps{
    className?:string
}

const JoinUs = ({className=''}:JoinUsProps) => {
    const [form] = Form.useForm();

    const onFinish = () => {
    };

    const onFinishFailed = () => {
    };

    return (
        <div id="join-us">
            <RowLayout containerClassName='app-join-us-container' className={`app-join-us ${className}`}>
                <Col span={24} offset={0} md={{ offset: 6, span: 12 }} className="app-join-us-form-container">
                    <Title level={2} color="color4">
                    AGENCES ! REJOIGNEZ-NOUS
                    </Title>
                    <Text className='app-join-us-description'>
                    Si vous êtes une coopérative et que vous souhaitez proposer vos services sur M-Go, nous vous invitons à laisser votre contact. Nous entrerons en relation avec vous dans les plus bref délais.
                    </Text>

                    <Form
                        form={form}
                        onFinish={onFinish}
                        onFinishFailed={onFinishFailed}
                        autoComplete="off"
                        layout="vertical"

                    >
                        <Row>
                            <Col span={24} lg={{ span: 11 }}>
                                <Form.Item
                                    name="company"
                                    label="VOTRE COOPERATIVE"

                                >
                                    <Input className='app-join-us-form-field'/>
                                </Form.Item>
                            </Col>
                            <Col span={24} offset={0} lg={{ span: 11, offset: 2 }} >
                                <Form.Item
                                    name="telephone"
                                    label="NUMERO TELEPHONE"
                                >
                                    <Input className='app-join-us-form-field' />
                                </Form.Item>
                            </Col>


                        </Row>
                        <Row>

                            <Button type="primary">Envoyer mon contact</Button>
                        </Row>


                    </Form>
                </Col>
            </RowLayout>
        </div>)
}

export default JoinUs