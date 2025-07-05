package com.busoft.project1.vo;

import java.util.List;

public class LoginResponseVo extends BaseVo{
    private List<RoleVo> roleVos;
    private String reDirectPage;
    private String accessToken;
    private String refreshToken;
    private String userFullName;

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getReDirectPage() {
        return reDirectPage;
    }

    public void setReDirectPage(String reDirectPage) {
        this.reDirectPage = reDirectPage;
    }

    public List<RoleVo> getRoleVos() {
        return roleVos;
    }

    public void setRoleVos(List<RoleVo> roleVos) {
        this.roleVos = roleVos;
    }
}
