package com.vmoska.mybookstore.repository;

import com.vmoska.mybookstore.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select (count(b) > 0) from Book b where b.title = :title")
    boolean existsByTitle(@Param("title") String title);

    @Query("select (count(b) > 0) from Book b where upper(b.isbn13) = upper(?1)")
    boolean existsByIsbn13(String isbn13);

    Book findByTitleStartsWithIgnoreCase(String filterText);
}

