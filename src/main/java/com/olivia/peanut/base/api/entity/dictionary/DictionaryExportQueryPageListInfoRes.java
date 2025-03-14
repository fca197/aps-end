package com.olivia.peanut.base.api.entity.dictionary;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 字典值(Dictionary)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:04
 */
@Accessors(chain = true)
@Getter
@Setter

public class DictionaryExportQueryPageListInfoRes extends BaseEntityDto {


  /***
   *  字典组
   */
  @ExcelProperty("字典组")
  private String dictionaryGroup;
  /***
   *  字典值
   */
  @ExcelProperty("字典值")
  private String dictionaryValue;
  /***
   *  排序
   */
  @ExcelProperty("排序")
  private Integer dictionarySort;
  /***
   *  而外信息
   */
  @ExcelProperty("而外信息")
  private String dictionaryExt;

}


