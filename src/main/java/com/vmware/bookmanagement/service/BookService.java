package com.vmware.bookmanagement.service;

import com.vmware.bookmanagement.model.Book;
import com.vmware.bookmanagement.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> addBook(Book book);
    void deleteBook(long id);
    Book getBook(long id);
    List<Book> getBookByCategory(Category category);
    List<Book> getBookByAuthor(String author);
    List<Book> getBookByName(String name);
    Page<Book> getAllBooks();
    Page<Book> getAllBooks(int offset, int pageSize);

}
