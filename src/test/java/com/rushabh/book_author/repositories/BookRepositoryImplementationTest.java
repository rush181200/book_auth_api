package com.rushabh.book_author.repositories;

import com.rushabh.book_author.TestDataUtil;
import com.rushabh.book_author.domain.entities.AuthorEntity;
import com.rushabh.book_author.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryImplementationTest {

    private BookRepository bookRepository;

    @Autowired
    public BookRepositoryImplementationTest(BookRepository bookRepository){

        this.bookRepository=bookRepository;
    }

    @Test
    public void testThatBook(){
        AuthorEntity author = TestDataUtil.createAuthorA();
        BookEntity book = TestDataUtil.createThatbookA(author);
        bookRepository.save(book);
        Optional<BookEntity> result = bookRepository.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

    }

    @Test
    public void testThatMultiple(){

        AuthorEntity author = TestDataUtil.createAuthorA();


        BookEntity bookA = TestDataUtil.createThatbookA(author);

        bookRepository.save(bookA);
        BookEntity bookB = TestDataUtil.createThatbookB(author);

        bookRepository.save(bookB);
        BookEntity bookC = TestDataUtil.createThatbookC(author);

        bookRepository.save(bookC);

        Iterable<BookEntity> res = bookRepository.findAll();
        assertThat(res).hasSize(3).containsExactly(bookA,bookB,bookC);
    }

    @Test
    public void testThatUpdateBook(){
        AuthorEntity author = TestDataUtil.createAuthorA();


        BookEntity bookA = TestDataUtil.createThatbookA(author);

        bookRepository.save(bookA);

        bookA.setTitle("UPDATED");
        bookRepository.save(bookA);

        Optional<BookEntity> result = bookRepository.findById(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);
    }

    @Test
    public void testThatBookDelete(){
        AuthorEntity author = TestDataUtil.createAuthorA();


        BookEntity bookA = TestDataUtil.createThatbookA(author);

        bookRepository.save(bookA);

        bookRepository.deleteById(bookA.getIsbn());

        Optional<BookEntity> result = bookRepository.findById(bookA.getIsbn());
        assertThat(result).isEmpty();
    }

}
