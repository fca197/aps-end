package com.olivia.peanut.base.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.base.model.BaseApp;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.base.api.entity.baseApp.*;

/**
 * 应用表(BaseApp)表服务接口
 *
 * @author peanut
 * @since 2024-08-05 11:18:58
 */
public interface BaseAppService extends MPJBaseService<BaseApp> {

  BaseAppQueryListRes queryList(BaseAppQueryListReq req);

  DynamicsPage<BaseAppExportQueryPageListInfoRes> queryPageList(BaseAppExportQueryPageListReq req);


  void setName(List<? extends BaseAppDto> baseAppDtoList);
}

