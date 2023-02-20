import { Outlet, useLoaderData } from "@remix-run/react";
import { Col, Layout } from "antd";
import { Content } from "antd/lib/layout/layout";
import React from "react";
import DepartureSummary from "~/components/booking/DepartureSummary";
import RowLayout from "~/components/RowLayout";
import { DeparturesBookingLoaderData } from "~/route-containers/departures-booking/departures-booking.server";
export { loader } from "~/route-containers/departures-booking/departures-booking.server"

const ValidationBooking = () => {
    const data = useLoaderData<DeparturesBookingLoaderData>();

    return (
        <div className="app-booking-validation">
            <div className="app-booking-validation-header-container" >
                <RowLayout className="app-booking-validation-header" wrap={true}>
                    <Col span={24}>
                        <DepartureSummary departuresBooking={data}/>
                    </Col>
                </RowLayout>
            </div>
            <div className="app-booking-validation-content-container" >
                <RowLayout className="app-booking-validation-content" wrap={true}>
                    <Col span={24}>
                        <Outlet />
                    </Col>
                  
                </RowLayout>
            </div>
          
        </div>
    )

}


export default ValidationBooking;