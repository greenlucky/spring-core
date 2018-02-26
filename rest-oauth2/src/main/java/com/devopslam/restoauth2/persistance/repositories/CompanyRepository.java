package com.devopslam.restoauth2.persistance.repositories;

import com.devopslam.restoauth2.persistance.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByName(String name);
}
