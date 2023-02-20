import { Button, Result } from 'antd'
import { ExceptionStatusType } from 'antd/lib/result'
import Document from './Document'

interface ErrorPageProps {
    code: ExceptionStatusType
    title: string
    subTitle: string
}
const ErrorPage = (
    { code, subTitle, title }: ErrorPageProps
) => {


    return (
        <Document title={`${code} ${title}`}>
            <Result
                status={code}
                title={title}
                subTitle={subTitle}
                extra={<Button type="primary" href='/'>Retourner à la page d'accueil</Button>}
            />
        </Document>
    )


}

export default ErrorPage