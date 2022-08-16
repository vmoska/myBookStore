package com.vmoska.mybookstore.service;

import com.vmoska.mybookstore.model.dto.BookDto;
import com.vmoska.mybookstore.model.entity.Book;

public interface BookService {
    BookDto findBookById(Long bookId);

    BookDto addBook(Book book);

    BookDto updateBook(Book book, Long bookId);

    Iterable<BookDto> getBooks();

    BookDto deleteBook(Long bookId);
}
