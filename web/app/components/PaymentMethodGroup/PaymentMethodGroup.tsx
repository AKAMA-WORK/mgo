import { List, Tooltip, Typography } from "antd"
import { PaymentMethod } from "~/types/transaction/PaymentMethod"
import Title from "../typography/Title"

interface PaymentMethodGroupItem {
    logo: string
    code: PaymentMethod
    name: string
}

interface PaymentMethodGroupProps {
    items: PaymentMethodGroupItem[]
    title: string
    onClick: (code: PaymentMethod) => void
}



const PaymentMethodGroup = ({ items, title, onClick }: PaymentMethodGroupProps) => {

    return (
        <div className="app-payment-method-group">
            <Title level={4} color="color5">{title}</Title>
            <List
                //  grid={{ gutter: 0, column: 1 }}
                dataSource={items}
                renderItem={item => (
                    <List.Item onClick={(event) => onClick(item.code)}>
                        <Tooltip title={item.name}>
                            <img src={item.logo} />
                        </Tooltip>
                    </List.Item>
                )}
            />
        </div>
    )

}

export default PaymentMethodGroup