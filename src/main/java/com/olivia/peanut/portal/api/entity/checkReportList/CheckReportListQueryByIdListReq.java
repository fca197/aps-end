package com.olivia.peanut.portal.api.entity.checkReportList;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 报表检查记录信息(CheckReportList)查询对象入参
 *
 * @author makejava
 * @since 2024-03-14 13:31:37
 */
@Accessors(chain = true)
@Getter
@Setter

public class CheckReportListQueryByIdListReq {

  private List<Long> idList;


}

