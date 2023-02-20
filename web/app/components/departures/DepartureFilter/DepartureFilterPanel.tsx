import { Button, Checkbox, CheckboxProps, InputNumber, InputNumberProps, Tooltip } from 'antd'
import React from 'react'
import CountableCheckBox from '~/components/form/CountableCheckBox'


export interface DepartureFilterItem {
    id: any
    label: string
    count: number
}

interface DepartureFilterPanelProps {
    data: DepartureFilterItem[]
    total: number
    loadMoreMessage?: string
    
    onChange?: (value: any[]) => void;
    value?: string[]
}


const DepartureFilterPanel = ({ data, total, loadMoreMessage, onChange, value }: DepartureFilterPanelProps) => {
    return (
        <>   
           
            <div>
            {
                    data.map((item, index) => (
                        <CountableCheckBox key={item.id} className="app-departure-filter-item" onChange={event => {
                            if (event.target.checked) {
                                onChange && onChange([...(value || []), item.id])
                            }
                            else {
                                onChange && onChange((value || []).filter(id => id !== item.id))
                            }
                        }} checked={value?.includes(item.id)} count={item.count}>
                            {item.label}
                        </CountableCheckBox>
                    ))
            }
            </div>
            
            {
                data.length < total && (
                    <div>
                        <Button type="link">{loadMoreMessage}</Button>
                    </div>
                )
            }
        </>)
}

export default DepartureFilterPanel