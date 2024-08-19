export interface Sort {

    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
    sortColumn?: string;

}
export interface Page<T> {

    totalPages: number;
    totalElements: number;
    first: boolean;
    last: boolean;
    size: number;
    content: []
    number: number;
    sort: Sort;
    numberOfElements: number;
    pageable: Pageable;
    empty: boolean;
}

export interface Pageable {
    pageNumber: number;
    pageSize: number;
    sort: Sort;
    offset: number;
    paged: boolean;
    unpaged: boolean;
}

export const defaultPage: Page<any> = {
    "totalPages": 0,
    "totalElements": 0,
    "first": true,
    "last": true,
    "size": 0,
    "content": [],
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": true,
        "unsorted": true
    },
    "numberOfElements": 0,
    "pageable": {
        "pageNumber": 0,
        "pageSize": 0,
        "sort": {
            "empty": true,
            "sorted": true,
            "unsorted": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": true
    },
    "empty": true
}

