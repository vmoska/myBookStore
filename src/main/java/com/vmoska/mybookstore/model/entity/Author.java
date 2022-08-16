package com.vmoska.mybookstore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @Column(name = "author_id", nullable = false)
    private Long id;

    @Column(name = "author_name", length = 400)
    private String authorName;

}