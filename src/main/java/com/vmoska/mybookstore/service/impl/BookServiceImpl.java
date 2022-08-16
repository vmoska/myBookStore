package com.vmoska.mybookstore.service.impl;

import com.vmoska.mybookstore.exception.BookAlreadyExistsException;
import com.vmoska.mybookstore.exception.BookNotExistsException;
import com.vmoska.mybookstore.exception.NotFoundException;
import com.vmoska.mybookstore.model.dto.BookDto;
import com.vmoska.mybookstore.model.entity.Book;
import com.vmoska.mybookstore.model.mapper.BookMapper;
import com.vmoska.mybookstore.repository.BookRepository;
import com.vmoska.mybookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto findBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book " + bookId + " not found!"));
        return bookMapper.bookToBookDto(book);
    }

    @Override
    public BookDto addBook(Book book) {
        if (bookRepository.existsByTitle(book.getTitle())
                && bookRepository.existsByIsbn13(book.getIsbn13())) {
            throw new BookAlreadyExistsException("Book " + book + " already exists!");
        } else {
            Book save = bookRepository.save(new Book(
                    book.getId(),
                    book.getIsbn13(),
                    book.getNumPages(),
                    book.getTitle(),
                    book.getLanguage(),
                    book.getPublicationDate(),
                    book.getPublisher()
            ));
            return bookMapper.bookToBookDto(save);

        }

    }

    @Override
    public BookDto updateBook(Book book, Long bookId) {
        return bookRepository.findById(bookId)
                .map(b -> {
                    b.setTitle(book.getTitle());
                    b.setIsbn13(book.getIsbn13());
                    b.setNumPages(book.getNumPages());
                    b.setLanguage(book.getLanguage());
                    b.setPublicationDate(book.getPublicationDate());
                    b.setPublisher(book.getPublisher());
                    return bookMapper.bookToBookDto(b);
                }).orElseThrow(() -> new BookNotExistsException("Book " + bookId + " not exists!"));
    }


    @Override
    public Iterable<BookDto> getBooks() {

        List<Book> bookList = bookRepository.findAll();
        return bookMapper.bookListToBookDtoList(bookList);
    }

    @Override
    public BookDto deleteBook(Long bookId) {
        return bookRepository.findById(bookId)
                .map(b -> {
                    bookRepository.delete(b);
                    return bookMapper.bookToBookDto(b);
                }).orElseThrow(() -> new BookNotExistsException("Book " + bookId + " not exists!"));
    }
}

