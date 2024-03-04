package com.rushabh.book_author.controller;


import com.rushabh.book_author.domain.dto.AuthorDto;
import com.rushabh.book_author.domain.entities.AuthorEntity;
import com.rushabh.book_author.mappers.Mapper;
import com.rushabh.book_author.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    private AuthorService authorService;
    private Mapper<AuthorEntity,AuthorDto> authorEntityAuthorDtoMapper;
    public AuthorController(AuthorService authorService,Mapper<AuthorEntity,AuthorDto> authorEntityAuthorDtoMapper){
        this.authorService=authorService;
        this.authorEntityAuthorDtoMapper=authorEntityAuthorDtoMapper;
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author){
        AuthorEntity authorEntity = authorEntityAuthorDtoMapper.mapFrom(author);
        AuthorEntity savedauthor =authorService.createAuthor(authorEntity);
        return new ResponseEntity<>(authorEntityAuthorDtoMapper.mapTo(savedauthor), HttpStatus.CREATED);
    }
    @GetMapping(path = "/authors")
    public List<AuthorDto> listAuthors(){
       List<AuthorEntity> authorEntities = authorService.findAll();
       return authorEntities.stream().map(authorEntityAuthorDtoMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long id){
       Optional<AuthorEntity> foundAuthor =  authorService.findOne(id);
       return foundAuthor.map(authorEntity -> {
           AuthorDto authorDto = authorEntityAuthorDtoMapper.mapTo(authorEntity);
           return new ResponseEntity(authorDto,HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto){
        if(!authorService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorDto.setId(id);
        AuthorEntity authorEntity = authorEntityAuthorDtoMapper.mapFrom(authorDto);
        AuthorEntity savedAuthor = authorService.createAuthor(authorEntity);
        return new ResponseEntity<>(authorEntityAuthorDtoMapper.mapTo(savedAuthor),HttpStatus.OK) ;
    }

    @PatchMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> partialUpdate(
            @PathVariable("id") Long id,
            @RequestBody AuthorDto authorDto
    ) {
        if(!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AuthorEntity authorEntity = authorEntityAuthorDtoMapper.mapFrom(authorDto);
        AuthorEntity updatedAuthor = authorService.partialUpdate(id, authorEntity);
        return new ResponseEntity<>(
                authorEntityAuthorDtoMapper.mapTo(updatedAuthor),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
