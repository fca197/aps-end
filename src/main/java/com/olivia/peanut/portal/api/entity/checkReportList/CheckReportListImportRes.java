package com.olivia.peanut.portal.api.entity.checkReportList;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 报表检查记录信息(CheckReportList)保存返回
 *
 * @author makejava
 * @since 2024-03-14 13:31:37
 */
@Accessors(chain = true)
@Getter
@Setter

public class CheckReportListImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

