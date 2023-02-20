import { Form, Link, useFetcher } from "@remix-run/react";
import { Affix, Button, Col, Row, Typography } from "antd";
import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import Logo from '~/components/Logo/Logo'
import RowLayout from "~/components/RowLayout";
import useNetwork from "~/hooks/useNetwork";
import Locale, { LocaleProps } from "./Locale/Locale";
import { Alert } from 'antd';
import { User } from "~/types/User";
import LoggedUser from "./LoggedUser/LoggedUser";

type HeaderType = 'white' | 'normal'
const Header = (props: { type?: HeaderType, user?: User } & LocaleProps) => {
    const { t } = useTranslation()
    const network = useNetwork();
    const [successRedirect, setSuccessRedirect] = React.useState<string>('/');

    useEffect(() => {
        setSuccessRedirect(`${window.location.pathname}${window.location.search ? window.location.search : ''}`)
    }, [])

    return (
        <RowLayout containerClassName={props?.type === 'white' ? 'app-header-container-white' : "app-header-container"} className="app-header">

            <div className="app-header-section">
                <Logo />
            </div>
            <div className="app-header-section" id="app-header-right-container">
                <Locale {...props} />

                {
                    props.user ?

                        <LoggedUser user={props.user} />
                        :
                        <>
                            <Form action="/users/signup" method="post">
                                <input type="hidden" name="successRedirect" value={successRedirect} />
                                <Button htmlType="submit" type="link">
                                    <Typography.Text>
                                        {t('signup')}
                                    </Typography.Text>
                                </Button>
                            </Form>

                            <Form action="/users/login" method="post">
                                <input type="hidden" name="successRedirect" value={successRedirect} />
                                <Button className="secondary-btn" htmlType="submit" >
                                    {t('signin')}
                                </Button>
                            </Form>
                        </>
                }

            </div>
            {!network.online && (
                <div className="app-alert-network-state">
                    <Alert
                        // message="Erreur de connexion"
                        description="Vous êtes actuellement hors ligne."
                        type="warning"
                        showIcon
                    /></div>)}
        </RowLayout>
    )
}

export default Header