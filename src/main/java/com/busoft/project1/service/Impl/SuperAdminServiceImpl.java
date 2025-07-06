package com.busoft.project1.service.Impl;

import com.busoft.project1.business.ISuperAdminBO;
import com.busoft.project1.service.ISuperAdminService;
import com.busoft.project1.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SuperAdminServiceImpl implements ISuperAdminService {
    @Autowired
    private ISuperAdminBO superAdminBO;

    @Override
    @Transactional
    public BaseVo createCompany(BaseVo baseVo) {
        return (BaseVo) superAdminBO.createCompany((CompanyVo) baseVo);
    }

    @Override
    public BaseVo getAllCompanies(BaseVo baseVo) {
        return (BaseVo) superAdminBO.getAllCompanies((CommonFilterVo) baseVo);
    }

    @Override
    public BaseVo deleteCompany(BaseVo baseVo) {
        return (BaseVo) superAdminBO.deleteCompany((CompanyVo) baseVo);
    }

    @Override
    public BaseVo editCompany(BaseVo baseVo) {
        return (BaseVo) superAdminBO.editCompany((CompanyVo) baseVo);
    }

    @Override
    public BaseVo editCompanyWithProfile(BaseVo baseVo) {
        return (BaseVo) superAdminBO.editCompanyWithProfile((CompanyVo) baseVo);
    }

    @Override
    public BaseVo createCompanyWithProfile(BaseVo baseVo) {
       return  (BaseVo) superAdminBO.createCompanyWithProfile((CompanyVo) baseVo);
    }
}
