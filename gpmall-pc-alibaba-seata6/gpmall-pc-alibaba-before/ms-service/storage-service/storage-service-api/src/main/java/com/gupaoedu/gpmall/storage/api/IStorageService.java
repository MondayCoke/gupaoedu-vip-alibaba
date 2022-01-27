package com.gupaoedu.gpmall.storage.api;

import com.gupaoedu.gpmall.storage.dto.DecrementStorageRequest;
import com.gupaoedu.gpmall.storage.dto.DecrementStorageResponse;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/

public interface IStorageService {

    /**
     * 扣减库存
     * @param request
     * @return
     */
    DecrementStorageResponse decreseStorage(DecrementStorageRequest request);
}
