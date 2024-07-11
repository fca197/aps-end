package com.olivia.peanut.portal.api.entity.processLine;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

/**
 * 流水线信息(ProcessLine)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:55:21
 */
//@Accessors(chain=true)
@Getter
@Setter

public class ProcessLineDto extends BaseEntityDto {


  /***
   *  所属工厂id
   */
  @ExcelProperty("所属工厂id")
  private Long factoryId;
  /***
   *  所属进程id
   */
  @ExcelProperty("所属进程id")
  private Long processId;
  /***
   *  线别名称
   */
  @ExcelProperty("线别名称")
  private String lineName;
  /***
   *  线别编码
   */
  @ExcelProperty("线别编码")
  private String lineCode;
  /***
   *  线别描述
   */
  @ExcelProperty("线别描述")
  private String lineDesc;
  /***
   *  线别排序
   */
  @ExcelProperty("线别排序")
  private Integer lineSort;


}


