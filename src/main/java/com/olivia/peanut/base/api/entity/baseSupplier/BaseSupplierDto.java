package com.olivia.peanut.base.api.entity.baseSupplier;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

/**
 * (BaseSupplier)查询对象返回
 *
 * @author peanut
 * @since 2024-03-28 15:35:38
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseSupplierDto extends BaseEntityDto {


  private String supplierName;
  private String supplierCode;
  private String supplierPhone;
  private String supplierEmail;
  private String supplierAddr;
  private Integer supplierStatus;
  private String supplierRemark;
}


