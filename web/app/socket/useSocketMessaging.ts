import React from "react";
import { useRef } from "react";
import { Socket } from "socket.io-client";
import { useSocket } from "./context";


export interface MessageListener<D> {
    (data: D, socket: Socket): void
}

export interface useSocketMessagingResult {
    emit: <D>(type: string, data: D) => void
    on: <D> (type: string, cb: MessageListener<D>) => void;
    socket?: Socket
}


export const useSocketMessaging = (): useSocketMessagingResult => {
    const socket = useSocket();
    const listeners = useRef<Record<string, MessageListener<any>[]>>({});

    React.useEffect(() => {
        if (!socket) return;

        socket.on("message", (message) => {
            if (message) {
                const asJson = JSON.parse(message);
                if (asJson.type) {
                    listeners.current[asJson.type]?.forEach(cb => cb(asJson.data, socket));
                }
            }

        });

    }, [socket]);


    return {
        socket,
        emit: (type, data) => {
            socket?.emit("message", JSON.stringify({ type, data }));
        },
        on: (type, cb) => {
            listeners.current[type] = [...(listeners.current[type] || []), cb];
        }
    }
}