package com.olivia.peanut.portal.api.entity.checkReportList;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
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

public class CheckReportListDto extends BaseEntityDto {


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


}


