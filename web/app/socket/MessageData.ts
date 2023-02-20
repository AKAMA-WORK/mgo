export interface MessageData{
    personId:number
    clientType: 'WEB' | 'BACK_OFFICE' | 'USSD' | 'FACEBOOK_BOT'
    correlationId:string
}