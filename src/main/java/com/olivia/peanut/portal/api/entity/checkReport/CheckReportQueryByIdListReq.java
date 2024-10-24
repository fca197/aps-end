package com.olivia.peanut.portal.api.entity.checkReport;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 报表信息(CheckReport)查询对象入参
 *
 * @author makejava
 * @since 2024-03-14 13:31:36
 */
@Accessors(chain = true)
@Getter
@Setter

public class CheckReportQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

