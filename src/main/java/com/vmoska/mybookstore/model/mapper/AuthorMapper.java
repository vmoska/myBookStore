package com.vmoska.mybookstore.model.mapper;

import com.vmoska.mybookstore.model.dto.AuthorDto;
import com.vmoska.mybookstore.model.entity.Author;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AuthorMapper {
    Author authorDtoToAuthor(AuthorDto authorDto);

    AuthorDto authorToAuthorDto(Author author);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Author updateAuthorFromAuthorDto(AuthorDto authorDto, @MappingTarget Author author);

    Iterable<AuthorDto> authorsToAuthorDtos(List<Author> all);
}
