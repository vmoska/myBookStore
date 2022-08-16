package com.vmoska.mybookstore.controller;


import com.vmoska.mybookstore.model.dto.AuthorDto;
import com.vmoska.mybookstore.model.entity.Author;
import com.vmoska.mybookstore.service.impl.AuthorServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
@Slf4j
public class AuthorController {

    private final AuthorServiceImpl authorService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get author", notes = "Get author by id")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable("id") Long id) {
        log.info("Getting author with id: {}", id);
        AuthorDto authorById = authorService.findAuthorById(id);
        return new ResponseEntity<>(authorById, HttpStatus.OK);
    }

    @GetMapping("/get-authors")
    @ApiOperation(value = "Get authors", notes = "Get all authors")
    public ResponseEntity<Iterable<AuthorDto>> getAuthors() {
        log.info("Getting all authors");
        Iterable<AuthorDto> authors = authorService.getAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @PostMapping("/add-author")
    @ApiOperation(value = "Add author", notes = "Add author")
    public ResponseEntity<AuthorDto> createAuthor(@Validated @RequestBody Author author) {
        log.info("Creating author: {}", author);
        return new ResponseEntity<>(authorService.addAuthor(author), HttpStatus.CREATED);

    }

    @PutMapping("/update-author/{id}")
    @ApiOperation(value = "Update author", notes = "Update author by id")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id, @Validated @RequestBody Author author) {
        log.info("Updating author: {}", author);
        return new ResponseEntity<>(authorService.addAuthor(author), HttpStatus.OK);

    }

    @DeleteMapping("/delete-author/{id}")
    @ApiOperation(value = "Delete author", notes = "Delete author by id")
    public ResponseEntity<AuthorDto> deleteAuthor(@PathVariable Long id) {
        log.info("Deleting author with id: {}", id);
        return new ResponseEntity<>(authorService.deleteAuthor(id), HttpStatus.OK);
    }
}
