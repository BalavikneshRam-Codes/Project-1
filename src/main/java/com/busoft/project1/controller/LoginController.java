package com.busoft.project1.controller;

import com.busoft.project1.constant.StatusEnum;
import com.busoft.project1.utility.JwtUtil;
import com.busoft.project1.vo.LoginRequestVo;
import com.busoft.project1.vo.LoginResponseVo;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LoginController extends BaseController {
    @Autowired
    private AuthenticationManager authenticationManager;
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/userAuthenticate")
    public LoginResponseVo userAuthenticate(@RequestBody LoginRequestVo loginRequestVo, HttpServletRequest servletRequest) {
        LoginResponseVo loginResponseVo;
        try {
            loginResponseVo = (LoginResponseVo) callingService(loginRequestVo, servletRequest, "userAuthenticate");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestVo.getUserName(),
                            loginRequestVo.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            setLoginUserSession(loginResponseVo,servletRequest);
            servletRequest.getSession(true).setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            loginResponseVo.setStatus(StatusEnum.SUCCESS.getKey());
        } catch (Exception e) {
            loginResponseVo = new LoginResponseVo();
            loginResponseVo.setStatus(StatusEnum.FAILED.getKey());
            loginResponseVo.setErrorMessage(e.getMessage());
        }
        return loginResponseVo;
    }
    @PostMapping("/refresh-token")
    public LoginResponseVo refreshToken(@RequestBody LoginResponseVo loginResponseVo_, HttpServletRequest servletRequest) {
        LoginResponseVo loginResponseVo;
        try {
            loginResponseVo = (LoginResponseVo) callingService(loginResponseVo_, servletRequest, "refreshToken");
            setLoginUserSession(loginResponseVo,servletRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return loginResponseVo;
    }

}
