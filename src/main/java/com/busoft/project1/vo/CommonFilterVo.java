package com.busoft.project1.vo;

public class CommonFilterVo extends BaseVo{
    private String companyStatus;
    private String companyName;
    private FilterVO filterVO;

    public FilterVO getFilterVO() {
        return filterVO;
    }

    public void setFilterVO(FilterVO filterVO) {
        this.filterVO = filterVO;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
