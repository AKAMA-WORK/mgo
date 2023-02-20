import { Typography } from "antd"
import { ReactNode } from "react"
import { ColorType } from "./types"

interface TextProps {
    children: ReactNode
    align?: 'left' | 'center' | 'right'
    color?:ColorType
    className?:string
}


const Text = ({children,align="left",className='', color="color5"}: TextProps) => {
    return (
        <Typography.Text  className={`app-typography-text app-typography-${align} ${color} ${className}`}>
            {children}
        </Typography.Text>
    )

}

export default Text