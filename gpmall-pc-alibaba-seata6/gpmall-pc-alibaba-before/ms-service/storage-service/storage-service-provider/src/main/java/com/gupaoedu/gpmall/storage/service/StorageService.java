package com.gupaoedu.gpmall.storage.service;

import com.gupaoedu.gpmall.storage.api.IStorageService;
import com.gupaoedu.gpmall.storage.dal.mapper.TccStorageMapper;
import com.gupaoedu.gpmall.storage.dto.DecrementStorageRequest;
import com.gupaoedu.gpmall.storage.dto.DecrementStorageResponse;
import com.gupaoedu.gpmall.storage.enums.StorageResCodeEnum;
import com.gupaoedu.gpmall.storage.utils.exception.StorageExceptionWrapper;
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
public class StorageService implements IStorageService {

    @Autowired
    TccStorageMapper storageMapper;

    @Override
    public DecrementStorageResponse decreseStorage(DecrementStorageRequest request) {
        log.info("begin StorageService.decreseStorage,req:"+request);
        DecrementStorageResponse response=new DecrementStorageResponse();
        try{
            request.checkParam();
            int rows=storageMapper.decrementStorage(request.getCount(),request.getCommodityCode());
            if(rows>0) {
                response.setCode(StorageResCodeEnum.SYS_SUCCESS.getCode());
                response.setMsg(StorageResCodeEnum.SYS_SUCCESS.getMsg());
            }else{
                response.setCode(StorageResCodeEnum.SYS_UPDATE_DATA_FAIL.getCode());
                response.setMsg(StorageResCodeEnum.SYS_UPDATE_DATA_FAIL.getMsg());
            }
        }catch (Exception e){
            log.error("[StorageService.decreseStorage], occur Exception",e);
            StorageExceptionWrapper.handlerException4biz(response,e);
        }
        return response;
    }
}
