package com.olivia.peanut.aps.api.entity.workshopSection;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 工段信息(WorkshopSection)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:55:23
 */
//@Accessors(chain=true)
@Getter
@Setter

public class WorkshopSectionDto extends BaseEntityDto {

  /***
   *  工段名称
   */
  @NotBlank(message = "工段名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @ExcelProperty("工段名称")
  private String sectionName;
  /***
   *  工段编码
   */
  @NotBlank(message = "工段编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @ExcelProperty("工段编码")
  private String sectionCode;


}


