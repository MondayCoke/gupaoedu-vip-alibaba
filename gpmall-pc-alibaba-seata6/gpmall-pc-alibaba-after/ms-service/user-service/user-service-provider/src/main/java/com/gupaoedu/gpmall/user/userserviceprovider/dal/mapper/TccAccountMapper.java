package com.gupaoedu.gpmall.user.userserviceprovider.dal.mapper;

import com.gupaoedu.gpmall.user.userserviceprovider.dal.model.TccAccount;
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
public interface TccAccountMapper extends BaseMapper<TccAccount> {


    @Update("update tcc_account set amount=amount-#{amount},frozen_amount=frozen_amount+#{amount}")
    int decrementAccount(@Param("userId")String userId,@Param("amount")Double amount);

    @Update("update tcc_account set frozen_amount=frozen_amount-#{amount} where user_id=#{userId}")
    int decrementAccountCommit(@Param("userId")String userId,@Param("amount")Double amount);

    @Update("update tcc_account set amount=amount+#{amount},frozen_amount=frozen_amount-#{amount}")
    int decrementAccountCancel(@Param("userId")String userId,@Param("amount")Double amount);
}
