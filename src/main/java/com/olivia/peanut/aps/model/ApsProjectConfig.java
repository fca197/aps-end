package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsProjectConfig)表实体类
 *
 * @author peanut
 * @since 2024-03-30 16:21:20
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_project_config")
public class ApsProjectConfig extends BaseEntity {

  private String saleCode;
  private String saleName;
  private Integer supplierStatus;
  private Integer isValue;
  private Long parentId;

}

