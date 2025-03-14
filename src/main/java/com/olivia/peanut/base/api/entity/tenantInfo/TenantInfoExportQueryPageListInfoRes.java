package com.olivia.peanut.base.api.entity.tenantInfo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 租户信息(TenantInfo)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:55:21
 */
@Accessors(chain = true)
@Getter
@Setter

public class TenantInfoExportQueryPageListInfoRes extends BaseEntityDto {

  /***
   *  租户名称
   */
  @ExcelProperty("租户名称")
  private String tenantName;
  /***
   *  租户编码
   */
  @ExcelProperty("租户编码")
  private String tenantCode;


}


