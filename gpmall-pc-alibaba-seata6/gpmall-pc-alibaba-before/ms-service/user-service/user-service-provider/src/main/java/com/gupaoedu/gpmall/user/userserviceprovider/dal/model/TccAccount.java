package com.gupaoedu.gpmall.user.userserviceprovider.dal.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author mic4096
 * @since 2022-01-24
 */
@Getter
@Setter
@TableName("tcc_account")
public class TccAccount extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 账号余额
     */
    private Double amount;

    /**
     * 账号冻结余额
     */
    private Double frozenAmount;


}
