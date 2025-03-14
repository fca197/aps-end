package com.olivia.peanut.base.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.base.api.entity.factory.FactoryExportQueryPageListInfoRes;
import com.olivia.peanut.base.api.entity.factory.FactoryExportQueryPageListReq;
import com.olivia.peanut.base.api.entity.factory.FactoryQueryListReq;
import com.olivia.peanut.base.api.entity.factory.FactoryQueryListRes;
import com.olivia.peanut.base.model.Factory;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * 工段信息(Factory)表服务接口
 *
 * @author makejava
 * @since 2024-03-11 10:44:05
 */
public interface FactoryService extends MPJBaseService<Factory> {

  FactoryQueryListRes queryList(FactoryQueryListReq req);

  DynamicsPage<FactoryExportQueryPageListInfoRes> queryPageList(FactoryExportQueryPageListReq req);

}

