package com.busoft.project1.vo;

import java.util.List;

public class CompaniesVo extends BaseVo{
    private List<CompanyVo> companyVos;
    private Long totalRecords;

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<CompanyVo> getCompanyVos() {
        return companyVos;
    }

    public void setCompanyVos(List<CompanyVo> companyVos) {
        this.companyVos = companyVos;
    }
}
