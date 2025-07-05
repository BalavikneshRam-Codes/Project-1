package com.busoft.project1.repo;

import com.busoft.project1.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICompanyRepository extends JpaRepository<Company,Long> {
    List<Company> findBySubDomain(String subDomain);
    List<Company> findByCompanyName(String companyName);
    Company findFirstByCompanyName(String companyName);
}
