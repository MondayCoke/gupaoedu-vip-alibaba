package com.gupaoedu.gpmall.user;

import com.gupaoedu.gpmall.user.dto.DecrementAccountRequest;
import com.gupaoedu.gpmall.user.dto.DecrementAccountResponse;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
public interface IAccountService {

    /**
     * 扣减资金账户余额
     * @param request
     * @return
     */
    DecrementAccountResponse decrementAccount(DecrementAccountRequest request);
}
