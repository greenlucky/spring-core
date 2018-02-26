package com.devopslam.restoauth2.persistance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Car implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String registrationNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Company company;

    public Car() {
    }

    public Car(@NotNull String registrationNumber, Company company) {
        this.registrationNumber = registrationNumber;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return id != null ? id.equals(car.id) : car.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", company=" + company +
                '}';
    }
}
