package com.vmware.bookmanagement.controller;

import com.vmware.bookmanagement.dto.ApiResponse;
import com.vmware.bookmanagement.model.Book;
import com.vmware.bookmanagement.model.Category;
import com.vmware.bookmanagement.service.BookService;
import com.vmware.bookmanagement.util.ResponseCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private ResponseCreator<Book> responseCreator;

    @PostMapping
    public ApiResponse<Book> addBook(@RequestBody @Valid Book book)
    {
        Optional<Book> optional = service.addBook(book);
        if(optional.isEmpty())
        {
            return responseCreator.createResponse(HttpStatus.CREATED, "created successfully");
        }
        else{
            return responseCreator.createResponse(HttpStatus.OK, "Already added this book", optional.get());
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable long id)
    {
        service.deleteBook(id);
        return responseCreator.createResponse(HttpStatus.OK, "deleted successfully");
    }
    @GetMapping("/{id}")
    public ApiResponse<Book> getBook(@PathVariable long id)
    {
        Book book = service.getBook(id);
        if(book == null)
        {
            return responseCreator.createResponse(HttpStatus.BAD_REQUEST, "Invalid id : "+id);
        }
        else{
            return responseCreator.createResponse(book);
        }
    }

    @GetMapping("/category/{cat}")
    public ApiResponse<List<Book>> getBooksByCategory(@PathVariable Category cat)
    {
        List<Book> list = service.getBookByCategory(cat);
        if(list.isEmpty())
        {
            return responseCreator.createResponse(HttpStatus.BAD_REQUEST, "No Entry found for the category : "+cat);
        }
        else{
            return responseCreator.createResponse(list);
        }
    }

    @GetMapping("/author/{authorName}")
    public ApiResponse<List<Book>> getBooksByAuthor(@PathVariable String authorName)
    {
        List<Book> list = service.getBookByAuthor(authorName);
        if(list.isEmpty())
        {
            return responseCreator.createResponse(HttpStatus.BAD_REQUEST, "No Entry found for the author : "+authorName);
        }
        else{
            return responseCreator.createResponse(list);
        }
    }

    @GetMapping("/name/{bookName}")
    public ApiResponse<List<Book>> getBooksByName(@PathVariable String bookName)
    {
        List<Book> list = service.getBookByName(bookName);
        if(list.isEmpty())
        {
            return responseCreator.createResponse(HttpStatus.BAD_REQUEST, "No Entry found for the book name : "+bookName);
        }
        else{
            return responseCreator.createResponse(list);
        }
    }

    @GetMapping
    public ApiResponse<List<Book>> getBooks()
    {
        Page<Book> pages = service.getAllBooks();
        return responseCreator.createResponse(pages);
    }

    @GetMapping("/{offset}/{pageSize}")
    public ApiResponse<List<Book>> getBooks(@PathVariable int offset, @PathVariable int pageSize)
    {
        Page<Book> pages = service.getAllBooks(offset, pageSize);
        return responseCreator.createResponse(pages);
    }
}
