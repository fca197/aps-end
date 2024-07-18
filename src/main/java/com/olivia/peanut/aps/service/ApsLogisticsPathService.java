package com.olivia.peanut.aps.service;

import com.olivia.peanut.aps.api.entity.apsLogisticsPath.*;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.peanut.aps.model.ApsLogisticsPath;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

/**
 * 物流路径表(ApsLogisticsPath)表服务接口
 *
 * @author peanut
 * @since 2024-07-18 13:27:36
 */
public interface ApsLogisticsPathService extends MPJBaseService<ApsLogisticsPath> {

  ApsLogisticsPathQueryListRes queryList(ApsLogisticsPathQueryListReq req);

  DynamicsPage<ApsLogisticsPathExportQueryPageListInfoRes> queryPageList(ApsLogisticsPathExportQueryPageListReq req);


  void setName(List<? extends ApsLogisticsPathDto> apsLogisticsPathDtoList);

  ApsLogisticsPathInsertRes save(ApsLogisticsPathInsertReq req);

  ApsLogisticsPathUpdateByIdRes updateById(ApsLogisticsPathUpdateByIdReq req);
}

