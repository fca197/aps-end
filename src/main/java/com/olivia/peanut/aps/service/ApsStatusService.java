package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsStatus.*;
import com.olivia.peanut.aps.model.ApsStatus;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (ApsStatus)表服务接口
 *
 * @author peanut
 * @since 2024-04-01 10:50:12
 */
public interface ApsStatusService extends MPJBaseService<ApsStatus> {

  ApsStatusQueryListRes queryList(ApsStatusQueryListReq req);

  DynamicsPage<ApsStatusExportQueryPageListInfoRes> queryPageList(ApsStatusExportQueryPageListReq req);


  void setName(List<? extends ApsStatusDto> apsStatusDtoList);

}

