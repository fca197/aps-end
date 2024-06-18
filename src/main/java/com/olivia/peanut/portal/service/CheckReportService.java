package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.checkReport.CheckReportExportQueryPageListInfoRes;
import com.olivia.peanut.portal.api.entity.checkReport.CheckReportExportQueryPageListReq;
import com.olivia.peanut.portal.api.entity.checkReport.CheckReportQueryListReq;
import com.olivia.peanut.portal.api.entity.checkReport.CheckReportQueryListRes;
import com.olivia.peanut.portal.model.CheckReport;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * 报表信息(CheckReport)表服务接口
 *
 * @author makejava
 * @since 2024-03-14 13:31:35
 */
public interface CheckReportService extends MPJBaseService<CheckReport> {

  CheckReportQueryListRes queryList(CheckReportQueryListReq req);

  DynamicsPage<CheckReportExportQueryPageListInfoRes> queryPageList(CheckReportExportQueryPageListReq req);

}

