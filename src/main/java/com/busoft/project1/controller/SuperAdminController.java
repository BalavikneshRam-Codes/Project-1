package com.busoft.project1.controller;

import com.busoft.project1.constant.StatusEnum;
import com.busoft.project1.vo.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SuperAdminController extends BaseController{
    @PostMapping("/createCompany")
    public CommonVo createCompany(@RequestBody CompanyVo companyVo, HttpServletRequest servletRequest){
        CommonVo commonVo = null;
        try {
            commonVo =(CommonVo) callingSuperAdminService(companyVo, servletRequest, "createCompany");
            commonVo.setStatus(StatusEnum.SUCCESS.getKey());
        }catch (Exception e){
            commonVo = new CommonVo();
            commonVo.setStatus(StatusEnum.FAILED.getKey());
            commonVo.setErrorMessage(e.getMessage());
        }
        return commonVo;
    }

    @PostMapping("/getAllCompanies")
    public CompaniesVo getAllCompanies(@RequestBody CommonFilterVo commonFilterVo,HttpServletRequest servletRequest){
        CompaniesVo companyVos = null;
        try{
            companyVos =(CompaniesVo) callingSuperAdminService(commonFilterVo, servletRequest, "getAllCompanies");
            companyVos.setStatus(StatusEnum.SUCCESS.getKey());
        } catch (Exception e) {
            companyVos = new CompaniesVo();
            companyVos.setCompanyVos(Collections.EMPTY_LIST);
            companyVos.setStatus(StatusEnum.FAILED.getKey());
            companyVos.setErrorMessage(e.getMessage());
        }
        return companyVos;
    }
    @PostMapping("/deleteCompany")
    public BaseVo deleteCompany(@RequestBody CompanyVo companyVo, HttpServletRequest servletRequest){
        BaseVo BaseVo = null;
        try{
            BaseVo =(BaseVo) callingSuperAdminService(companyVo, servletRequest, "deleteCompany");
            BaseVo.setStatus(StatusEnum.SUCCESS.getKey());
        } catch (Exception e) {
            BaseVo = new BaseVo();
            BaseVo.setStatus(StatusEnum.FAILED.getKey());
            BaseVo.setErrorMessage(e.getMessage());
        }
        return BaseVo;
    }
    @PostMapping("/editCompany")
    public BaseVo editCompany(@RequestBody CompanyVo companyVo, HttpServletRequest servletRequest){
        BaseVo BaseVo = null;
        try{
            BaseVo =(BaseVo) callingSuperAdminService(companyVo, servletRequest, "editCompany");
            BaseVo.setStatus(StatusEnum.SUCCESS.getKey());
        } catch (Exception e) {
            BaseVo = new BaseVo();
            BaseVo.setStatus(StatusEnum.FAILED.getKey());
            BaseVo.setErrorMessage(e.getMessage());
        }
        return BaseVo;
    }
    @PostMapping("/editCompanyWithProfile")
    public BaseVo editCompanyWithProfile(MultipartHttpServletRequest request, HttpServletRequest servletRequest){
        BaseVo baseVo = null;
        try{
            CompanyVo companyVo = new CompanyVo();
            companyVo.setCompanyName(request.getParameter("companyName"));
            companyVo.setSubDomain(request.getParameter("subDomain"));
            companyVo.setAddressName(request.getParameter("addressName"));
            companyVo.setCity(request.getParameter("city"));
            companyVo.setState(request.getParameter("state"));
            companyVo.setCountry(request.getParameter("country"));
            companyVo.setEmail(request.getParameter("email"));
            companyVo.setPhone(request.getParameter("phone"));
            companyVo.setStatus(request.getParameter("status"));
            companyVo.setProfilePic(request.getFile("profilePic"));
            baseVo =(BaseVo) callingSuperAdminService(companyVo, servletRequest, "editCompanyWithProfile");
            baseVo.setStatus(StatusEnum.SUCCESS.getKey());
        } catch (Exception e) {
            baseVo = new BaseVo();
            baseVo.setStatus(StatusEnum.FAILED.getKey());
            baseVo.setErrorMessage(e.getMessage());
        }
        return baseVo;
    }
}
