package com.olivia.peanut.base.api.entity.factory;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

/**
 * 工段信息(Factory)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:05
 */
//@Accessors(chain=true)
@Getter
@Setter

public class FactoryDto extends BaseEntityDto {


  /***
   *  工厂名称
   */
  @ExcelProperty("工厂名称")
  private String factoryName;
  /***
   *  工厂编码
   */
  @ExcelProperty("工厂编码")
  private String factoryCode;
  /***
   *  工厂状态
   */
  @ExcelProperty("工厂状态 ")
  private String factoryStatus;


}


