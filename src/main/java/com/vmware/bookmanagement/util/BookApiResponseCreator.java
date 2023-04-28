package com.vmware.bookmanagement.util;

import com.vmware.bookmanagement.dto.ApiResponse;
import com.vmware.bookmanagement.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookApiResponseCreator implements ResponseCreator<Book>{

    private ThreadLocal<ApiResponse<Book>> threadLocalBook = new ThreadLocal<ApiResponse<Book>>() {
        @Override
        protected ApiResponse initialValue() {
            // Provide the initial value for the thread-local variable
            ApiResponse<Book> apiResponse = ApiResponse.<Book>builder()
                    .status(HttpStatus.OK.value())
                    .message("Successful")
                    .build();
            return apiResponse;
        }
    };

    private ThreadLocal<ApiResponse<List<Book>>> threadLocalListOfBooks = new ThreadLocal<>(){
        @Override
        protected ApiResponse<List<Book>> initialValue() {
            // Provide the initial value for the thread-local variable
            ApiResponse<List<Book>> apiResponse = ApiResponse.<List<Book>>builder()
                    .status(HttpStatus.OK.value())
                    .message("Successful")
                    .build();
            return apiResponse;
        }
    };

    @Override
    public ApiResponse createResponse(HttpStatus status, String message) {
        ApiResponse apiResponse = threadLocalBook.get();
        apiResponse.setTimeStamp(DateTimeCalculator.getCurrentTime());
        apiResponse.setStatus(status.value());
        apiResponse.setMessage(message);
        return apiResponse;
    }

    @Override
    public ApiResponse<Book> createResponse(HttpStatus status, String message, Book book) {
        ApiResponse<Book> apiResponse = threadLocalBook.get();
        apiResponse.setTimeStamp(DateTimeCalculator.getCurrentTime());
        apiResponse.setStatus(status.value());
        apiResponse.setMessage(message);
        apiResponse.setData(book);
        return apiResponse;
    }

    @Override
    public ApiResponse<Book> createResponse(Book book) {
        ApiResponse<Book> apiResponse = threadLocalBook.get();
        apiResponse.setTimeStamp(DateTimeCalculator.getCurrentTime());
        apiResponse.setData(book);
        return apiResponse;
    }

    @Override
    public ApiResponse<List<Book>> createResponse(List<Book> t) {
        ApiResponse<List<Book>> apiResponse = threadLocalListOfBooks.get();
        apiResponse.setData(t);
        apiResponse.setTimeStamp(DateTimeCalculator.getCurrentTime());
        apiResponse.setNumberOfBooks(t.size());
        return apiResponse;
    }

    @Override
    public ApiResponse<List<Book>> createResponse(Page<Book> t) {
        ApiResponse<List<Book>> apiResponse = threadLocalListOfBooks.get();
        apiResponse.setCurrentPage(t.getNumber());
        apiResponse.setNumberOfBooks(t.getNumberOfElements());
        apiResponse.setTotalPages(t.getTotalPages());
        apiResponse.setTotalBooks(t.getTotalElements());
        apiResponse.setData(t.getContent());
        apiResponse.setTimeStamp(DateTimeCalculator.getCurrentTime());
        return apiResponse;
    }
}
