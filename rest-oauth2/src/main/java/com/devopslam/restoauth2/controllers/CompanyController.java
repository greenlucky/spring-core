package com.devopslam.restoauth2.controllers;

import com.devopslam.restoauth2.persistance.model.Company;
import com.devopslam.restoauth2.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/secured/company")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<Company> getAll() {
        return companyService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Company get(@PathVariable Long id) {
        return companyService.get(id);
    }

    @GetMapping("/filter")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Company get(@RequestParam String name) {
        return companyService.get(name);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> create(@RequestBody Company company) {
        companyService.create(company);
        HttpHeaders headers = new HttpHeaders();
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(CompanyController.class).get(company.getId()));
        headers.setLocation(linkBuilder.toUri());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Company company) {
        companyService.update(company);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        companyService.delete(id);
    }
}
