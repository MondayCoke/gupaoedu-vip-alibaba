package com.gupaoedu.gpmall.marking.markingserviceprovider.dal.mapper;

import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.model.MmsLotteryChance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mic4096
 * @since 2022-01-18
 */
public interface MmsLotteryChanceMapper extends BaseMapper<MmsLotteryChance> {

    @Update("update mms_lottery_chance set now_limit=now_limit+1 where uid=#{uid} and max_limit>now_limit")
    int updateLotteryChance(@Param("uid") Integer uid);


    @Update("update mms_lottery_chance set max_limit=max_limit+1 where uid=#{uid}")
    int incrementLotteryChance(@Param("uid") Integer uid);
}
