import React from "react";
import { DownOutlined, SmileOutlined } from '@ant-design/icons';
import { Dropdown, Menu, Space, Typography } from 'antd';
import frFlag from '~/assets/images/fr.svg'
import mgFlag from '~/assets/images/mg.svg'
import { Button } from 'antd';






export interface LocaleProps {
    value: string
    onChange: (value:string) => void
}

const Locale = ({value, onChange }: LocaleProps) => {

    const handleClickItem = (event: any, locale:string) => {
        event.preventDefault();
        event.stopPropagation();

        onChange(locale)
    }


    const items = [
        {
            key: 'fr',
            label: (
                <a rel="noopener noreferrer" href="#" onClick={(event)=> handleClickItem(event, 'fr')}>
                    FR
                </a>
            ),
            icon: <img src={frFlag} alt="FR" className="current-locale-flag" />
        },
        {
            key: 'mg',
            label: (
                <a rel="noopener noreferrer" href="#" onClick={(event)=> handleClickItem(event, 'mg')}>
                    MG
                </a>
            ),
            icon: <img src={mgFlag} alt="MG" className="current-locale-flag" />,
        },
    
    ]

    const current = items.find(item=>item.key===value )
    return (
        <Dropdown
            overlay={<Menu
                items={items}
            />}
            
        >
            <Button type="link">
                <Space>
                    {current?.icon}
                    <span className="current-locale">{current?.label}</span>
                    <DownOutlined />
                </Space>


            </Button>

        </Dropdown>


    )
}

export default Locale;
