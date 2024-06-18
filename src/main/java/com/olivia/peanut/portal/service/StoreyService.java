package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.storey.StoreyExportQueryPageListInfoRes;
import com.olivia.peanut.portal.api.entity.storey.StoreyExportQueryPageListReq;
import com.olivia.peanut.portal.api.entity.storey.StoreyQueryListReq;
import com.olivia.peanut.portal.api.entity.storey.StoreyQueryListRes;
import com.olivia.peanut.portal.model.Storey;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.Map;

/**
 * 楼层信息(Storey)表服务接口
 *
 * @author makejava
 * @since 2024-03-11 17:20:55
 */
public interface StoreyService extends MPJBaseService<Storey> {

  StoreyQueryListRes queryList(StoreyQueryListReq req);

  DynamicsPage<StoreyExportQueryPageListInfoRes> queryPageList(StoreyExportQueryPageListReq req);

  Map<Long, Storey> listByFactoryIdList(Long factoryId);


}

