package com.rushabh.book_author.repositories;


import com.rushabh.book_author.TestDataUtil;
import com.rushabh.book_author.domain.entities.AuthorEntity;
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
public class AuthorRepositoryImplementationTest {

   private AuthorRepository authorRepository;

    @Autowired
    public AuthorRepositoryImplementationTest(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Test
    public void testThatAuthorCanBeCreated(){
        AuthorEntity author = TestDataUtil.createAuthorA();
        authorRepository.save(author);
        Optional<AuthorEntity> result = authorRepository.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthors(){
        AuthorEntity authorA = TestDataUtil.createAuthorA();
        authorRepository.save(authorA);
        AuthorEntity authorB = TestDataUtil.createAuthorB();
        authorRepository.save(authorB);
        AuthorEntity authorC = TestDataUtil.createAuthorC();
        authorRepository.save(authorC);

        Iterable<AuthorEntity> result = authorRepository.findAll();
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(authorA,authorB,authorC);
    }

    @Test
    public void testThatUpdate(){
        AuthorEntity authorA = TestDataUtil.createAuthorA();
        authorRepository.save(authorA);
        authorA.setName("UPDATED");
        authorRepository.save(authorA);
        Optional<AuthorEntity> result = authorRepository.findById(authorA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);
    }
//
    @Test
    public void TestThatAuthorDelete(){
        AuthorEntity authorA = TestDataUtil.createAuthorA();
        authorRepository.save(authorA);
        authorRepository.deleteById(authorA.getId());
        authorRepository.findById(authorA.getId());
        Optional<AuthorEntity> res = authorRepository.findById(authorA.getId());
        assertThat(res).isEmpty();
    }

    @Test
    public void testAuthorWithLessAge(){
        AuthorEntity authorA = TestDataUtil.createAuthorA();
        authorRepository.save(authorA);
        AuthorEntity authorB = TestDataUtil.createAuthorB();
        authorRepository.save(authorB);
        AuthorEntity authorC = TestDataUtil.createAuthorC();
        authorRepository.save(authorC);

       Iterable<AuthorEntity> result =  authorRepository.ageLessThan(50);
       assertThat(result).containsExactly(authorB,authorC);
    }

    @Test
    public void testAuthorGreaterAge(){
        AuthorEntity authorA = TestDataUtil.createAuthorA();
        authorRepository.save(authorA);
        AuthorEntity authorB = TestDataUtil.createAuthorB();
        authorRepository.save(authorB);
        AuthorEntity authorC = TestDataUtil.createAuthorC();
        authorRepository.save(authorC);
        Iterable<AuthorEntity> result =  authorRepository.findAuthorAgeGreaterThan(50);
        assertThat(result).containsExactly(authorA);

    }

}
