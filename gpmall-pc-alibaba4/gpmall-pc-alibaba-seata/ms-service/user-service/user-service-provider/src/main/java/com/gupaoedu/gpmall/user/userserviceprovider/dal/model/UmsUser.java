package com.gupaoedu.gpmall.user.userserviceprovider.dal.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author mic4096
 * @since 2022-01-18
 */
@Getter
@Setter
@TableName("ums_user")
public class UmsUser extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码，加密存储
     */
    private String password;

    /**
     * 注册手机号
     */
    private String phone;

    /**
     * 注册邮箱
     */
    private String email;

    /**
     * 性别
     */
    private String sex;

    /**
     * 状态（1，有效，0，无效）
     */
    private Integer state;

    /**
     * 头像
     */
    private String file;

    /**
     * 积分
     */
    private Integer points;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 个人简介
     */
    private String description;

    private LocalDateTime created;

    private LocalDateTime updated;


}
