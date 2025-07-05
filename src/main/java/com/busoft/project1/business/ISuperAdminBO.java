package com.busoft.project1.business;

import com.busoft.project1.vo.*;

public interface ISuperAdminBO {
    CommonVo createCompany(CompanyVo companyVo);

    CompaniesVo getAllCompanies(CommonFilterVo commonFilterVo);

    BaseVo deleteCompany(CompanyVo baseVo);
}
