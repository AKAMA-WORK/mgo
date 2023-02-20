import { useSearchParams, useTransition } from "@remix-run/react";
import React, { useState, useEffect } from 'react';
import { Avatar, List, Switch  } from "antd";
import { LikeOutlined, MessageOutlined, StarOutlined } from '@ant-design/icons';
import { SelectSeatsMessageData } from "~/socket/selectSeats/SelectSeatsMessageData";
import {  useSocketMessaging } from "~/socket/useSocketMessaging";
import { Departure } from "~/types/departure/Departure";
import { Paging } from "~/types/Paging";
import { SearchResult } from "~/types/SearchResult";
import DeparturePreview from "./Departure";
import DeparturesPagination from "./DeparturesListPagination";
import Skeleton from "../Skeleton/Skeleton";


interface DeparturesListProps {
    departures?: SearchResult<Departure>
    paging: Paging
    onPagingChange: (paging:Paging) => void
}

const DeparturesList = ({ departures, onPagingChange, paging }: DeparturesListProps) => {
   

    const [params] = useSearchParams();

   /* React.useEffect(() => {
        messaging.on<SelectSeatsMessageData>("SELECT_SEATS", (data) => {
            console.log(`On SELECT_SEATS 1`, data);
        })
    }, []);

    const handleSeatSelection = () => {
        messaging.emit<SelectSeatsMessageData>('SELECT_SEATS', {
            seats: [{
                departureId: 1,
                seatNumber: 3
            }, {
                departureId: 1,
                seatNumber: 2
            }]
        })


    }*/

    const transition = useTransition();
    const loading = transition.state!='idle';

    
    return (
        <>
          
                <div className="app-departures-list">
                
                    {loading ? <Skeleton/> :  <div>
                        {
                            departures?.items.map((departure) => (<DeparturePreview showConfirmButton seatsSelection={params.get('seatsSelection') === 'true'} key={departure.departureId} departure={departure} />))
                        }
                    </div>}
                    <DeparturesPagination paging={paging} onPagingChange={onPagingChange} />
                </div>
          
        </>
    )

}


export default DeparturesList;