package com.busoft.project1.service.Impl;

import com.busoft.project1.business.ISuperAdminBO;
import com.busoft.project1.service.ISuperAdminService;
import com.busoft.project1.vo.BaseVo;
import com.busoft.project1.vo.CommonFilterVo;
import com.busoft.project1.vo.CompaniesVo;
import com.busoft.project1.vo.CompanyVo;
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
}
