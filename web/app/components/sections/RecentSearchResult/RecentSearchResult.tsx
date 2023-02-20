import { Col, List, Row, Typography } from "antd";
import React,{useEffect} from "react";
import { useTranslation } from "react-i18next";

import FloatingCard from "~/components/banner/Floating-banner";
import RowLayout from "~/components/RowLayout";
import Title from "~/components/typography/Title";
import { Departure } from "~/types/departure/Departure";
import SearchResult from "./SearchResult";
import banner_card  from "~/assets/images/banner_img.png"
interface RecentSearchResultProps {
    departures: Departure[]
    className?:string
}

const RecentSearchResult = ({ departures,className='' }: RecentSearchResultProps) => {
    const { t } = useTranslation()
  
    return (
        <RowLayout className={`app-recent-search-result ${className}` } containerClassName="app-recent-search-result-container">
            <Title className="app-recent-search-result-title" level={3} color="color2" align="center">
                RECHERCHES RECENTES

            </Title>

            <List
                itemLayout="horizontal"
                grid={{
                    xl: 4,
                    lg: 2,
                    xs: 1
                }}
                dataSource={departures}
                className="app-recent-search-result-departures"
                renderItem={item => (
                    <SearchResult departure={item} />
                )}
            />
            <div className="floating-banner" >
                <FloatingCard img={banner_card} text1="Vous êtes une agence ?" text2="Rejoignez-nous !" />
            </div>
        </RowLayout>

    )
}

export default RecentSearchResult