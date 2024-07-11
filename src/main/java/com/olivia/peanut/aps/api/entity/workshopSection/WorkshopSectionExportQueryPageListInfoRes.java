package com.olivia.peanut.aps.api.entity.workshopSection;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工段信息(WorkshopSection)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:55:22
 */
@Accessors(chain = true)
@Getter
@Setter

public class WorkshopSectionExportQueryPageListInfoRes extends BaseEntityDto {


  /***
   *  所属租户id
   */
  @ExcelProperty("所属租户id")
  private Long tenantId;
  /***
   *  所属工厂id
   */
  @ExcelProperty("所属工厂id")
  private Long factoryId;
  /***
   *  工段名称
   */
  @ExcelProperty("工段名称")
  private String sectionName;
  /***
   *  工段编码
   */
  @ExcelProperty("工段编码")
  private String sectionCode;
  /***
   *  工段类型
   */
  @ExcelProperty("工段类型")
  private String sectionType;
  /***
   *  工段状态
   */
  @ExcelProperty("工段状态 ")
  private String sectionStatus;


}


