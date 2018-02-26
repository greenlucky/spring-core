package com.devopslam.restoauth2.persistance.model;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@lombok.Getter
@lombok.Setter
@EqualsAndHashCode(of = "id")
public class Address implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String houseNumber;

    private String street;

    private String zipCode;

}
