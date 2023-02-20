import { Typography } from "antd"
import { ReactNode } from "react"
import { ColorType } from "./types"

interface TitleProps {
    children: ReactNode
    align?: 'left' | 'center' | 'right'
    color?: ColorType
    level: 1 | 2 | 3 | 4 | 5
    className?:string
    textTransform?:'uppercase' | string
}

const Title = ({ children, align = 'left', color='color1', textTransform='uppercase', level, className='' }: TitleProps) => {
    return (
        <Typography.Title level={level} className={`app-typography-title-${textTransform} app-typography-${align} ${color} ${className}`}>{children}</Typography.Title>
    )
}

export default Title;