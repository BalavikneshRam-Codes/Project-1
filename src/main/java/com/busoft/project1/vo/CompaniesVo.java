package com.busoft.project1.vo;

import java.util.List;

public class CompaniesVo extends BaseVo{
    private List<CompanyVo> companyVos;

    public List<CompanyVo> getCompanyVos() {
        return companyVos;
    }

    public void setCompanyVos(List<CompanyVo> companyVos) {
        this.companyVos = companyVos;
    }
}
