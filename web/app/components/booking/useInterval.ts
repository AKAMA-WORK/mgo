import { useEffect, useRef } from 'react';

export const useInterval = (callback: () => void, delay?: number) => {
    const callbacRef = useRef<any>();

    // update callback function with current render callback that has access to latest props and state
    useEffect(() => {
        if (callbacRef) {
            callbacRef.current = callback;
        }
    });

    useEffect(() => {
        if (!delay) {
            return () => { };
        }

        const interval = setInterval(() => {
            callbacRef.current && callbacRef.current();
        }, delay);
        return () => clearInterval(interval);
    }, [delay]);
}