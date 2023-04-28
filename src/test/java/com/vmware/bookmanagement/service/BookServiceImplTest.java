package com.vmware.bookmanagement.service;

import com.vmware.bookmanagement.model.Book;
import com.vmware.bookmanagement.model.Category;
import com.vmware.bookmanagement.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;


    @Test
    void addBookTest() {
        Book book = new Book();
        book.setId(100l);
        book.setName("abcd");
        book.setAuthor("xyz");
        book.setCategory(Category.POETRY);
        when(bookRepository.findByNameAndAuthor(book.getName(), book.getAuthor())).thenReturn(null);
        when(bookRepository.save(book)).thenReturn(book);
        bookService.addBook(book);
        verify(bookRepository).save(book);
    }

    @Test
    void deleteBookTest() {
        doNothing().when(bookRepository).deleteById(102L);
        bookService.deleteBook(102L);
        Mockito.verify(bookRepository).deleteById(102L);
    }

    @Test
    void getBookByCategoryTest() {
        List<Book> result = getResult();
        Category category = Category.POETRY;
        when(bookRepository.findAllByCategory(category)).thenReturn(result);
        assertEquals(result,bookService.getBookByCategory(category));
    }

    @Test
    void getBookByAuthorTest() {
        List<Book> result = getResult();
        String author = "xyz";
        when(bookRepository.findAllByAuthor(author)).thenReturn(result);
        assertEquals(result,bookService.getBookByAuthor(author));
    }

    @Test
    void getBookByNameTest() {
        List<Book> result = getResult();
        String bookName = "abcd";
        when(bookRepository.findAllByName(bookName)).thenReturn(result);
        assertEquals(result,bookService.getBookByName(bookName));
    }

    private List<Book> getResult()
    {
        List<Book> books = new ArrayList<>();
        Book book = new Book(100l,"abcd", "xyz",Category.POETRY);
        books.add(book);
        return  books;
    }
}