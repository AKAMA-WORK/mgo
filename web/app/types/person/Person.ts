import { Civility } from "../Civility"

export interface Person {
    personId:string
    civility:Civility
    firstName?:string
    lastName?:string
    phoneNumber?:string
    idNumber?:string
    idIssueDate?:string
    idIssueLocation?:string
    idDuplicateDate?:string
    idDuplicateLocation?:string
    idType?:"PASSPORT" | "CIN"| "CARTE_ETUDIANT"
    userId?:string
}