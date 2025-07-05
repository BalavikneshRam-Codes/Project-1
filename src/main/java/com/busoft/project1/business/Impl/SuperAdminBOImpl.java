package com.busoft.project1.business.Impl;

import com.busoft.project1.business.ISuperAdminBO;
import com.busoft.project1.entity.Company;
import com.busoft.project1.repo.ICompanyRepository;
import com.busoft.project1.vo.CommonVo;
import com.busoft.project1.vo.CompaniesVo;
import com.busoft.project1.vo.CompanyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SuperAdminBOImpl implements ISuperAdminBO {
    @Autowired
    private ICompanyRepository companyRepository;
    @Override
    public CommonVo createCompany(CompanyVo companyVo) {
        Company company = convertVoToEntity(companyVo);
        companyRepository.save(company);
        CommonVo commonVo = new CommonVo();
        return commonVo;
    }

    @Override
    public CompaniesVo getAllCompanies(CompaniesVo companiesVo) {
        Sort  sort = Sort.by(Sort.Direction.DESC,"createdIn");
        Pageable pageable = PageRequest.of(0,10,sort);
        Page<Company> companies = companyRepository.findAll(pageable);
        return convertEntityToVoList(companies);
    }
    public CompaniesVo convertEntityToVoList(Page<Company> companies){
        CompaniesVo companiesVo = new CompaniesVo();
        List<CompanyVo> companiesVos = new ArrayList<>();
        companies.forEach(company -> {
            CompanyVo companyVo = convertEntityToVo(company);
            companiesVos.add(companyVo);
        });
        companiesVo.setCompanyVos(companiesVos);
        return companiesVo;
    }
    private CompanyVo convertEntityToVo(Company company){
        CompanyVo companyVo = new CompanyVo();
        companyVo.setCity(company.getCity());
        companyVo.setCompanyName(company.getCompanyName());
        companyVo.setAddressName(company.getAddressName());
        companyVo.setCountry(company.getCountry());
        companyVo.setEmail(company.getEmail());
        companyVo.setState(company.getState());
        companyVo.setPhone(company.getPhone());
        companyVo.setSubDomain(company.getSubDomain());
        companyVo.setStatus(company.getStatus());
        return companyVo;
    }
    private Company convertVoToEntity(CompanyVo companyVo){
        Company company = new Company();
        company.setCity(companyVo.getCity());
        company.setCompanyName(companyVo.getCompanyName());
        company.setAddressName(companyVo.getAddressName());
        company.setCountry(companyVo.getCountry());
        company.setEmail(companyVo.getEmail());
        company.setState(companyVo.getState());
        company.setPhone(companyVo.getPhone());
        company.setSubDomain(companyVo.getSubDomain());
        company.setStatus(companyVo.getStatus());
        return company;
    }
}
