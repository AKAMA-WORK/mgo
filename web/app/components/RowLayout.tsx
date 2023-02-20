import { Button, Col, Form, Input, Row, Space, Typography } from 'antd'
import React from 'react'


interface RowLayoutProps{
    containerClassName?:string
    className?:string
    children?: any
    wrap?:boolean
}

const RowLayout = (props?: RowLayoutProps) => {
    
    const wrap = props?.wrap===undefined || props.wrap===true 
    return (

        <Row className={props?.containerClassName}>
            <Col span={22} offset={1} lg={{ span: 20, offset: 2 }}>
                {wrap ? (
                    <Row className={props?.className}>
                    {props?.children}
                </Row>

                ): props.children}
                
            </Col>

        </Row>)
}

export default RowLayout