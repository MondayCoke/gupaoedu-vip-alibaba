package com.gupaoedu.gpmall.storage.dto;

import com.gupaoedu.gpmall.core.AbstractRequest;
import com.gupaoedu.gpmall.exception.ValidException;
import com.gupaoedu.gpmall.storage.enums.StorageResCodeEnum;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Data
public class DecrementStorageRequest extends AbstractRequest {

    private String commodityCode;
    private Integer count;

    public void checkParam(){
        if(StringUtils.isEmpty(this.commodityCode)){
            throw new ValidException(StorageResCodeEnum.SYS_PARAM_NOT_NULL.getCode(),StorageResCodeEnum.SYS_PARAM_NOT_NULL.getMsg("商品编码不能为空"));
        }
        if(count==null){
            throw new ValidException(StorageResCodeEnum.SYS_PARAM_NOT_NULL.getCode(),StorageResCodeEnum.SYS_PARAM_NOT_NULL.getMsg());
        }
    }
}
