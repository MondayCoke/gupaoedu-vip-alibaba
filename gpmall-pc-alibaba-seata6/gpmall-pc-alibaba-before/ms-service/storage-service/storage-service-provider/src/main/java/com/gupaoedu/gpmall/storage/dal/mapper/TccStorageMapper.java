package com.gupaoedu.gpmall.storage.dal.mapper;

import com.gupaoedu.gpmall.storage.dal.model.TccStorage;
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
public interface TccStorageMapper extends BaseMapper<TccStorage> {

    @Update("update tcc_storage set count=count-#{count},frozen_count=frozen_count+#{count} where commodity_code=#{commodityCode}")
    int decrementStorage(@Param("count")Integer count,@Param("commodityCode")String commodityCode);


}
