package com.microservice.inventario.shared.response.pagination;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Value
@Builder
public class PaginaResult<T> {

    List<T> items;
    int pageNumber;
    int pageSize;
    long totalElements;
    int totalPages;
    boolean isFirst;
    boolean isLast;
    boolean hasNext;
    boolean hasPrevious;
    boolean isEmpty;

    public static <T> PaginaResult<T> of(
            List<T> items,
            int pageNumber,
            int pageSize,
            long totalElements
    ) {
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);

        return PaginaResult.<T>builder()
                .items(items)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .isFirst(pageNumber == 0)
                .isLast(pageNumber >= totalPages - 1)
                .hasNext(pageNumber < totalPages - 1)
                .hasPrevious(pageNumber > 0)
                .isEmpty(items.isEmpty())
                .build();
    }

    public <R> PaginaResult<R> map(Function<? super T, ? extends R> mapper) {
        List<R> nuevoContenido = this.items.stream()
                .map(mapper)
                .collect(Collectors.toList());

        return PaginaResult.<R>builder()
                .items(nuevoContenido)
                .pageNumber(this.pageNumber)
                .pageSize(this.pageSize)
                .totalElements(this.totalElements)
                .totalPages(this.totalPages)
                .isFirst(this.isFirst)
                .isLast(this.isLast)
                .hasNext(this.hasNext)
                .hasPrevious(this.hasPrevious)
                .isEmpty(nuevoContenido.isEmpty())
                .build();
    }
}