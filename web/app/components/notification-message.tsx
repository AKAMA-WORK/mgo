import * as React from 'react'
import { useState } from 'react'
import { useSearchParams } from 'react-router-dom'
import { AnimatePresence, motion } from 'framer-motion'
import { PlusIcon } from './icons/plus-icon'

function NotificationMessage({
    queryStringKey,
    visibleMs = 8000,
    visible: controlledVisible,
    autoClose,
    children,
    position = 'bottom-right',
    /* how long to wait before the message is shown, after mount 0 to 1 */
    delay = typeof controlledVisible === 'undefined' ? 1 : 0,
}: {
    queryStringKey?: string
    children?: React.ReactNode | React.ReactNode[]
    position?: 'bottom-right' | 'top-center'
    // make the visibility controlled
    visible?: boolean
    delay?: number
} & (
        | { autoClose: false; visibleMs?: never }
        | { visibleMs?: number; autoClose?: never }
    )) {
    const [searchParams] = useSearchParams()
    const hasQueryStringValue = queryStringKey
        ? searchParams.has(queryStringKey)
        : false
    const [isVisible, setIsVisible] = useState(
        !queryStringKey || hasQueryStringValue,
    )
    const messageFromQuery = queryStringKey && searchParams.get(queryStringKey)
    // Eslint is wrong here, params.get can return an empty string
    // eslint-disable-next-line @typescript-eslint/prefer-nullish-coalescing
    const message = messageFromQuery || children
    const latestMessageRef = React.useRef(message)

    // if the query gets a message after the initial mount then we want to toggle visibility
    React.useEffect(() => {
        if (hasQueryStringValue) setIsVisible(true)
    }, [hasQueryStringValue])

    React.useEffect(() => {
        latestMessageRef.current = message
    }, [message])

    React.useEffect(() => {
        if (!latestMessageRef.current) return
        if (autoClose === false) return
        if (controlledVisible === false) return

        const timeout = setTimeout(() => {
            setIsVisible(false)
        }, visibleMs + delay)

        return () => clearTimeout(timeout)
    }, [queryStringKey, delay, autoClose, controlledVisible, visibleMs])

    React.useEffect(() => {
        if (!latestMessageRef.current) return
        if (queryStringKey && searchParams.has(queryStringKey)) {
            const newSearchParams = new URLSearchParams(searchParams)
            newSearchParams.delete(queryStringKey)

            // use setSearchParams from useSearchParams resulted in redirecting the
            // user to the homepage (wut?) and left a `?` at the end of the URL even
            // if there aren't any other search params. This doesn't have either of
            // those issues.
            window.history.replaceState(
                null,
                '',
                [window.location.pathname, newSearchParams.toString()]
                    .filter(Boolean)
                    .join('?'),
            )
        }
    }, [queryStringKey, searchParams])

    const initialY = position.includes('bottom') ? 50 : -50
    const show =
        message && typeof controlledVisible === 'boolean'
            ? controlledVisible
            : isVisible

    return (
        <AnimatePresence>
            {show ? (
                <motion.div
                    initial={{ y: initialY, opacity: 0 }}
                    animate={{ y: 0, opacity: 1, transition: { delay } }}
                    exit={{ y: initialY, opacity: 0 }}
                    transition={{ ease: 'easeInOut', duration: 0.3 }}
                >
                    <div
                        style={{
                            position: 'fixed',
                            bottom: 0,
                            right: 10
                        }}
                    >
                        <div>
                            {typeof controlledVisible === 'undefined' ? (
                                <button
                                    aria-label="dismiss message"
                                    onClick={() => setIsVisible(false)}
                                >
                                    <PlusIcon />
                                </button>
                            ) : null}
                            {message}
                        </div>
                    </div>
                </motion.div>
            ) : null}
        </AnimatePresence>
    )
}

export { NotificationMessage }
