package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (BaseSupplier)表实体类
 *
 * @author peanut
 * @since 2024-03-28 15:35:38
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("base_supplier")
public class BaseSupplier extends BaseEntity {

  private String supplierName;
  private String supplierCode;
  private String supplierPhone;
  private String supplierEmail;
  private String supplierAddr;
  private Integer supplierStatus;
  private String supplierRemark;

}

