package com.vmware.bookmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiResponse<T> {
    private int status;
    private String message;
    private String timeStamp;
    private Integer currentPage;
    private Integer numberOfBooks;
    private Integer totalPages;
    private Long totalBooks;
    private T data;
}
