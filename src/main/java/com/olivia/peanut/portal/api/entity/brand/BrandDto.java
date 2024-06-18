package com.olivia.peanut.portal.api.entity.brand;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 品牌信息(Brand)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:02
 */
//@Accessors(chain=true)
@Getter
@Setter

public class BrandDto {


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
   *  品牌编码
   */
  @ExcelProperty("品牌编码")
  private String brandCode;
  /***
   *  品牌名称
   */
  @ExcelProperty("品牌名称")
  private String brandName;

  /***
   *  是否在用
   */
  @ExcelProperty("在用")
  private Boolean isUsed;
  private Integer versionNum = 1;
}


