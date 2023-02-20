export const parseSearchSort = <T>(sort?: Record<string, any>, defaultSort?: T): T | undefined => {
    if (sort) {
        return sort as any || defaultSort;
    }

    return defaultSort;
}
