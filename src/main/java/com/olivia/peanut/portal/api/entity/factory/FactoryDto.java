package com.olivia.peanut.portal.api.entity.factory;

import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalTime;
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

public class FactoryDto {


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


  /***
   *  创建时间
   */
  @ExcelProperty("创建时间")
  private LocalTime createTime;
  /***
   *  创建人id
   */
  @ExcelProperty("创建人id")
  private Long createBy;
  /***
   *  更新时间
   */
  @ExcelProperty("更新时间")
  private LocalTime updateTime;
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

}


