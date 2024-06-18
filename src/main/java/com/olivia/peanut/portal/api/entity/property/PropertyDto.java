package com.olivia.peanut.portal.api.entity.property;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalDateTime;
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

public class PropertyDto {


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


  /***
   *  创建时间
   */
  @ExcelProperty("创建时间")
  private LocalDateTime createTime;
  /***
   *  创建人id
   */
  @ExcelProperty("创建人id")
  private Long createBy;
  /***
   *  更新时间
   */
  @ExcelProperty("更新时间")
  private LocalDateTime updateTime;
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
  /***
   *  版本号
   */
  @ExcelProperty("版本号")
  private Integer versionNum;

  public String getInUseStr() {
    return Boolean.TRUE.equals(this.inUse) ? "是" : "否";
  }
}


