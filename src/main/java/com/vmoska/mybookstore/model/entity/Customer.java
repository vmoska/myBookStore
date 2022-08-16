package com.vmoska.mybookstore.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.util.Objects;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    @Id
    @Column(name = "customer_id", nullable = false)
    private Long id;

    @Column(name = "first_name", length = 200)
    private String firstName;

    @Column(name = "last_name", length = 200)
    private String lastName;

    @Column(name = "email", length = 350)
    @Email(message = "Email is not valid")
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return id != null && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}