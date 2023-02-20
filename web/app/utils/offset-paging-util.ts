import { OffsetPaging } from "~/types/OffsetPaging";
import { Paging } from "~/types/Paging";

export const parseSearchOffsetPaging = (paging: Record<string, any>, defaultLimit: number): OffsetPaging => {
    if (paging) {
        return {
            offset: paging?.offset ? parseInt(paging.offset, 10) : 0,
            limit: paging?.limit ? parseInt(paging.limit, 10) : defaultLimit,
        }
    }

    return {
        offset: 0,
        limit: defaultLimit
    };
}


export const computePaging = ({ offset, limit, totalCount }: OffsetPaging & { totalCount: number }): Paging => {


    return {
        offset, limit,
        totalPages: Math.ceil(totalCount / limit),
        currentPage: Math.ceil(offset / limit) + 1
    }


}
