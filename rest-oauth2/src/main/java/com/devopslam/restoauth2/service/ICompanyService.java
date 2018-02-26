package com.devopslam.restoauth2.service;

import com.devopslam.restoauth2.persistance.model.Company;

import java.util.List;

public interface ICompanyService {
    Company get(Long id);

    Company get(String name);

    List<Company> getAll();

    void create(Company company);

    Company update(Company company);

    void delete(Long id);

    void delete(Company company);
}
