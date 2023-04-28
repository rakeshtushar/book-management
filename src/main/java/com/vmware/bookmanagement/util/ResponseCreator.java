package com.vmware.bookmanagement.util;

import com.vmware.bookmanagement.dto.ApiResponse;
import com.vmware.bookmanagement.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface ResponseCreator<T> {
    ApiResponse createResponse(HttpStatus status, String message);
    ApiResponse createResponse(HttpStatus status, String message, T t);
    ApiResponse<Book> createResponse(T t);
    ApiResponse<List<T>> createResponse(List<T> t);
    ApiResponse<List<T>> createResponse(Page<T> t);

}
