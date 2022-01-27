package com.gupaoedu.gpmall.order.api;

import com.gupaoedu.gpmall.order.dto.CreateOrderRequest;
import com.gupaoedu.gpmall.order.dto.CreateOrderResponse;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@LocalTCC
public interface IOrderService {


    /**
     * 创建订单
     * @param request
     * @return
     *   * 识别TwoPhaseResult
     *   * boolean
     */
    @TwoPhaseBusinessAction(name = "createOrderTcc", commitMethod = "createOrderCommit", rollbackMethod = "createOrderCancel")
    CreateOrderResponse createOrder(BusinessActionContext actionContext,
                                    @BusinessActionContextParameter(paramName = "request")CreateOrderRequest request);

    CreateOrderResponse createOrderCommit(BusinessActionContext actionContext);

    CreateOrderResponse createOrderCancel(BusinessActionContext actionContext);
}
