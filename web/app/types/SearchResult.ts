export interface SearchResult<T,A=any> {
    items: T[]
    totalCount:number
    aggregate?:A
}