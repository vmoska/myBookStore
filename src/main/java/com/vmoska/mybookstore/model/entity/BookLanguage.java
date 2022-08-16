package com.vmoska.mybookstore.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_language")
public class BookLanguage {
    @Id
    @Column(name = "language_id", nullable = false)
    private Long languageId;

    @Column(name = "language_code", length = 8)
    private String languageCode;

    @Column(name = "language_name", length = 50)
    private String languageName;
}