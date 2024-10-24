package com.olivia.peanut.portal.api.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson2.JSON;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
  @ExcelProperty("id")
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
  private Long createBy;
  private Long updateBy;

  /***
   *  更新时间
   */
  @ExcelProperty("更新时间")
  private LocalDateTime updateTime;
  private Integer versionNum;
  private String createUserName;
  private String updateUserName;

  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }


}
