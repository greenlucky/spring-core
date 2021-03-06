package com.devopslam.oauth2.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Company company;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company=" + company +
                '}';
    }
}
