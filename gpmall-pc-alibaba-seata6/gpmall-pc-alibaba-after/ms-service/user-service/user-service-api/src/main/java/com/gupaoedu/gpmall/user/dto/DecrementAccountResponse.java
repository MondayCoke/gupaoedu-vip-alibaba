package com.gupaoedu.gpmall.user.dto;

import com.gupaoedu.gpmall.core.AbstractRequest;
import com.gupaoedu.gpmall.core.AbstractResponse;
import io.seata.rm.tcc.TwoPhaseResult;
import lombok.Data;

import java.io.Serializable;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
public class DecrementAccountResponse extends TwoPhaseResult implements Serializable {

    /**
     * Instantiates a new Two phase result.
     *
     * @param isSuccess the is success
     * @param msg       the msg
     */
    public DecrementAccountResponse(boolean isSuccess, String msg) {
        super(isSuccess, msg);
    }
}
