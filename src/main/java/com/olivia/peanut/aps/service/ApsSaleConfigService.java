package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsSaleConfig.*;
import com.olivia.peanut.aps.model.ApsSaleConfig;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * (ApsSaleConfig)表服务接口
 *
 * @author peanut
 * @since 2024-03-29 23:10:39
 */
public interface ApsSaleConfigService extends MPJBaseService<ApsSaleConfig> {

  ApsSaleConfigQueryListRes queryList(ApsSaleConfigQueryListReq req);

  DynamicsPage<ApsSaleConfigExportQueryPageListInfoRes> queryPageList(ApsSaleConfigExportQueryPageListReq req);

  void setName(List<? extends ApsSaleConfigDto> apsSaleConfigDtoList);
}

