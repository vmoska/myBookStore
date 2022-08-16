package com.vmoska.mybookstore.service.impl;

import com.vmoska.mybookstore.exception.NotFoundException;
import com.vmoska.mybookstore.model.dto.AuthorDto;
import com.vmoska.mybookstore.model.entity.Author;
import com.vmoska.mybookstore.model.mapper.AuthorMapper;
import com.vmoska.mybookstore.repository.AuthorRepository;
import com.vmoska.mybookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    @Override
    public AuthorDto findAuthorById(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author " + authorId + " not found!"));
        return authorMapper.authorToAuthorDto(author);
    }

    @Override
    public AuthorDto addAuthor(Author author) {
        if (authorRepository.existsByName(author.getAuthorName())) {
            throw new NotFoundException("Author " + author + " already exists!");
        } else {
            Author save = authorRepository.save(new Author(
                    author.getId(),
                    author.getAuthorName()
            ));
            return authorMapper.authorToAuthorDto(save);

        }

    }

    @Override
    public AuthorDto updateAuthor(Author author, Long authorId) {
        return authorRepository.findById(authorId)
                .map(a -> {
                    a.setAuthorName(author.getAuthorName());
                    return authorMapper.authorToAuthorDto(a);
                })
                .orElseThrow(() -> new NotFoundException("Author " + authorId + " not found!"));
    }

    @Override
    public Iterable<AuthorDto> getAuthors() {
        return authorMapper.authorsToAuthorDtos(authorRepository.findAll());
    }

    @Override
    public AuthorDto deleteAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author " + authorId + " not found!"));
        authorRepository.delete(author);
        return authorMapper.authorToAuthorDto(author);
    }
}
