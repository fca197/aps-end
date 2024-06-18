package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.checkReportList.CheckReportListExportQueryPageListInfoRes;
import com.olivia.peanut.portal.api.entity.checkReportList.CheckReportListExportQueryPageListReq;
import com.olivia.peanut.portal.api.entity.checkReportList.CheckReportListQueryListReq;
import com.olivia.peanut.portal.api.entity.checkReportList.CheckReportListQueryListRes;
import com.olivia.peanut.portal.model.CheckReportList;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * 报表检查记录信息(CheckReportList)表服务接口
 *
 * @author makejava
 * @since 2024-03-14 13:31:37
 */
public interface CheckReportListService extends MPJBaseService<CheckReportList> {

  CheckReportListQueryListRes queryList(CheckReportListQueryListReq req);

  DynamicsPage<CheckReportListExportQueryPageListInfoRes> queryPageList(CheckReportListExportQueryPageListReq req);

}

