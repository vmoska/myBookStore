package com.vmoska.mybookstore.repository;

import com.vmoska.mybookstore.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("select (count(a) > 0) from Author a where upper(a.authorName) = upper(?1)")
    boolean existsByName(String authorName);

}
