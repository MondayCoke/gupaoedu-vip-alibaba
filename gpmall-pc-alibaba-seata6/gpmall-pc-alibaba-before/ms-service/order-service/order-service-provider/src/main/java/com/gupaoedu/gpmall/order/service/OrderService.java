package com.gupaoedu.gpmall.order.service;

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
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Slf4j
@DubboService(cluster = "failfast")
public class OrderService implements IOrderService {

    @Autowired
    TccOrderMapper orderMapper;

    @Autowired
    OrderConverter orderConverter;

    @DubboReference
    IAccountService accountService;

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        log.info("begin OrderService.createOrder, request:{}",request);
        CreateOrderResponse response=new CreateOrderResponse();
        try{
            //第一步，创建订单
            TccOrder order=orderConverter.dto2TccOrder(request);
            order.setOrderNo(UUID.randomUUID().toString().replace("-",""));
            order.setStatus(OrderConstant.ORDER_INIT_STATUS);
            orderMapper.insert(order);

            //冻结账户余额
            DecrementAccountRequest req=new DecrementAccountRequest();
            req.setAmount(request.getOrderAmount());
            req.setUserId(request.getUserId());
            DecrementAccountResponse res =accountService.decrementAccount(req);
            if(!res.getCode().equals(OrderResCodeEnum.SYS_SUCCESS.getCode())){
                //如果账户余额冻结失败
                response.setCode(OrderResCodeEnum.ACCOUNT_FORZEN_FAILE.getCode());
                response.setMsg(OrderResCodeEnum.ACCOUNT_FORZEN_FAILE.getMsg());
            }else {
                response.setCode(OrderResCodeEnum.SYS_SUCCESS.getCode());
                response.setMsg(OrderResCodeEnum.SYS_SUCCESS.getMsg());
            }
        }catch (Exception e){
            log.error("[StorageService.decreseStorage], occur Exception",e);
            OrderExceptionWrapper.handlerException4biz(response,e);
        }
        return response;
    }
}
