package com.gupaoedu.gpmall.storage.dal.model;

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
@TableName("tcc_storage")
public class TccStorage extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品编码
     */
    private String commodityCode;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品库存数
     */
    private Integer count;

    /**
     * 冻结商品库存数
     */
    private Integer frozenCount;


}
