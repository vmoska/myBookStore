package com.vmoska.mybookstore.service;

import com.vmoska.mybookstore.model.dto.AuthorDto;
import com.vmoska.mybookstore.model.entity.Author;

public interface AuthorService {
    AuthorDto findAuthorById(Long authorId);

    AuthorDto addAuthor(Author author);

    AuthorDto updateAuthor(Author author, Long authorId);

    Iterable<AuthorDto> getAuthors();

    AuthorDto deleteAuthor(Long authorId);
}
