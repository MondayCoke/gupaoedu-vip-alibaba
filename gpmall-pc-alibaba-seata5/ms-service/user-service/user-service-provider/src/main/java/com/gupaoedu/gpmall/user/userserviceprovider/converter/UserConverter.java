package com.gupaoedu.gpmall.user.userserviceprovider.converter;


import com.gupaoedu.gpmall.user.dto.UserRegisterRequest;
import com.gupaoedu.gpmall.user.userserviceprovider.dal.model.UmsUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;


/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * https://ke.gupaoedu.cn
 **/
@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mappings({})
    UmsUser dto2UmsUser(UserRegisterRequest request);

}
