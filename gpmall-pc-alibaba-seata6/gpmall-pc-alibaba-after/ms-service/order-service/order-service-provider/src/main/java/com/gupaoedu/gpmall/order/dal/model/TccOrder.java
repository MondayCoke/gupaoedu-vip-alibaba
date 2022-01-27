package com.gupaoedu.gpmall.order.dal.model;

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
@TableName("tcc_order")
public class TccOrder extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 商品编码
     */
    private String code;

    /**
     * 商品数量
     */
    private Integer count;

    /**
     * 消费总金额
     */
    private Double amount;

    /**
     * 状态，1-预创建；2-创建成功；3-创建失败
     */
    private Integer status;


}
