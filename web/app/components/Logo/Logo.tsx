import { Typography } from 'antd'
import React from 'react'
import { LogoIcon } from '../icons/logo-icon';
import { LogoWhiteIcon } from '../icons/logo-white-icon'

type LogoType='white' | 'normal';
function Logo(props? :{type?: LogoType}) {


    const colorClassName =  props?.type==='white'?'color6':'color1'
    return (
        <a href='/' className='app-logo'>
           {props?.type==='white'? <LogoWhiteIcon/>: <LogoIcon/>}
            <span className={`app-logo-name ${colorClassName}`}>
                m-go
            </span>
        </a>
    )
}

export default Logo 
