package com.olivia.peanut.portal.api.entity.checkReport;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 报表信息(CheckReport)保存返回
 *
 * @author makejava
 * @since 2024-03-14 13:31:35
 */
@Accessors(chain = true)
@Getter
@Setter

public class CheckReportImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

