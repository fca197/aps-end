package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsOrderUser.*;
import com.olivia.peanut.aps.model.ApsOrderUser;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (ApsOrderUser)表服务接口
 *
 * @author peanut
 * @since 2024-04-09 13:19:39
 */
public interface ApsOrderUserService extends MPJBaseService<ApsOrderUser> {

  ApsOrderUserQueryListRes queryList(ApsOrderUserQueryListReq req);

  DynamicsPage<ApsOrderUserExportQueryPageListInfoRes> queryPageList(ApsOrderUserExportQueryPageListReq req);


  void setName(List<? extends ApsOrderUserDto> apsOrderUserDtoList);
}

