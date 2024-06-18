package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSaleConfig)表实体类
 *
 * @author peanut
 * @since 2024-03-29 23:10:39
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_sale_config")
public class ApsSaleConfig extends BaseEntity {

  private Long parentId;
  private String saleCode;
  private String saleName;
  private Integer supplierStatus;
  private Integer isValue;

}

