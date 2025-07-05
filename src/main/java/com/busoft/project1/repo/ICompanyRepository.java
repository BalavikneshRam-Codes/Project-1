package com.busoft.project1.repo;

import com.busoft.project1.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompanyRepository extends JpaRepository<Company,Long> {

}
