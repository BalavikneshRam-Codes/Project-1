package com.busoft.project1.service;

import com.busoft.project1.vo.BaseVo;

public interface IUserService {
    BaseVo userAuthenticate(BaseVo baseVo) throws Exception;
    BaseVo refreshToken(BaseVo baseVo) throws Exception;
}
