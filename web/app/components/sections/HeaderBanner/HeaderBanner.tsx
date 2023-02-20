import { Link } from "@remix-run/react";
import { Affix, Button, Col, Row, Typography } from "antd";
import React, { useState } from "react";
import { useTranslation } from "react-i18next";
import RowLayout from "~/components/RowLayout";
import { useDisplayBanner } from "~/hooks/useDisplayBanner";
import { User } from "~/types/User";
import Header from "../Header/Header";
import { LocaleProps } from "../Header/Locale/Locale";

const HeaderBanner = (props: LocaleProps & { user?: User }) => {
    const { t } = useTranslation()
    const [affixed, setAffixed] = useState<boolean | undefined>(false)
    const displayBanner = useDisplayBanner();

    const header = (
        <Affix offsetTop={0} onChange={setAffixed}>
            <Header {...props} type={affixed ? 'white' : 'normal'} />
        </Affix>
    )
    if (!displayBanner) {
        return header
    }
    return (
        <div id="app-parallax-bg">
            {header}
            <RowLayout className="app-header-banner" containerClassName="app-header-banner-container">
                <Typography.Title className="app-header-banner-title">
                    Voyagez où vous voulez
                </Typography.Title>
                <Typography.Paragraph className="app-header-banner-description">
                    Proin nunc mi, pellentesque sed condimentum sed, iaculis in massa.
                </Typography.Paragraph>
            </RowLayout>
        </div>
    )
}

export default HeaderBanner