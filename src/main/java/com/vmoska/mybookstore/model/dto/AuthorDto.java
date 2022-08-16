package com.vmoska.mybookstore.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorDto implements Serializable {
    private final Integer id;
    private final String authorName;
}
