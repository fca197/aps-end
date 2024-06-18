package com.olivia.peanut.aps.api.entity.workshopStation;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 工位信息(WorkshopStation)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:55:24
 */
//@Accessors(chain=true)
@Getter
@Setter

public class WorkshopStationDto extends BaseEntityDto {


  /***
   *  所属工厂id
   */
  @ExcelProperty("所属工厂id")
  @NotNull(message = "所属工厂id不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;
  /***
   *  所属工段id
   */
  @ExcelProperty("所属工段id")
  @NotNull(message = "所属工段id不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long sectionId;
  /***
   *  工位名称
   */
  @ExcelProperty("工位名称")
  @NotBlank(message = "工位名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String stationName;
  /***
   *  工位编码
   */
  @ExcelProperty("工位编码")
  @NotBlank(message = "工位编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String stationCode;
  /***
   *  工位类型
   */
  @ExcelProperty("工位类型")
  @NotBlank(message = "工位类型不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String stationType;
  /***
   *  工位状态
   */
  @ExcelProperty("工位状态 ")
  @NotBlank(message = "工位状态不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String stationStatus;

}


