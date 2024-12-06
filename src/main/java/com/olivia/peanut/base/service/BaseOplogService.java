package com.olivia.peanut.base.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.base.api.entity.baseOplog.*;
import com.olivia.peanut.base.model.BaseOplog;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 操作日志(BaseOplog)表服务接口
 *
 * @author makejava
 * @since 2024-11-30 16:01:02
 */
public interface BaseOplogService extends MPJBaseService<BaseOplog> {
  BaseOplogQueryListRes queryList(BaseOplogQueryListReq req);

  DynamicsPage<BaseOplogExportQueryPageListInfoRes> queryPageList(BaseOplogExportQueryPageListReq req);


  void setName(List<? extends BaseOplogDto> baseOplogDtoList);
}

