package com.busoft.project1.service;

import com.busoft.project1.vo.BaseVo;

public interface ISuperAdminService {
    BaseVo createCompany(BaseVo baseVo);
    BaseVo getAllCompanies(BaseVo baseVo);
    BaseVo deleteCompany(BaseVo baseVo);
    BaseVo editCompany(BaseVo baseVo);
    BaseVo editCompanyWithProfile(BaseVo baseVo);
}
