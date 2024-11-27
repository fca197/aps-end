package com.olivia.peanut.aps.api.entity.workshopStation;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
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


}


