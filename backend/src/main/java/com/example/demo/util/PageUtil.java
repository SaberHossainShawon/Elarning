package com.example.demo.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

public class PageUtil {
    public static Pageable getPageable(Integer page, Integer size, String sort, String order) {
        page = page == null ? 0 : page;
        size = size == null ? 10 : size;
        sort = sort == null ? "id" : sort;
        order = order == null ? "ASC" : order;
        return PageRequest.of(page, size, Direction.valueOf(order.toUpperCase()), sort);

    }
}
