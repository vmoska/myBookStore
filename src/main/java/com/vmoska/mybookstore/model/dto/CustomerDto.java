package com.vmoska.mybookstore.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Data
public class CustomerDto implements Serializable {
    private final String firstName;
    private final String lastName;
    @Email(message = "Email is not valid")
    private final String email;
}
