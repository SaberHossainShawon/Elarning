export class PageRequest {
    pageNumber: number;
    pageSize: number;
    sortColumn?: string;
    search?: string;
    order?: string;
    constructor(pageNumber: number, pageSize: number, sortColumn?: string, search?: string, order?: string) {
        this.pageNumber = pageNumber || 0;
        this.pageSize = pageSize || 10;
        this.sortColumn = sortColumn || 'id';
        this.search = search || '*';
        this.order = order || 'asc';
    }

    getRequestPath(): string {
        return `${this.pageNumber}/${this.pageSize}/${this.sortColumn || 'id'}/${this.order || 'asc'}/${this.search || '*'}`;
    }
}