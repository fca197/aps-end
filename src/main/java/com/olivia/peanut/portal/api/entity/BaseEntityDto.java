package com.olivia.peanut.portal.api.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson2.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class BaseEntityDto implements BaseCheck {


  /***
   *  id
   */
  @ExcelIgnore
  private Long id;
  /***
   *  所属租户id
   */
  @ExcelIgnore
  private Long tenantId;
  @ExcelProperty("所属租户Name")
  private String tenantName;

  /***
   *  创建时间
   */
  @ExcelProperty("创建时间")
  private LocalDateTime createTime;

  @ExcelIgnore
  private Long createBy;
  @ExcelIgnore
  private Long updateBy;

  /***
   *  更新时间
   */
  @ExcelProperty("更新时间")
  private LocalDateTime updateTime;
  @ExcelIgnore
  private Integer versionNum;
  @ExcelProperty("创建人")
  private String createUserName;
  @ExcelProperty("更新人")
  private String updateUserName;

  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }


}
