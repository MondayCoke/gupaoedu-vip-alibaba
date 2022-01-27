package com.gupaoedu.gpmall.user.userserviceprovider.service;

import com.alibaba.fastjson.JSON;
import com.gupaoedu.gpmall.exception.BizException;
import com.gupaoedu.gpmall.user.IAccountService;
import com.gupaoedu.gpmall.user.dto.DecrementAccountRequest;
import com.gupaoedu.gpmall.user.dto.DecrementAccountResponse;
import com.gupaoedu.gpmall.user.enums.UmsResCodeEnum;
import com.gupaoedu.gpmall.user.userserviceprovider.dal.mapper.TccAccountMapper;
import com.gupaoedu.gpmall.user.userserviceprovider.utils.exception.UmsExceptionWrapper;
import io.seata.rm.tcc.api.BusinessActionContext;
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
@DubboService(cluster = "failfast",timeout = 3000)
public class AccountService implements IAccountService {

    @Autowired
    TccAccountMapper accountMapper;

    @Override
    public DecrementAccountResponse decrementAccountPrepare(BusinessActionContext actionContext, DecrementAccountRequest request) {
        log.info("begin AccountService.decrementAccount,request:{}",request);
        DecrementAccountResponse response=new DecrementAccountResponse(true,"SUCCESS");
        try{
            request.checkParam();
            int rows= accountMapper.decrementAccount(request.getUserId(),request.getAmount().doubleValue());
            if(rows<1){
                throw new BizException(UmsResCodeEnum.UPDATE_DATA_FAIL.getCode(),UmsResCodeEnum.UPDATE_DATA_FAIL.getMsg());
            }
        }catch (Exception e){
            log.error("[AccountService.regidecrementAccountster], occur Exception",e);
            response.setSuccess(false);
            response.setMessage("FAILED:"+e.getMessage());
        }
        return response;
    }

    @Override
    public DecrementAccountResponse decrementAccountCommit(BusinessActionContext actionContext) {
        DecrementAccountRequest request= JSON.parseObject(JSON.toJSONString(actionContext.getActionContext("request")),DecrementAccountRequest.class);
        log.info("begin AccountService.decrementAccountCommit,request:{}",request);
        DecrementAccountResponse response=new DecrementAccountResponse(true,"SUCCESS");
        try{
            int rows= accountMapper.decrementAccountCommit(request.getUserId(),request.getAmount().doubleValue());
            if(rows<1){
                throw new BizException(UmsResCodeEnum.UPDATE_DATA_FAIL.getCode(),UmsResCodeEnum.UPDATE_DATA_FAIL.getMsg());
            }
        }catch (Exception e){
            log.error("[AccountService.decrementAccountCommit], occur Exception",e);
            response.setSuccess(false);
            response.setMessage("FAILED:"+e.getMessage());
        }
        return response;
    }

    @Override
    public DecrementAccountResponse decrementAccountCancel(BusinessActionContext actionContext) {
        DecrementAccountRequest request= JSON.parseObject(JSON.toJSONString(actionContext.getActionContext("request")),DecrementAccountRequest.class);
        log.info("begin AccountService.decrementAccountCancel,request:{}",request);
        DecrementAccountResponse response=new DecrementAccountResponse(true,"SUCCESS");
        try{
            int rows= accountMapper.decrementAccountCancel(request.getUserId(),request.getAmount().doubleValue());
            if(rows<1){
                throw new BizException(UmsResCodeEnum.UPDATE_DATA_FAIL.getCode(),UmsResCodeEnum.UPDATE_DATA_FAIL.getMsg());
            }
        }catch (Exception e){
            log.error("[AccountService.decrementAccountCancel], occur Exception",e);
            response.setSuccess(false);
            response.setMessage("FAILED:"+e.getMessage());
        }
        return response;
    }
}
