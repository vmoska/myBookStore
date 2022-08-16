package com.vmoska.mybookstore.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book implements Serializable {
    @Id
    @Column(name = "book_id", nullable = false)
    private Long id;

    @Column(name = "title", length = 400)
    private String title;

    @Column(name = "isbn13", length = 13)
    private String isbn13;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    //@JsonIgnore
    //@Transient
    private BookLanguage language;

    @Column(name = "num_pages")
    private Integer numPages;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    //@JsonIgnore
    //@Transient
    private Publisher publisher;

    public Book(Long id, String isbn13, Integer numPages, String title, BookLanguage language, LocalDate publicationDate, Publisher publisher) {

        this.id = id;
        this.isbn13 = isbn13;
        this.numPages = numPages;
        this.title = title;
        this.language = language;
        this.publicationDate = publicationDate;
        this.publisher = publisher;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return id != null && Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}