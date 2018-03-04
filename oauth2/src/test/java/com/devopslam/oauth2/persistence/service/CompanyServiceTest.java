package com.devopslam.oauth2.persistence.service;

import com.devopslam.oauth2.persistence.model.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @Test
    public void createCompany() {
        Company company = new Company();
        company.setName("Test Company");
        companyService.create(company);

        List<Company> companies = companyService.getAll();
        System.out.println(companies.toString());
    }

}