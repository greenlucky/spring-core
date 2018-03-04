package com.devopslam.oauth2.controller;

import com.devopslam.oauth2.persistence.model.Company;
import com.devopslam.oauth2.persistence.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secured/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<Company> getAll() {
        return companyService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Company get(@PathVariable Long id) {
        return companyService.get(id);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Company company) {
        companyService.create(company);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Company company) {
        company = companyService.update(company);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        companyService.delete(id);
    }
    @Transactional
    @DeleteMapping
    public void delete(@RequestBody Company company) {
        companyService.delete(company);
    }


}
