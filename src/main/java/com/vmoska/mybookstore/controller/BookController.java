package com.vmoska.mybookstore.controller;

import com.vmoska.mybookstore.model.dto.BookDto;
import com.vmoska.mybookstore.model.entity.Book;
import com.vmoska.mybookstore.service.impl.BookServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookServiceImpl bookService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get book", notes = "Get book by id")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long id) {
        log.info("Getting book with id: {}", id);
        BookDto bookById = bookService.findBookById(id);
        return new ResponseEntity<>(bookById, HttpStatus.OK);
    }

    @GetMapping("/get-books")
    @ApiOperation(value = "Get books", notes = "Get all books")
    public ResponseEntity<Iterable<BookDto>> getBooks() {
        log.info("Getting all books");
        Iterable<BookDto> books = bookService.getBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/add-book")
    @ApiOperation(value = "Add book", notes = "Add book")
    public ResponseEntity<BookDto> createBook(@Validated @RequestBody Book book) {
        log.info("Creating book: {}", book);
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);

    }

    @PutMapping("/update-book/{id}")
    @ApiOperation(value = "Update book", notes = "Update book by id")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @Validated @RequestBody Book book) {
        log.info("Updating book: {}", book);
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.OK);

    }

    @DeleteMapping("/delete-book/{id}")
    @ApiOperation(value = "Delete book", notes = "Delete book by id")
    public ResponseEntity<BookDto> deleteBook(@PathVariable Long id) {
        log.info("Deleting book with id: {}", id);
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK);
    }
}
