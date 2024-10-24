package com.olivia.peanut.portal.api.entity.checkReportList;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 报表检查记录信息(CheckReportList)查询对象返回
 *
 * @author makejava
 * @since 2024-03-14 13:31:36
 */
@Accessors(chain = true)
@Getter
@Setter

public class CheckReportListQueryListRes {

  /***
   * 返回对象列表
   */
  private List<CheckReportListDto> dataList;


}

