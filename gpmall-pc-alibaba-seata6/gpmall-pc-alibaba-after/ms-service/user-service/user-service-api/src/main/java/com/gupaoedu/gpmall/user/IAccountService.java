package com.gupaoedu.gpmall.user;

import com.gupaoedu.gpmall.user.dto.DecrementAccountRequest;
import com.gupaoedu.gpmall.user.dto.DecrementAccountResponse;
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
public interface IAccountService {

    /**
     * 扣减资金账户余额
     * @param request
     * @return
     */
    @TwoPhaseBusinessAction(name = "decreaseAccountTcc", commitMethod = "decrementAccountCommit", rollbackMethod = "decrementAccountCancel")
    DecrementAccountResponse decrementAccountPrepare(BusinessActionContext actionContext,
                                                     @BusinessActionContextParameter(paramName = "request")DecrementAccountRequest request);

    /**
     * 提交
     * @param actionContext
     * @return
     */
    DecrementAccountResponse decrementAccountCommit(BusinessActionContext actionContext);

    /**
     * 回滚
     * @param actionContext
     * @return
     */
    DecrementAccountResponse decrementAccountCancel(BusinessActionContext actionContext);
}
