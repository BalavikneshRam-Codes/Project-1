package com.busoft.project1.business;

import com.busoft.project1.vo.CommonVo;
import com.busoft.project1.vo.CompaniesVo;
import com.busoft.project1.vo.CompanyVo;

public interface ISuperAdminBO {
    CommonVo createCompany(CompanyVo companyVo);

    CompaniesVo getAllCompanies(CompaniesVo companiesVo);
}
