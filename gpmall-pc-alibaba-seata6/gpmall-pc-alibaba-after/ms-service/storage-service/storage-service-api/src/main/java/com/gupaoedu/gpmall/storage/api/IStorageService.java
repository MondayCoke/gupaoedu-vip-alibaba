package com.gupaoedu.gpmall.storage.api;

import com.gupaoedu.gpmall.storage.dto.DecrementStorageRequest;
import com.gupaoedu.gpmall.storage.dto.DecrementStorageResponse;
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
public interface IStorageService {

    /**
     * 扣减库存
     * @param request
     * @return
     */
    @TwoPhaseBusinessAction(name = "decreaseStorageTcc", commitMethod = "decrementStorageCommit", rollbackMethod = "decrementStorageCancel")
    DecrementStorageResponse decrementStorage(BusinessActionContext actionContext,
                                            @BusinessActionContextParameter(paramName = "request")DecrementStorageRequest request);

    DecrementStorageResponse decrementStorageCommit(BusinessActionContext actionContext);

    DecrementStorageResponse decrementStorageCancel(BusinessActionContext actionContext);

}
