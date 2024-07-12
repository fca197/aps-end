package com.olivia.peanut.aps.api.entity.workshopStation;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工位信息(WorkshopStation)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:55:23
 */
@Accessors(chain = true)
@Getter
@Setter

public class WorkshopStationExportQueryPageListInfoRes extends BaseEntityDto {


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
   *  所属工段id
   */
  @ExcelProperty("所属工段id")
  private Long sectionId;
  /***
   *  工位名称
   */
  @ExcelProperty("工位名称")
  private String stationName;
  /***
   *  工位编码
   */
  @ExcelProperty("工位编码")
  private String stationCode;
  /***
   *  工位类型
   */
  @ExcelProperty("工位类型")
  private String stationType;
  /***
   *  工位状态
   */
  @ExcelProperty("工位状态 ")
  private String stationStatus;

}


