package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsGoodsBomGroup;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsGoodsBomGroup.*;

/**
 * 零件组配置(ApsGoodsBomGroup)表服务接口
 *
 * @author peanut
 * @since 2024-06-19 16:49:04
 */
public interface ApsGoodsBomGroupService extends MPJBaseService<ApsGoodsBomGroup> {

  ApsGoodsBomGroupQueryListRes queryList(ApsGoodsBomGroupQueryListReq req);

  DynamicsPage<ApsGoodsBomGroupExportQueryPageListInfoRes> queryPageList(ApsGoodsBomGroupExportQueryPageListReq req);


  void setName(List<? extends ApsGoodsBomGroupDto> apsGoodsBomGroupDtoList);
}

