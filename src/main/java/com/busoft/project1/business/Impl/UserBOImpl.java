package com.busoft.project1.business.Impl;

import com.busoft.project1.business.IUserBO;
import com.busoft.project1.constant.RoleEnum;
import com.busoft.project1.constant.UserStatusEnum;
import com.busoft.project1.entity.Role;
import com.busoft.project1.entity.User;
import com.busoft.project1.repo.IUserRepository;
import com.busoft.project1.utility.JwtUtil;
import com.busoft.project1.vo.LoginRequestVo;
import com.busoft.project1.vo.LoginResponseVo;
import com.busoft.project1.vo.RoleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class UserBOImpl implements IUserBO {
    private static final Logger log = LoggerFactory.getLogger(UserBOImpl.class);
    @Autowired
    private Environment env;
    @Autowired
    private IUserRepository userRepository;

    @Override
    public LoginResponseVo userAuthenticate(LoginRequestVo requestVo) throws Exception {
        User user = userRepository.findByUserEmailAndStatus(requestVo.getUserName(), UserStatusEnum.ACTIVE.getKey());
        if (user == null)
            throw new Exception("User not found");
        if (!requestVo.getPassword().equals(user.getPassword()))
            throw new Exception("Password is wrong");
        LoginResponseVo loginResponseVo = new LoginResponseVo();
        if (!user.getRoles().isEmpty()) {
            loginResponseVo.setRoleVos(convertEntityToVoList(user.getRoles()));
        }
        loginResponseVo.getRoleVos().forEach(roleVo -> {
            if (roleVo.getRoleName().equalsIgnoreCase(RoleEnum.ADMIN.getRole())) {
                loginResponseVo.setReDirectPage("dashboard");
            } else if (roleVo.getRoleName().equalsIgnoreCase(RoleEnum.SUPER_ADMIN.getRole())) {
                loginResponseVo.setReDirectPage("superAdminDashboard");
            }
        });
        loginResponseVo.setAccessToken(generateAccessToken(user, getAccessTokenExpire()));
        loginResponseVo.setRefreshToken(generateAccessToken(user, getRefreshTokenExpire()));
        loginResponseVo.setUserFullName(user.getUserName());
        return loginResponseVo;
    }

    @Override
    public LoginResponseVo refreshToken(LoginResponseVo responseVo) throws Exception {
        try {
            String userName = JwtUtil.extractUsername(responseVo.getRefreshToken());
            User user = userRepository.findByUserEmailAndStatus(userName, UserStatusEnum.ACTIVE.getKey());
            if (user == null)
                throw new Exception("User not found");
            responseVo.setAccessToken(generateAccessToken(user, getAccessTokenExpire()));
            responseVo.setRefreshToken(generateAccessToken(user, getRefreshTokenExpire()));
            return responseVo;
        }catch (Exception e){
            throw  e;
        }
    }

    private String generateAccessToken(User user, long tokenExpire) {
        List<String> roleNames = user.getRoles().stream()
                .map(Role::getRoleName)
                .toList();
        Map<String, Object> claims = Map.of(
                "Roles", roleNames,
                "username", user.getUserEmail());
        return JwtUtil.generateToken(user.getUserFirstName(), claims, tokenExpire);
    }

    private long getAccessTokenExpire() {
        return Long.parseLong(env.getProperty("jwt.accessToken.expire"));
    }

    private long getRefreshTokenExpire() {
        return Long.parseLong(env.getProperty("jwt.refreshToken.expire"));
    }

    private List<RoleVo> convertEntityToVoList(Set<Role> roles) {
        List<RoleVo> rolesVo = new ArrayList<>();
        roles.forEach(role -> {
            rolesVo.add(convertEntityToVo(role));
        });
        return rolesVo;
    }

    private RoleVo convertEntityToVo(Role role) {
        RoleVo roleVo = new RoleVo();
        roleVo.setRoleName(role.getRoleName());
        return roleVo;
    }
}
