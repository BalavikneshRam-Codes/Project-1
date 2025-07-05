package com.busoft.project1.business;

import com.busoft.project1.vo.LoginRequestVo;
import com.busoft.project1.vo.LoginResponseVo;

public interface IUserBO {
    LoginResponseVo userAuthenticate(LoginRequestVo requestVo) throws Exception;

    LoginResponseVo refreshToken(LoginResponseVo responseVo) throws Exception;
}
