import { Avatar, Button, Card, List, Space, Typography } from "antd";
import { ReactNode } from "react";
import { useTranslation } from "react-i18next";
import Text from "~/components/typography/Text";
import Title from "~/components/typography/Title";

interface WhyChooseItemProps {
    icon: ReactNode
    title: string
    description: string
}
const WhyChooseItem = ({ icon, title, description }: WhyChooseItemProps) => {
    const { t } = useTranslation()
    return (
        <div className="app-why-choose-item">
                <div className="app-why-choose-item-element">
                    {icon}
                </div>
                <div className="app-why-choose-item-element">
                    <Title level={4} textTransform=''>{title}</Title>
                </div>
                <div className="app-why-choose-item-element">
                    <Text align="center">
                    {description}
                    </Text>
                </div>
        </div>


    )
}

export default WhyChooseItem