package com.devopslam.oauth2.persistence.repository;

import com.devopslam.oauth2.persistence.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
