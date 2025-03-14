package com.olivia.peanut.base.api.entity.calendar;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 工作日历(Calendar)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:03
 */
@Accessors(chain = true)
@Getter
@Setter

public class CalendarQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<Info> dataList;


  @Getter
  @Setter
  public static class Info extends BaseEntityDto {

    /***
     *  所属租户id
     */
    private Long tenantId;
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
}

