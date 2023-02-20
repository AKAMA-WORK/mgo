import { useState, useEffect } from 'react';
import { throttle } from 'lodash';

const getDeviceConfig = (width: number): BreakPoint => {
    if (width <= 480) {
        return 'xs';
    } else if (width > 480 && width <= 576) {
        return 'sm';
    } else if (width > 576 && width <= 768) {
        return 'md';

    } else if (width > 768 && width <= 992) {
        return 'lg';

    }
    else if (width > 992 && width <= 1200) {
        return 'xl';
    }
    // else if(width > 1200) {
    return 'xxl';
    //}
};

type BreakPoint = 'xs' | 'sm' | 'md' | 'lg' | 'xl' | 'xxl'
const useBreakpoint = (initialValue?: BreakPoint) => {
    const [brkPnt, setBrkPnt] = useState<BreakPoint>(() => initialValue ? initialValue : typeof window !== 'undefined' ? getDeviceConfig(window.innerWidth) : 'lg');

    useEffect(() => {
        if (typeof window !== 'undefined') {

            const calcInnerWidth = throttle(() => {
                setBrkPnt(getDeviceConfig(window.innerWidth))
            }, 200);
            window.addEventListener('resize', calcInnerWidth);
            return () => window.removeEventListener('resize', calcInnerWidth);
        }
    }, []);

    return brkPnt;
}
export default useBreakpoint;