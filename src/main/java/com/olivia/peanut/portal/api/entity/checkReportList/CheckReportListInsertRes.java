package com.olivia.peanut.portal.api.entity.checkReportList;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 报表检查记录信息(CheckReportList)保存返回
 *
 * @author makejava
 * @since 2024-03-14 13:31:36
 */
@Accessors(chain = true)
@Getter
@Setter

public class CheckReportListInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
  private String reportName;

}

