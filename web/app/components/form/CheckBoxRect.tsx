import React from 'react'
import { Button, Checkbox, CheckboxProps, InputNumber, InputNumberProps, Tooltip } from 'antd'





const CheckBoxRect = (props: CheckboxProps) => {

    return (

        <div className="app-checkbox">

            <div className="checkbox">
                <Checkbox className="app-checkboxRect"> Tout sélectionner</Checkbox>
                <Checkbox className="app-checkboxRect"> Tout Effacer</Checkbox>
            </div>
           
        </div>

    )
}

export default CheckBoxRect