import { OffsetPaging } from "./OffsetPaging"



export interface Paging extends OffsetPaging {
    currentPage: number
    totalPages:number
}