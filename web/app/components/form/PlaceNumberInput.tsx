import React from 'react'
import { Button, InputNumber, InputNumberProps } from 'antd'


const Plus = () => {

    return (<svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M7.99996 0C6.41771 0 4.87099 0.469192 3.5554 1.34824C2.2398 2.22729 1.21442 3.47672 0.608922 4.93853C0.00342049 6.40034 -0.155006 8.00887 0.153676 9.56072C0.462357 11.1126 1.22428 12.538 2.3431 13.6569C3.46192 14.7757 4.88739 15.5376 6.43924 15.8463C7.99108 16.155 9.59962 15.9965 11.0614 15.391C12.5232 14.7855 13.7727 13.7602 14.6517 12.4446C15.5308 11.129 16 9.58225 16 8C16 5.87827 15.1571 3.84344 13.6568 2.34315C12.1565 0.842855 10.1217 0 7.99996 0V0ZM12.8 8.8C12.8 9.01217 12.7157 9.21566 12.5656 9.36569C12.4156 9.51571 12.2121 9.6 12 9.6H9.59996V12C9.59996 12.2122 9.51567 12.4157 9.36564 12.5657C9.21561 12.7157 9.01213 12.8 8.79996 12.8H7.19996C6.98778 12.8 6.7843 12.7157 6.63427 12.5657C6.48424 12.4157 6.39996 12.2122 6.39996 12V9.6H3.99996C3.78778 9.6 3.5843 9.51571 3.43427 9.36569C3.28424 9.21566 3.19996 9.01217 3.19996 8.8V7.2C3.19996 6.98783 3.28424 6.78434 3.43427 6.63431C3.5843 6.48429 3.78778 6.4 3.99996 6.4H6.39996V4C6.39996 3.78783 6.48424 3.58434 6.63427 3.43431C6.7843 3.28429 6.98778 3.2 7.19996 3.2H8.79996C9.01213 3.2 9.21561 3.28429 9.36564 3.43431C9.51567 3.58434 9.59996 3.78783 9.59996 4V6.4H12C12.2121 6.4 12.4156 6.48429 12.5656 6.63431C12.7157 6.78434 12.8 6.98783 12.8 7.2V8.8Z" fill="#1276A1" />
    </svg>
    )
}

const Minus = () => {

    return (<svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M8 0C6.41775 0 4.87104 0.469192 3.55544 1.34824C2.23985 2.22729 1.21447 3.47672 0.608967 4.93853C0.00346626 6.40034 -0.15496 8.00887 0.153721 9.56072C0.462403 11.1126 1.22433 12.538 2.34315 13.6569C3.46197 14.7757 4.88743 15.5376 6.43928 15.8463C7.99113 16.155 9.59966 15.9965 11.0615 15.391C12.5233 14.7855 13.7727 13.7602 14.6518 12.4446C15.5308 11.129 16 9.58225 16 8C16 5.87827 15.1571 3.84344 13.6569 2.34315C12.1566 0.842855 10.1217 0 8 0V0ZM12.8 8.8C12.8 9.01217 12.7157 9.21566 12.5657 9.36569C12.4157 9.51571 12.2122 9.6 12 9.6H4C3.78783 9.6 3.58435 9.51571 3.43432 9.36569C3.28429 9.21566 3.2 9.01217 3.2 8.8V7.2C3.2 6.98783 3.28429 6.78434 3.43432 6.63431C3.58435 6.48429 3.78783 6.4 4 6.4H12C12.2122 6.4 12.4157 6.48429 12.5657 6.63431C12.7157 6.78434 12.8 6.98783 12.8 7.2V8.8Z" fill="#1276A1" />
    </svg>
    )
}


type ValueType = number | string
const PlaceNumberInput = <T extends ValueType = ValueType>(props: InputNumberProps<T> & {controls?:boolean}) => {


    const handleMinus:React.MouseEventHandler<HTMLElement> = (event)=> {
        event.preventDefault();
        event.stopPropagation();

        if(props.onChange){
            const newValue = ((props.value || 0) as number  - 1) as T 
            if(props.min===undefined || newValue>=props.min ){
                props.onChange(newValue );
            }
        }


        
    }

    const handlePlus:React.MouseEventHandler<HTMLElement> = (event)=> {
        event.preventDefault();
        event.stopPropagation();

        
        if(props.onChange){
            const newValue = ((props.value || 0) as number  + 1) as T 
            if(props.max===undefined || newValue<=props.max ){
                props.onChange(newValue);
            }
        }
    }
    return (
        <InputNumber
            className='app-place-number-input'
            addonBefore={ props.controls ? <Button className='app-place-number-input-stepper' type="link" icon={<Minus />} 
                onClick={handleMinus}
            />:undefined}
            addonAfter={props.controls ? <Button className='app-place-number-input-stepper' type="link"
            onClick={handlePlus}
            icon={<Plus />} />: undefined}
            defaultValue={1 as any}
            controls={false}
            {...props}
        />

    )
}

export default PlaceNumberInput