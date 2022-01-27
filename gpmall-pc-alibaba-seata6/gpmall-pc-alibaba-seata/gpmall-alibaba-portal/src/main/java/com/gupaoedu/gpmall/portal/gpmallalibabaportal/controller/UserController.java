package com.gupaoedu.gpmall.portal.gpmallalibabaportal.controller;

import com.gupaoedu.gpmall.core.CommonResponse;
import com.gupaoedu.gpmall.marking.ILotterChanceService;
import com.gupaoedu.gpmall.marking.dto.UserDrawChanceRequest;
import com.gupaoedu.gpmall.user.IUserService;
import com.gupaoedu.gpmall.user.dto.UserRegisterRequest;
import com.gupaoedu.gpmall.user.dto.UserRegisterResponse;
import com.gupaoedu.gpmall.user.enums.UmsResCodeEnum;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @DubboReference
    IUserService userService;

    @DubboReference
    ILotterChanceService lotterChanceService;

    @GlobalTransactional(timeoutMills = 5000,name = "user-register-draw-chance")
    @PostMapping
    public CommonResponse register(@RequestBody Map<String,String> map){
        UserRegisterRequest request=new UserRegisterRequest();
        request.setUsername(map.get("username"));
        request.setPassword(map.get("password"));
        UserRegisterResponse response=userService.register(request); //插入数据(需要回滚）
        if(response.getCode().equals(UmsResCodeEnum.SYS_SUCCESS.getCode())){
            UserDrawChanceRequest req=new UserDrawChanceRequest();
            req.setUid(response.getUid());
            lotterChanceService.incrementDrawChance(req);
            throw new RuntimeException("模拟异常回滚");
//            return CommonResponse.success(response.getUid());
        }
        return CommonResponse.error(response);
    }
}
