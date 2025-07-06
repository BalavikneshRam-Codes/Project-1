package com.busoft.project1.business.Impl;

import com.busoft.project1.Helper.AwsHelper;
import com.busoft.project1.business.ISuperAdminBO;
import com.busoft.project1.entity.Company;
import com.busoft.project1.repo.ICompanyRepository;
import com.busoft.project1.vo.*;
import io.micrometer.common.util.StringUtils;
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
    @Autowired
    private AwsHelper awsHelper;

    @Override
    public CommonVo createCompany(CompanyVo companyVo) {
        try {
            List<Company> subDomains = companyRepository.findBySubDomain(companyVo.getSubDomain());
            if (!subDomains.isEmpty()) throw new RuntimeException("Sub Domain already present");
            List<Company> companies = companyRepository.findByCompanyName(companyVo.getCompanyName());
            if (!companies.isEmpty()) throw new RuntimeException("Company already present");
            Company company = convertVoToEntity(companyVo);
            companyRepository.save(company);
            CommonVo commonVo = new CommonVo();
            return commonVo;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public CompaniesVo getAllCompanies(CommonFilterVo commonFilterVo) {
        try {
            Sort sort = sortDirection(commonFilterVo.getFilterVO().getDirection(), null);
            Pageable pageable = PageRequest.of(commonFilterVo.getFilterVO().getPageNumber(), commonFilterVo.getFilterVO().getPageSize(), sort);
            Page<Company> companies = companyRepository.findAll(pageable);
            return convertEntityToVoList(companies);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public BaseVo deleteCompany(CompanyVo baseVo) {
        try {
            Company company = companyRepository.findFirstByCompanyName(baseVo.getCompanyName());
            if (company == null) throw new RuntimeException("There is no Company ");
            if (StringUtils.isNotEmpty(company.getProfilePic())) awsHelper.deleteImage(company.getProfilePic());
            companyRepository.delete(company);
            CommonVo commonVo = new CommonVo();
            return commonVo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BaseVo editCompany(CompanyVo companyVo) {
        BaseVo baseVo = new BaseVo();
        try {
            Company company_ = companyRepository.findFirstByCompanyName(companyVo.getCompanyName());
            if (company_ != null) {
                Company company = convertVoToEntity(companyVo);
                company.setCompanyId(company_.getCompanyId());
                company.setCreatedIn(company_.getCreatedIn());
                companyRepository.save(company);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return baseVo;
    }

    @Override
    public BaseVo editCompanyWithProfile(CompanyVo companyVo) {
        try {
            if (companyVo.getProfilePic() != null) {
                Company company_ = companyRepository.findFirstByCompanyName(companyVo.getCompanyName());
                if (company_ != null) {
                    Company company = convertVoToEntity(companyVo);
                    company.setCompanyId(company_.getCompanyId());
                    company.setCreatedIn(company_.getCreatedIn());
                    String key = awsHelper.uploadImage(companyVo.getProfilePic());
                    company.setProfilePic(key);
                    companyRepository.save(company);
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CommonVo createCompanyWithProfile(CompanyVo companyVo) {
        try {
            List<Company> subDomains = companyRepository.findBySubDomain(companyVo.getSubDomain());
            if (!subDomains.isEmpty()) throw new RuntimeException("Sub Domain already present");
            List<Company> companies = companyRepository.findByCompanyName(companyVo.getCompanyName());
            if (!companies.isEmpty()) throw new RuntimeException("Company already present");
            Company company = convertVoToEntity(companyVo);
            String key = null;
            if(companyVo.getProfilePic() != null){
                key =  awsHelper.uploadImage(companyVo.getProfilePic());
            }
            company.setProfilePic(key);
            companyRepository.save(company);
            CommonVo commonVo = new CommonVo();
            return commonVo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Sort sortDirection(String direction, String sortBy) {
        if (sortBy == null || sortBy.trim().isEmpty()) {
            sortBy = "createdIn";
        }
        if (direction != null) {
            if (direction.equalsIgnoreCase("Desc")) return Sort.by(Sort.Direction.DESC, sortBy);
            else if (direction.equalsIgnoreCase("Asc")) return Sort.by(Sort.Direction.ASC, sortBy);
        }
        return Sort.by(Sort.Direction.DESC, sortBy);
    }

    public CompaniesVo convertEntityToVoList(Page<Company> companies) {
        CompaniesVo companiesVo = new CompaniesVo();
        List<CompanyVo> companiesVos = new ArrayList<>();
        companies.forEach(company -> {
            CompanyVo companyVo = convertEntityToVo(company);
            companiesVos.add(companyVo);
        });
        companiesVo.setCompanyVos(companiesVos);
        companiesVo.setTotalRecords(companies.getTotalElements());
        return companiesVo;
    }

    private CompanyVo convertEntityToVo(Company company) {
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
        if (StringUtils.isNotEmpty(company.getProfilePic())) {
            String preSignedUrl = awsHelper.generatePresignedUrl(company.getProfilePic());
            if (StringUtils.isNotEmpty(preSignedUrl)) companyVo.setProfilePicURL(preSignedUrl);
        }
        return companyVo;
    }

    private Company convertVoToEntity(CompanyVo companyVo) {
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
