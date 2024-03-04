package com.rushabh.book_author;

import com.rushabh.book_author.domain.dto.AuthorDto;
import com.rushabh.book_author.domain.dto.BookDto;
import com.rushabh.book_author.domain.entities.AuthorEntity;
import com.rushabh.book_author.domain.entities.BookEntity;

public class TestDataUtil {
    private TestDataUtil(){

    }

    public static BookDto createTestBookDtoA(final AuthorDto authorDto) {
        return BookDto.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(authorDto)
                .build();
    }

    public static AuthorEntity createAuthorA() {
        return AuthorEntity.builder()
                .id(1L)
                .name("Rushabh")
                .age(80)
                .build();
    }

    public static AuthorEntity createAuthorB() {
        return AuthorEntity.builder()
                .id(2L)
                .name("Thom")
                .age(20)
                .build();
    }
    public static AuthorEntity createAuthorC() {
        return AuthorEntity.builder()
                .id(3L)
                .name("Hesse")
                .age(30)
                .build();
    }

    public static AuthorDto createTestAuthorDtoA() {
        return AuthorDto.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static BookEntity createThatbookA(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("978-1-2345")
                .title("The Shadow is Attic")
                .author(author).build();
    }

    public static BookDto createThatbookDtoA(final AuthorDto author) {
        return BookDto.builder()
                .isbn("978-1-2345")
                .title("The Shadow is Attic")
                .author(author).build();
    }
    public static BookEntity createThatbookB(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("978-1-2346")
                .title("Shadow")
                .author(author).build();
    }
    public static BookEntity createThatbookC(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("978-1-2347")
                .title("ALONE")
                .author(author).build();
    }
}
