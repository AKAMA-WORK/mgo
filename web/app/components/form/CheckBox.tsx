import React from 'react'
import { Button, Checkbox, CheckboxProps, InputNumber, InputNumberProps, Tooltip } from 'antd'





const CheckBox = (props: CheckboxProps) => {

    return (
        <div className={`app-countable-checkbox ${props.className || ''}`}>
            <div>
                <Checkbox {...props}>{props.children}</Checkbox>
            </div>
        </div>

    )
}

export default CheckBox