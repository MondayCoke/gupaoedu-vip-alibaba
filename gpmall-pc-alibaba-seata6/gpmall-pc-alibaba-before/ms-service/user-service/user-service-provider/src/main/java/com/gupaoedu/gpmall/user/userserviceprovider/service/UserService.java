package com.gupaoedu.gpmall.user.userserviceprovider.service;

import com.gupaoedu.gpmall.user.IUserService;
import com.gupaoedu.gpmall.user.dto.UserRegisterRequest;
import com.gupaoedu.gpmall.user.dto.UserRegisterResponse;
import com.gupaoedu.gpmall.user.enums.UmsResCodeEnum;
import com.gupaoedu.gpmall.user.userserviceprovider.converter.UserConverter;
import com.gupaoedu.gpmall.user.userserviceprovider.dal.mapper.UmsUserMapper;
import com.gupaoedu.gpmall.user.userserviceprovider.dal.model.UmsUser;
import com.gupaoedu.gpmall.user.userserviceprovider.utils.constants.UmsConstants;
import com.gupaoedu.gpmall.user.userserviceprovider.utils.exception.UmsExceptionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Slf4j
@DubboService
public class UserService implements IUserService {

    @Autowired
    UmsUserMapper userMapper;

    @Autowired
    UserConverter userConverter;

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        log.info("[UserService.register], request {},",request);
        UserRegisterResponse response=new UserRegisterResponse();
        try{
            request.checkParam();
            UmsUser user=userConverter.dto2UmsUser(request);
            user.setState(UmsConstants.USER_STATE_ENABLE);
            user.setCreated(LocalDateTime.now());
            user.setUpdated(LocalDateTime.now());
            int rows=userMapper.insert(user);
            if(rows>0){
                response.setCode(UmsResCodeEnum.SYS_SUCCESS.getCode());
                response.setMsg(UmsResCodeEnum.SYS_SUCCESS.getMsg());
                response.setUid(user.getId());
            }else{
                response.setCode(UmsResCodeEnum.UPDATE_DATA_FAIL.getCode());
                response.setMsg(UmsResCodeEnum.UPDATE_DATA_FAIL.getMsg());
            }
        }catch (Exception e){
            log.error("[UserService.register], occur Exception",e);
            UmsExceptionWrapper.handlerException4biz(response,e);
        }
        return response;
    }
}
