package com.rushabh.book_author.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rushabh.book_author.TestDataUtil;
import com.rushabh.book_author.domain.dto.BookDto;
import com.rushabh.book_author.domain.entities.BookEntity;
import com.rushabh.book_author.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {
    private MockMvc mockMvc;

    private BookService bookService;
    private ObjectMapper objectMapper;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper,BookService bookService) {
        this.mockMvc = mockMvc;
        this.bookService=bookService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateBookReturnsHttpStatus201() throws Exception {
        BookDto bookDto = TestDataUtil.createThatbookDtoA(null);
        String createBookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void testThatCreateBookReturnsCreateBook() throws Exception {
        BookDto bookDto = TestDataUtil.createThatbookDtoA(null);
        String createBookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        );

    }

    @Test
    public void testThatListBookReturnsHttpStatus200() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testThatListBooksReturnsBook() throws Exception{
        BookEntity bookEntity = TestDataUtil.createThatbookA(null);
        bookService.createBook(bookEntity.getIsbn(),bookEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value("978-1-2345")

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value("The Shadow is Attic")
        );
    }

    @Test
    public void testThatisbnBookReturnsHttpStatus200whenbookexits() throws Exception {
        BookEntity bookEntity = TestDataUtil.createThatbookA(null);
        bookService.createBook(bookEntity.getIsbn(),bookEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void testThatisbnBookReturnsHttpStatus404whenbookdoesexits() throws Exception {
        BookEntity bookEntity = TestDataUtil.createThatbookA(null);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isNotFound());

    }
}
