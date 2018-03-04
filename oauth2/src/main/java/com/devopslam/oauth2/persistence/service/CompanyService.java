package com.devopslam.oauth2.persistence.service;

import com.devopslam.oauth2.contant.MessageContant;
import com.devopslam.oauth2.exception.NotFoundException;
import com.devopslam.oauth2.persistence.model.Company;
import com.devopslam.oauth2.persistence.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
    public Company get(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (!company.isPresent()) throw new NotFoundException(MessageContant.COMPANY_ID_NOT_FOUND + id);
        return company.get();

    }

    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_CREATE')")
    public void create(Company company) {
        companyRepository.save(company);
    }

    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_UPDATE')")
    public Company update(Company company) {
        Optional<Company> com = companyRepository.findById(company.getId());
        if (!com.isPresent()) throw new NotFoundException(MessageContant.COMPANY_ID_NOT_FOUND + company.getId());
        return companyRepository.save(company);
    }

    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void delete(Long id) {
        Optional<Company> com = companyRepository.findById(id);
        if (!com.isPresent()) throw new NotFoundException(MessageContant.COMPANY_ID_NOT_FOUND + id);
        companyRepository.deleteById(id);
    }

    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void delete(Company company) {
        Optional<Company> com = companyRepository.findById(company.getId());
        if (!com.isPresent()) throw new NotFoundException(MessageContant.COMPANY_ID_NOT_FOUND + company.getId());
        companyRepository.delete(company);
    }
}
