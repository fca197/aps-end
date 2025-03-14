package com.olivia.peanut.base.api.entity.tenantInfo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

/**
 * 租户信息(TenantInfo)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:55:22
 */
//@Accessors(chain=true)
@Getter
@Setter

public class TenantInfoDto extends BaseEntityDto {


  /***
   *  租户编码
   */
  @ExcelProperty("租户编码")
  private String tenantCode;

}


