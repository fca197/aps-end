package com.olivia.peanut.portal.api.entity.tenantInfo;

import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalDateTime;
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

public class TenantInfoExportQueryPageListInfoRes {


  /***
   *  id
   */
  @ExcelProperty("id")
  private Long id;
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


  /***
   *  创建时间
   */
  @ExcelProperty("创建时间")
  private LocalDateTime createTime;
  /***
   *  创建人id
   */
  @ExcelProperty("创建人id")
  private Long createBy;
  /***
   *  更新时间
   */
  @ExcelProperty("更新时间")
  private LocalDateTime updateTime;
  /***
   *  更新人id
   */
  @ExcelProperty("更新人id")
  private Long updateBy;
  /***
   *  链路追踪ID
   */
  @ExcelProperty("链路追踪ID")
  private String traceId;
  @ExcelProperty("${column.comment}")
  private Long tenantId;

}


