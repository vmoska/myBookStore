package com.vmoska.mybookstore.model.mapper;

import com.vmoska.mybookstore.model.dto.BookDto;
import com.vmoska.mybookstore.model.entity.Book;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookMapper {
    @Mapping(source = "languageId", target = "language.languageId")
    @Mapping(source = "publisherId", target = "publisher.id")
    Book bookDtoToBook(BookDto bookDto);

    @InheritInverseConfiguration(name = "bookDtoToBook")
    BookDto bookToBookDto(Book book);

    @InheritConfiguration(name = "bookDtoToBook")
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateBookFromBookDto(BookDto bookDto, @MappingTarget Book book);

    Iterable<BookDto> bookListToBookDtoList(List<Book> bookList);
}
