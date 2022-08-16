package com.vmoska.mybookstore.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "country_id", nullable = false)
    private Integer id;

    @Column(name = "country_name", length = 200)
    private String countryName;

}