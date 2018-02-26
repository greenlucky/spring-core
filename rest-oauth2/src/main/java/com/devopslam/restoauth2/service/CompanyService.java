package com.devopslam.restoauth2.service;

import com.devopslam.restoauth2.persistance.model.Company;
import com.devopslam.restoauth2.persistance.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
    public Company get(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
    public Company get(String name) {
        return companyRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('COMPANY_READ')")
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_CREATE')")
    public void create(Company company) {
        companyRepository.save(company);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_UPDATE')")
    public Company update(Company company) {
        return companyRepository.save(company);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void delete(Company company) {
        companyRepository.delete(company);
    }
}
