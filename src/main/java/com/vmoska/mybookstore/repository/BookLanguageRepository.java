package com.vmoska.mybookstore.repository;

import com.vmoska.mybookstore.model.entity.BookLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLanguageRepository extends JpaRepository<BookLanguage, Long> {
}