package com.gupaoedu.gpmall.portal.gpmallalibabaportal.controller;

import com.gupaoedu.gpmall.core.CommonResponse;
import com.gupaoedu.gpmall.marking.ILotterChanceService;
import com.gupaoedu.gpmall.marking.ILotterService;
import com.gupaoedu.gpmall.marking.dto.DrawRequest;
import com.gupaoedu.gpmall.marking.dto.DrawResponse;
import com.gupaoedu.gpmall.marking.dto.UserDrawChanceRequest;
import com.gupaoedu.gpmall.marking.dto.UserDrawChanceResponse;
import com.gupaoedu.gpmall.marking.enums.MmsResCodeEnum;
import com.gupaoedu.gpmall.portal.gpmallalibabaportal.util.CusAccessObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Slf4j
@RestController
@RequestMapping("/marking")
public class MarkingController {

    @DubboReference(check = false)
    ILotterService lotterService;

    @DubboReference(check = false)
    ILotterChanceService lotterChanceService;

    @GetMapping("/draw")
    public CommonResponse draw(@RequestBody Map<String,String> map){
        log.info("begin MarkingController.draw, req: {}:",map);
        DrawRequest req=new DrawRequest();
        req.setAccount(map.get("account"));
        req.setLotteryId(Integer.parseInt(map.get("id")));
        DrawResponse response=lotterService.doDraw(req);
        if(response.getCode().equals(MmsResCodeEnum.SYS_SUCCESS.getCode())){
            return CommonResponse.success(response);
        }
        return CommonResponse.error(response);
    }

    @GetMapping("/draw/chance/{id}")
    public CommonResponse lotteryChance(@PathVariable("id")Integer id){
        log.info("begin MarkingController.lotteryChance,access user {}",id);
        UserDrawChanceRequest request=new UserDrawChanceRequest();
        request.setUid(id);
        UserDrawChanceResponse response=lotterChanceService.queryUserDrawChance(request);
        if(response.getCode().equals(MmsResCodeEnum.SYS_SUCCESS.getCode())){
            return CommonResponse.success(response);
        }
        return CommonResponse.error(response);
    }
}
