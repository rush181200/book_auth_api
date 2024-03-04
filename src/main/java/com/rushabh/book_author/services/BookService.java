package com.rushabh.book_author.services;

import com.rushabh.book_author.domain.entities.BookEntity;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity createBook(String isbn,BookEntity book);

    List<BookEntity> findAll();
    Page<BookEntity> findAll(Pageable pageable);

    Optional<BookEntity> findOne(String isbn);

    boolean isExists(String isbn);

    BookEntity partialUpdate(String isbn, BookEntity bookEntity);

    void delete(String isbn);
}
