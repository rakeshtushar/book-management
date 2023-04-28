package com.vmware.bookmanagement.service;

import com.vmware.bookmanagement.model.Book;
import com.vmware.bookmanagement.model.Category;
import com.vmware.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository repository;
    @Override
    public Optional<Book> addBook(Book book) {
        String name = book.getName();
        String author = book.getAuthor();
        Book exist = repository.findByNameAndAuthor(name, author);
        if(exist != null)
        {
            return Optional.of(exist);
        }
        else{
            repository.save(book);
            return Optional.empty();
        }

    }

    @Override
    public void deleteBook(long id) {
        repository.deleteById(id);
    }

    @Override
    public Book getBook(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Book> getBookByCategory(Category category) {
        return repository.findAllByCategory(category);
    }

    @Override
    public List<Book> getBookByAuthor(String author) {
        return repository.findAllByAuthor(author);
    }

    @Override
    public List<Book> getBookByName(String name) {
        return repository.findAllByName(name);
    }
    @Override
    public Page<Book> getAllBooks() {
        return getAllBooksWIthPagination(0,100);
    }

    @Override
    public Page<Book> getAllBooks(int offset,int pageSize) {
        return getAllBooksWIthPagination(offset,pageSize);
    }
    private Page<Book> getAllBooksWIthPagination(int offset,int pageSize){
        Page<Book> products = repository.findAll(PageRequest.of(offset, pageSize));
        return  products;
    }
}
