package com.gupaoedu.gpmall.portal.gpmallalibabaportal.controller;

import com.gupaoedu.gpmall.core.CommonResponse;
import com.gupaoedu.gpmall.enums.CommonRetCodeEnums;
import com.gupaoedu.gpmall.order.api.IOrderService;
import com.gupaoedu.gpmall.order.dto.CreateOrderRequest;
import com.gupaoedu.gpmall.order.dto.CreateOrderResponse;
import com.gupaoedu.gpmall.order.enums.OrderResCodeEnum;
import com.gupaoedu.gpmall.storage.api.IStorageService;
import com.gupaoedu.gpmall.storage.dto.DecrementStorageRequest;
import com.gupaoedu.gpmall.storage.dto.DecrementStorageResponse;
import com.gupaoedu.gpmall.storage.enums.StorageResCodeEnum;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@RestController
@RequestMapping("/tcc")
public class TccTransactionController {

    @DubboReference
    IOrderService orderService;

    @DubboReference
    IStorageService storageService;

    /**
     *
     * @param map
     *   userId
     *   account
     *   code
     *   amount
     * @return
     */
    @GlobalTransactional(timeoutMills = 300000,name = "tcc-transaction-example")
    @PostMapping("/order")
    public CommonResponse pay(@RequestBody Map<String,String> map){
        /**
         * 1. 冻结库存资源
         * 2. 创建订单
         */
        DecrementStorageRequest request=new DecrementStorageRequest();
        Integer account=Integer.parseInt(map.get("account"));
        String code=map.get("code");
        request.setCount(account);
        request.setCommodityCode(code);
        DecrementStorageResponse response=storageService.decrementStorage(null,request);

        if(response.isSuccess()){
            BigDecimal amount=BigDecimal.valueOf(Long.valueOf(map.get("amount")));
            String userId=map.get("userId");
            CreateOrderRequest orderRequest=new CreateOrderRequest();
            orderRequest.setCommodityCode(code);
            orderRequest.setOrderAmount(amount);
            orderRequest.setUserId(userId);
            orderRequest.setOrderCount(account);
            orderRequest.setOrderNo(UUID.randomUUID().toString().replace("-",""));
            CreateOrderResponse orderResponse=orderService.createOrder(null,orderRequest);
//
            if(orderResponse.isSuccess()){
                return CommonResponse.success(null);
            }else{
                throw new RuntimeException("失败异常测试");
            }
        }else{
            return CommonResponse.error(CommonRetCodeEnums.FAILED.getCode(),response.getMessage());
        }
    }
}
