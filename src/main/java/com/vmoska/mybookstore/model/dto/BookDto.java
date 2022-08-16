package com.vmoska.mybookstore.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class BookDto implements Serializable {
    private final Long id;
    private final String title;
    private final String isbn13;
    private final Long languageId;
    private final Integer numPages;
    private final LocalDate publicationDate;
    private final Integer publisherId;
}
