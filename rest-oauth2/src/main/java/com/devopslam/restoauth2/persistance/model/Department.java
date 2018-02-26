package com.devopslam.restoauth2.persistance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Employee> employees = new HashSet<>();

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company=" + company +
                '}';
    }
}
