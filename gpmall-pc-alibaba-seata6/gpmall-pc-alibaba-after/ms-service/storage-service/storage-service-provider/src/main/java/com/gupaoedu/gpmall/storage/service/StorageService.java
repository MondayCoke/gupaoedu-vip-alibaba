package com.gupaoedu.gpmall.storage.service;

import com.alibaba.fastjson.JSON;
import com.gupaoedu.gpmall.exception.BizException;
import com.gupaoedu.gpmall.storage.api.IStorageService;
import com.gupaoedu.gpmall.storage.dal.mapper.TccStorageMapper;
import com.gupaoedu.gpmall.storage.dto.DecrementStorageRequest;
import com.gupaoedu.gpmall.storage.dto.DecrementStorageResponse;
import com.gupaoedu.gpmall.storage.enums.StorageResCodeEnum;
import com.gupaoedu.gpmall.storage.utils.exception.StorageExceptionWrapper;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Slf4j
@DubboService(cluster = "failfast",timeout = 3000)
public class StorageService implements IStorageService {

    @Autowired
    TccStorageMapper storageMapper;

    @Transactional
    @Override
    public DecrementStorageResponse decrementStorage(BusinessActionContext actionContext, DecrementStorageRequest request) {
        log.info("begin StorageService.decreseStorage,req:"+request);
        DecrementStorageResponse response=new DecrementStorageResponse(true,"SUCCESS");
        try{
            request.checkParam();
            int rows=storageMapper.decrementStorage(request.getCount(),request.getCommodityCode());
            if(rows<1){
                throw new BizException(StorageResCodeEnum.UPDATE_DATA_FAIL.getCode(),StorageResCodeEnum.UPDATE_DATA_FAIL.getMsg());
            }
        }catch (Exception e){
            log.error("[StorageService.decreseStorage], occur Exception",e);
            response.setSuccess(false);
            response.setMessage("FAILED:"+e.getMessage());
        }
        return response;
    }

    @Override
    public DecrementStorageResponse decrementStorageCommit(BusinessActionContext actionContext) {
        DecrementStorageRequest request = JSON.parseObject(JSON.toJSONString(actionContext.getActionContext("request")), DecrementStorageRequest.class);
        log.info("begin StorageService.decrementStorageCommit,req:"+request);
        DecrementStorageResponse response=new DecrementStorageResponse(true,"SUCCESS");

        try{
            request.checkParam();
            int rows=storageMapper.decrementStorageCommit(request.getCount(),request.getCommodityCode());
            if(rows<1){
                throw new BizException(StorageResCodeEnum.UPDATE_DATA_FAIL.getCode(),StorageResCodeEnum.UPDATE_DATA_FAIL.getMsg());
            }
        }catch (Exception e){
            log.error("[StorageService.decrementStorageCommit], occur Exception",e);
            response.setSuccess(false);
            response.setMessage("FAILED:"+e.getMessage());
        }
        return response;
    }

    @Override
    public DecrementStorageResponse decrementStorageCancel(BusinessActionContext actionContext) {
        DecrementStorageRequest request = JSON.parseObject(JSON.toJSONString(actionContext.getActionContext("request")), DecrementStorageRequest.class);
        log.info("begin StorageService.decrementStorageCancel,req:"+request);
        DecrementStorageResponse response=new DecrementStorageResponse(true,"SUCCESS");
        try{
            request.checkParam();
            int rows=storageMapper.decrementStorageCancel(request.getCount(),request.getCommodityCode());
            if(rows<1){
                throw new BizException(StorageResCodeEnum.UPDATE_DATA_FAIL.getCode(),StorageResCodeEnum.UPDATE_DATA_FAIL.getMsg());
            }
        }catch (Exception e){
            log.error("[StorageService.decrementStorageCancel], occur Exception",e);
            response.setSuccess(false);
            response.setMessage("FAILED:"+e.getMessage());
        }
        return response;
    }
}
