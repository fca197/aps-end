package com.olivia.peanut.base.api.entity.checkReport;

import com.olivia.sdk.utils.$;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 报表信息(CheckReport)保存入参
 *
 * @author makejava
 * @since 2024-03-14 13:31:35
 */
@Accessors(chain = true)
@Getter
@Setter

public class CheckReportInsertReq extends CheckReportDto {

  public void checkParam() {
    $.requireNonNullCanIgnoreException(this.getReportCode(), "盘点编码不能为空");
    $.requireNonNullCanIgnoreException(this.getReportName(), "盘点名称不能为空");
  }
}

