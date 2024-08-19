export const debounce=<T> (fn:() => T, delay: number) => {
    let timeoutId: any;
    return () => {
        if (timeoutId) {
            clearTimeout(timeoutId);
        }
        timeoutId=setTimeout(fn, delay);
    }
}
