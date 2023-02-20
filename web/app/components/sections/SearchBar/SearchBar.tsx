import { AutoComplete, Button, Col, Dropdown, Form, Row, Space, DatePicker, Menu, Input, Typography, DatePickerProps } from 'antd'
import { City } from '~/types/City';
import { useSubmit } from '@remix-run/react';

import { extractSearchDepartureParamsFromForm } from '~/utils/departure-util';
import SearchBarContent from './SearchBarContent';

const dateFormat = 'DD/MM/YYYY';

interface DateFormatFactory {
    (prefix: string): DatePickerProps['format']
}

const customFormat: DateFormatFactory = (prefix: string) => {

    return value =>
        `${prefix} ${value.format(dateFormat)}`;
}

interface SearchBarProps {
    className?: string
    full?: boolean
    cities: City[]
    initialValues?: Record<string, any>
}




const DEFAULT_INITIAL_VALUES = {
    seats: 1,
    transportation: 'all',
    type: 'one-way'
}
function SearchBar(props?: SearchBarProps) {
    const initialValues = props?.initialValues || DEFAULT_INITIAL_VALUES;
    const [form] = Form.useForm();
    Form.useWatch('transportation', form);
    Form.useWatch('type', form);
    Form.useWatch('seats', form);
    Form.useWatch('from', form);
    Form.useWatch('to', form);
    Form.useWatch('outwardDate', form);
    Form.useWatch('returnDate', form);

    const submit = useSubmit();

    const onFinish = () => {
    };

    const onFinishFailed = () => {
    };


    const handleSearch = () => {
        submit(extractSearchDepartureParamsFromForm(form), { method: 'get', action: `/departures` })
    }
     
   

    return (
        <div className={props?.className}>
            <Form
                form={form}
                onFinish={onFinish}
                onFinishFailed={onFinishFailed}
                initialValues={initialValues}
                onChange={props?.full? handleSearch:undefined}
                autoComplete="off"
                layout="vertical"

            >
                <SearchBarContent cities={props?.cities || []} full={props?.full} form={form} onSearch={handleSearch}/>
            </Form>
            </div>
    )
}

export default SearchBar 
