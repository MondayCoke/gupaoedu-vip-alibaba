package com.gupaoedu.gpmall.marking.markingserviceprovider.dal.mapper;

import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.model.MmsLotteryPrize;
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
public interface MmsLotteryPrizeMapper extends BaseMapper<MmsLotteryPrize> {

    @Update("update mms_lottery_prize set valid_stock=valid_stock-1 where valid_stock>=1 and id=#{id}")
    void updateValidStock(@Param("id") Serializable id);
}
