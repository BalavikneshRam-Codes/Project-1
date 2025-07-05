package com.busoft.project1.controller;

import com.busoft.project1.constant.StatusEnum;
import com.busoft.project1.vo.CommonFilterVo;
import com.busoft.project1.vo.CommonVo;
import com.busoft.project1.vo.CompaniesVo;
import com.busoft.project1.vo.CompanyVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

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
            companyVos =(CompaniesVo) callingSuperAdminService(new CompaniesVo(), servletRequest, "getAllCompanies");
            companyVos.setStatus(StatusEnum.SUCCESS.getKey());
        } catch (Exception e) {
            companyVos = new CompaniesVo();
            companyVos.setCompanyVos(Collections.EMPTY_LIST);
            companyVos.setStatus(StatusEnum.FAILED.getKey());
            companyVos.setErrorMessage(e.getMessage());
        }
        return companyVos;
    }
}
