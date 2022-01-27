package com.gupaoedu.gpmall.order.utils.exception;

import com.gupaoedu.gpmall.core.AbstractResponse;
import com.gupaoedu.gpmall.exception.BizException;
import com.gupaoedu.gpmall.exception.ValidException;
import com.gupaoedu.gpmall.order.enums.OrderResCodeEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * https://ke.gupaoedu.cn
 **/
@Slf4j
public class OrderExceptionWrapper implements Serializable{

    /**
     * 将下层抛出的异常转换为resp返回码
     * 
     * @param e  Exception
     * @param response  AbstractResponse
     * @return
     */
    public static Exception handlerException4biz(AbstractResponse response, Exception e) {
        Exception ex = null;
        if (!(e instanceof Exception) ) {
            return null;
        }
        if (e instanceof BizException) {
            response.setCode(((BizException) e).getErrorCode());
            response.setMsg(((BizException) e).getErrorMessage());
        }else if(e instanceof ValidException){
            response.setCode(((ValidException) e).getErrorCode());
            response.setMsg(((ValidException) e).getErrorMessage());
        } else if (e instanceof Exception) {
            response.setCode(OrderResCodeEnum.SYSTEM_EXCEPTION.getCode());
            response.setMsg(OrderResCodeEnum.SYSTEM_EXCEPTION.getMsg());
        }
        log.error("UmsExceptionWrapper.handlerException4biz,Exception="  + e.getMessage(), e);
        return ex;
    }
}
