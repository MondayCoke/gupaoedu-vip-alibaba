package com.gupaoedu.gpmall.order.dal.mapper;

import com.gupaoedu.gpmall.order.dal.model.TccOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mic4096
 * @since 2022-01-24
 */
public interface TccOrderMapper extends BaseMapper<TccOrder> {

    @Update("update tcc_order set status=#{status} where order_no=#{orderNo}")
    int createOrderCommitOrCancel(@Param("orderNo")String orderNo,@Param("status")Integer status);
}
