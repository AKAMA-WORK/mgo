export const nestedObjectKeysToUrlSquareBrackets = (
    obj?: Record<string,any>,
    prefix?: string
) => {
    if(!obj){
        return undefined;
    }
    const keys = Object.keys(obj);
    let result : Record<string,any> = {};


    for(const key of keys){
        const value = obj[key];
        if(typeof value==='object'){    
            
            result = {
                ...result,
                ...(nestedObjectKeysToUrlSquareBrackets(value,prefix ? `${prefix}[${key}]`:key))
            }

        }
        else {
            result[prefix?`${prefix}[${key}]`: key] = value;
        }
    }

    return result;

}


export const toURLSearchParams = (obj?:Record<string,any>) => {
    if(!obj){
        return null;
    }

    const searchQuery = new URLSearchParams();
   
    for(const key in obj){
        searchQuery.set(key,obj[key])
    }



    return searchQuery;
}