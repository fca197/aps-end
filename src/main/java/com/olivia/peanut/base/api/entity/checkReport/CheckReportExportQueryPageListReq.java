package com.olivia.peanut.base.api.entity.checkReport;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 报表信息(CheckReport)查询对象入参
 *
 * @author makejava
 * @since 2024-03-14 13:31:35
 */
@Accessors(chain = true)
@Getter
@Setter

public class CheckReportExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private CheckReportDto data;


}

