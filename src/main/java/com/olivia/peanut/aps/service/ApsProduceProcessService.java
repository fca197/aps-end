package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsProduceProcess;

import java.util.List;

import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsProduceProcess.*;

/**
 * aps 生产路径(ApsProduceProcess)表服务接口
 *
 * @author makejava
 * @since 2024-10-24 17:00:19
 */
public interface ApsProduceProcessService extends MPJBaseService<ApsProduceProcess> {
  ApsProduceProcessQueryListRes queryList(ApsProduceProcessQueryListReq req);

  DynamicsPage<ApsProduceProcessExportQueryPageListInfoRes> queryPageList(ApsProduceProcessExportQueryPageListReq req);


  void setName(List<? extends ApsProduceProcessDto> apsProduceProcessDtoList);

  void save(ApsProduceProcessInsertReq req);

  void updateById(ApsProduceProcessUpdateByIdReq req);
}

