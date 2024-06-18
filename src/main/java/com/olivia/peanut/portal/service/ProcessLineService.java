package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.processLine.ProcessLineExportQueryPageListInfoRes;
import com.olivia.peanut.portal.api.entity.processLine.ProcessLineExportQueryPageListReq;
import com.olivia.peanut.portal.api.entity.processLine.ProcessLineQueryListReq;
import com.olivia.peanut.portal.api.entity.processLine.ProcessLineQueryListRes;
import com.olivia.peanut.portal.model.ProcessLine;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * 流水线信息(ProcessLine)表服务接口
 *
 * @author makejava
 * @since 2024-03-11 10:55:21
 */
public interface ProcessLineService extends MPJBaseService<ProcessLine> {

  ProcessLineQueryListRes queryList(ProcessLineQueryListReq req);

  DynamicsPage<ProcessLineExportQueryPageListInfoRes> queryPageList(ProcessLineExportQueryPageListReq req);

}

