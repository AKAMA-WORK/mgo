import { Avatar, Button, List, Space, Typography } from "antd";
import React from "react";
import { useTranslation } from "react-i18next";
import { Departure } from "~/types/departure/Departure";
import { CalendarIcon } from '~/components/icons/calendar-icon'
import { LocationIcon } from '~/components/icons/location-icon'
import { PersonIcon } from '~/components/icons/person-icon'
import moment from "moment";
import Title from "~/components/typography/Title";
import Text from "~/components/typography/Text";

interface SearchResultProps {
    departure: Departure
}
const SearchResult = ({ departure }: SearchResultProps) => {
    const { t } = useTranslation()
    return (
        <List.Item className="app-search-result-container">
            <div className="app-search-result">
                <div className="app-search-result-item">
                    <Space>
                        <div className="app-search-result-item-icon">
                            <LocationIcon />
                        </div>
                        <Title level={5} color="color4" textTransform=''>{departure.from.name} - {departure.to.name}</Title>

                    </Space>
                </div>
                <div className="app-search-result-item">
                    <Space>
                        <div className="app-search-result-item-icon">
                            <CalendarIcon />
                        </div>
                        <Text>
                            {moment(departure.date).format('DD/MM/YYYY')}
                        </Text>
                    </Space>
                </div>
                <div className="app-search-result-item">
                    <Space>
                        <div className="app-search-result-item-icon">
                            <PersonIcon />
                        </div>
                        <Text>
                            1 personne
                        </Text>
                    </Space>

                </div>
            </div>
        </List.Item>


    )
}

export default SearchResult