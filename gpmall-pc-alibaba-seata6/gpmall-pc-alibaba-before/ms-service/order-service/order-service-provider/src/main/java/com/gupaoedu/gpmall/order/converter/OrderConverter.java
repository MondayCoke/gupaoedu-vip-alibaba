package com.gupaoedu.gpmall.order.converter;


import com.gupaoedu.gpmall.order.dal.model.TccOrder;
import com.gupaoedu.gpmall.order.dto.CreateOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * https://ke.gupaoedu.cn
 **/
@Mapper(componentModel = "spring")
public interface OrderConverter {

    @Mappings({
            @Mapping(source="commodityCode",target ="code"),
            @Mapping(source="orderCount",target ="count"),
            @Mapping(source="orderAmount",target ="amount")
    })
    TccOrder dto2TccOrder(CreateOrderRequest request);

}
