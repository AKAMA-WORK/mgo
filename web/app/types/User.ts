import { Person } from "./person/Person"

export interface User {
    phoneNumber:string
    userId:string
    email:string
    name:string
    person:Person
}