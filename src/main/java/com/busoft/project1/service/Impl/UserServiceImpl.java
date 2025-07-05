package com.busoft.project1.service.Impl;

import com.busoft.project1.business.IUserBO;
import com.busoft.project1.service.IUserService;
import com.busoft.project1.vo.BaseVo;
import com.busoft.project1.vo.LoginRequestVo;
import com.busoft.project1.vo.LoginResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserBO userBO;

    @Override
    @Transactional
    public BaseVo userAuthenticate(BaseVo baseVo) throws Exception {
        return (BaseVo) userBO.userAuthenticate((LoginRequestVo) baseVo);
    }

    @Override
    public BaseVo refreshToken(BaseVo baseVo) throws Exception {
        return (BaseVo) userBO.refreshToken((LoginResponseVo) baseVo);
    }
}
