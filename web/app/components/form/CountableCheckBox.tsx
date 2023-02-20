import React from 'react'
import { Button, Checkbox, CheckboxProps, InputNumber, InputNumberProps, Tooltip } from 'antd'





const CountableCheckBox = (props: CheckboxProps & {count: number }) => {

    return (
        <div className={`app-countable-checkbox ${props.className || ''}`}>
            <div>
                <Checkbox {...props}>{props.children}</Checkbox>
            </div>
            <div className='app-countable-checkbox-count'>{props.count>=1000 ? (
                <Tooltip title={props.count}>
                <span>999+</span>
              </Tooltip>
            ) : props.count}</div>
        </div>

    )
}

export default CountableCheckBox