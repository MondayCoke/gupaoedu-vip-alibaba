package com.gupaoedu.gpmall.user.userserviceprovider.service;

import com.gupaoedu.gpmall.user.IAccountService;
import com.gupaoedu.gpmall.user.dto.DecrementAccountRequest;
import com.gupaoedu.gpmall.user.dto.DecrementAccountResponse;
import com.gupaoedu.gpmall.user.enums.UmsResCodeEnum;
import com.gupaoedu.gpmall.user.userserviceprovider.dal.mapper.TccAccountMapper;
import com.gupaoedu.gpmall.user.userserviceprovider.utils.exception.UmsExceptionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Slf4j
@DubboService(cluster = "failfast")
public class AccountService implements IAccountService {

    @Autowired
    TccAccountMapper accountMapper;

    @Override
    public DecrementAccountResponse decrementAccount(DecrementAccountRequest request) {
        log.info("begin AccountService.decrementAccount,request:{}",request);
        DecrementAccountResponse response=new DecrementAccountResponse();
        try{
            request.checkParam();
            int rows= accountMapper.decrementAccount(request.getUserId(),request.getAmount().doubleValue());
            if(rows>0) {
                response.setCode(UmsResCodeEnum.SYS_SUCCESS.getCode());
                response.setMsg(UmsResCodeEnum.SYS_SUCCESS.getMsg());
            }else{
                response.setCode(UmsResCodeEnum.SYS_UPDATE_DATA_FAIL.getCode());
                response.setMsg(UmsResCodeEnum.SYS_UPDATE_DATA_FAIL.getMsg());
            }
        }catch (Exception e){
            log.error("[AccountService.regidecrementAccountster], occur Exception",e);
            UmsExceptionWrapper.handlerException4biz(response,e);
        }
        return response;
    }
}
