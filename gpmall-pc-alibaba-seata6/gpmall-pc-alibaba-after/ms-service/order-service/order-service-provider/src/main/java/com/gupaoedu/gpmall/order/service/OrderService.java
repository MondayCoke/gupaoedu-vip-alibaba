package com.gupaoedu.gpmall.order.service;

import com.alibaba.fastjson.JSON;
import com.gupaoedu.gpmall.exception.BizException;
import com.gupaoedu.gpmall.order.api.IOrderService;
import com.gupaoedu.gpmall.order.converter.OrderConverter;
import com.gupaoedu.gpmall.order.dal.mapper.TccOrderMapper;
import com.gupaoedu.gpmall.order.dal.model.TccOrder;
import com.gupaoedu.gpmall.order.dto.CreateOrderRequest;
import com.gupaoedu.gpmall.order.dto.CreateOrderResponse;
import com.gupaoedu.gpmall.order.enums.OrderResCodeEnum;
import com.gupaoedu.gpmall.order.utils.constants.OrderConstant;
import com.gupaoedu.gpmall.order.utils.exception.OrderExceptionWrapper;
import com.gupaoedu.gpmall.user.IAccountService;
import com.gupaoedu.gpmall.user.dto.DecrementAccountRequest;
import com.gupaoedu.gpmall.user.dto.DecrementAccountResponse;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Slf4j
@DubboService(cluster = "failfast",timeout = 3000)
public class OrderService implements IOrderService {

    @Autowired
    TccOrderMapper orderMapper;

    @Autowired
    OrderConverter orderConverter;

    @DubboReference
    IAccountService accountService;

    @Override
    public CreateOrderResponse createOrder(BusinessActionContext actionContext, CreateOrderRequest request) {
        log.info("begin OrderService.createOrder, request:{}",request);
        CreateOrderResponse response=new CreateOrderResponse(true,"SUCCESS");
        try{
            //1. 冻结账户余额
            DecrementAccountRequest req=new DecrementAccountRequest();
            req.setAmount(request.getOrderAmount());
            req.setUserId(request.getUserId());
            DecrementAccountResponse res =accountService.decrementAccountPrepare(null,req);


            //2. 创建订单
            TccOrder order=orderConverter.dto2TccOrder(request);
            order.setStatus(OrderConstant.ORDER_INIT_STATUS);
            int rows=orderMapper.insert(order);
            if(!res.isSuccess()||rows<1){
                throw new BizException(OrderResCodeEnum.UPDATE_DATA_FAIL.getCode(),OrderResCodeEnum.UPDATE_DATA_FAIL.getMsg());
            }
        }catch (Exception e){
            log.error("[StorageService.decreseStorage], occur Exception",e);
            response.setSuccess(false);
            response.setMessage("FAILED:"+e.getMessage());
        }
        return response;
    }

    public CreateOrderResponse createOrderCommit(BusinessActionContext actionContext) {
        CreateOrderRequest request=JSON.parseObject(JSON.toJSONString(actionContext.getActionContext("request")), CreateOrderRequest.class);
        log.info("begin OrderService.createOrder, request:{}",request);
        CreateOrderResponse response=new CreateOrderResponse(true,"SUCCESS");
        try{
            int rows=orderMapper.createOrderCommitOrCancel(request.getOrderNo(),OrderConstant.ORDER_CREATE_SUCCESS_STATUS);
            if(rows<1){
                throw new BizException(OrderResCodeEnum.UPDATE_DATA_FAIL.getCode(),OrderResCodeEnum.UPDATE_DATA_FAIL.getMsg());
            }
        }catch (Exception e){
            log.error("[OrderService.createOrderCommit], occur Exception",e);
            response.setSuccess(false);
            response.setMessage("FAILED:"+e.getMessage());
        }
        return response;
    }

    public CreateOrderResponse createOrderCancel(BusinessActionContext actionContext) {
        CreateOrderRequest request=JSON.parseObject(JSON.toJSONString(actionContext.getActionContext("request")), CreateOrderRequest.class);
        log.info("begin OrderService.createOrderCancel, request:{}",request);
        CreateOrderResponse response=new CreateOrderResponse(true,"SUCCESS");

        try{
            int rows=orderMapper.createOrderCommitOrCancel(request.getOrderNo(),OrderConstant.ORDER_CREATE_FAILED_STATUS);
            if(rows<1){
                throw new BizException(OrderResCodeEnum.UPDATE_DATA_FAIL.getCode(),OrderResCodeEnum.UPDATE_DATA_FAIL.getMsg());
            }
        }catch (Exception e){
            log.error("[OrderService.createOrderCancel], occur Exception",e);
            response.setSuccess(false);
            response.setMessage("FAILED:"+e.getMessage());
        }
        return response;
    }
}
