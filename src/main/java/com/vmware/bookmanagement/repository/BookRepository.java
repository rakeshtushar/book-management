package com.vmware.bookmanagement.repository;

import com.vmware.bookmanagement.model.Book;
import com.vmware.bookmanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Book findById(long id);

    @Query("select b from Book b where b.name= :bookName and b.author= :authorName")
    Book findByNameAndAuthor(@Param("bookName") String name, @Param("authorName") String author);
    List<Book> findAllByCategory(Category category);
    List<Book> findAllByAuthor(String author);
    List<Book> findAllByName(String name);
}
