package com.olivia.peanut.portal.api.entity.checkReportList;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 报表检查记录信息(CheckReportList)保存入参
 *
 * @author makejava
 * @since 2024-03-14 13:31:36
 */
@Accessors(chain = true)
@Getter
@Setter

public class CheckReportListInsertReq extends CheckReportListDto {

  @NotBlank(message = "资产编码不能为空")
  private String propertyCode;

  @NotNull(message = "盘点不能为空")
  private Long checkId;

  public void checkParam() {
  }
}

