package com.olivia.peanut.portal.api.entity.property;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.utils.Str;
import lombok.Getter;
import lombok.Setter;

/**
 * 资产信息(Property)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 17:20:54
 */
//@Accessors(chain=true)
@Getter
@Setter

public class PropertyDto extends BaseEntityDto {

  /***
   *  所属工厂id
   */
  @ExcelIgnore
  private Long factoryId;
  @ExcelProperty("工厂")
  private String factoryName;
  /***
   *  楼层
   */
  @ExcelIgnore
  private Long storeyId;

  @ExcelProperty("楼层")
  private String storeyName;
  /***
   *  房间ID
   */
  @ExcelIgnore
  private Long roomId;
  @ExcelProperty("房间")
  private String roomName;
  /***
   *  资产编号
   */
  @ExcelProperty("资产编号")
//  @MaskValue
  private String propertyCode;
  /***
   *  资产
   */
  @ExcelProperty("资产")
  private String propertyName;
  /***
   *  是否在用  1是, 0否
   */
  @ExcelProperty("是否在用  1是, 0否")
  private Boolean inUse;


  public String getInUseStr() {

    return Str.booleanToStr(inUse);
  }
}


