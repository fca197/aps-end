package com.olivia.peanut.portal.api.entity.checkReportList;

import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 报表检查记录信息(CheckReportList)查询对象返回
 *
 * @author makejava
 * @since 2024-03-14 13:31:37
 */
//@Accessors(chain=true)
@Getter
@Setter

public class CheckReportListDto {


  /***
   *  id
   */
  @ExcelProperty("id")
  private Long id;
  /***
   *  所属租户id
   */
  @ExcelProperty("所属租户id")
  private Long tenantId;
  /***
   *  工厂ID
   */
  @ExcelProperty("工厂ID")
  private Long factoryId;
  /***
   *  报表编码
   */
  @ExcelProperty("报表编码")
  private Long reportId;
  /***
   *  资产ID
   */
  @ExcelProperty("资产ID")
  private Long propertyId;
  /***
   *  备注
   */
  @ExcelProperty("备注")
  private String remark;


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
  /***
   *  版本号
   */
  @ExcelProperty("版本号")
  private Integer versionNum;

}


