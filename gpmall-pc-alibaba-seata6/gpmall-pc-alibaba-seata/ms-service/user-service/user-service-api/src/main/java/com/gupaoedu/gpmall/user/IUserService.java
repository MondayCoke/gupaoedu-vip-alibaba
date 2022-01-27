package com.gupaoedu.gpmall.user;

import com.gupaoedu.gpmall.user.dto.UserRegisterRequest;
import com.gupaoedu.gpmall.user.dto.UserRegisterResponse;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 * 负责用户注册逻辑
 **/
public interface IUserService {

    //用户注册接口
    UserRegisterResponse register(UserRegisterRequest request);
}
