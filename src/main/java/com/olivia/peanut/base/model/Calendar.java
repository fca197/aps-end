package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工作日历(Calendar)表实体类
 *
 * @author makejava
 * @since 2024-03-11 10:44:03
 */
@Accessors(chain = true)
@Getter
@Setter
//
@TableName("t_calendar")
public class Calendar extends BaseEntity {

  /***
   *  所属工厂id
   */
  private Long factoryId;
  /***
   *  日历名称
   */
  private String calendarName;
  /***
   *  日历编码
   */
  private String calendarCode;
  /***
   *  日历类型
   */
  private String calendarType;
  /***
   *  日历描述
   */
  private String calendarDesc;
  /***
   *  日历状态 0 启用 ,1 禁用
   */
  private Boolean calendarDisabled;

}

