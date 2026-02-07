package com.libreriauniversal;

import java.util.List;

public record PagedResult<T> (
        List<T> items,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean isFirst,
        boolean isLast,
        boolean hasNext,
        boolean hasPrevious,
        boolean isEmpty
){ }