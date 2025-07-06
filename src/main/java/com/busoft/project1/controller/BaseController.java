package com.busoft.project1.controller;

import com.busoft.project1.service.ISuperAdminService;
import com.busoft.project1.service.IUserService;
import com.busoft.project1.vo.BaseVo;
import com.busoft.project1.vo.CompanyVo;
import com.busoft.project1.vo.LoginResponseVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ISuperAdminService superAdminService;

    public BaseVo callingService(BaseVo baseVo, HttpServletRequest servletRequest, String method) throws Exception {
        try {
            BaseVo response = null;
            Class clazz = userService.getClass();
            Class<?>[] noparms = {BaseVo.class};
            Method m = clazz.getDeclaredMethod(method, noparms);
            response = (BaseVo) m.invoke(userService, baseVo);
            return response;
        } catch (Exception ex) {
            if (ex instanceof InvocationTargetException) {
                Throwable targetEx = ((InvocationTargetException) ex).getTargetException();
                throw new Exception(targetEx.getMessage(), targetEx);
            }
            throw ex;
        }
    }
    public BaseVo callingSuperAdminService(BaseVo baseVo, HttpServletRequest servletRequest, String method) throws Exception {
        try {
            BaseVo response = null;
            Class clazz = superAdminService.getClass();
            Class<?>[] noparms = {BaseVo.class};
            Method m = clazz.getDeclaredMethod(method, noparms);
            response = (BaseVo) m.invoke(superAdminService, baseVo);
            return response;
        } catch (Exception ex) {
            if (ex instanceof InvocationTargetException) {
                Throwable targetEx = ((InvocationTargetException) ex).getTargetException();
                throw new Exception(targetEx.getMessage(), targetEx);
            }
            throw ex;
        }
    }
    public void setLoginUserSession(LoginResponseVo userSessionVo, HttpServletRequest request){
        request.getSession().setAttribute("ACCESS_TOKEN",userSessionVo.getAccessToken());
        request.getSession().setAttribute("REFRESH_TOKEN",userSessionVo.getRefreshToken());
    }
    public CompanyVo getCompanyVoFromMultiPart(MultipartHttpServletRequest request){
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
        return companyVo;
    }
}