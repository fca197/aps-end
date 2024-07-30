package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsOrderGoodsBom;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsOrderGoodsBom.*;

/**
 * 订单商品零件表(ApsOrderGoodsBom)表服务接口
 *
 * @author peanut
 * @since 2024-07-30 10:28:23
 */
public interface ApsOrderGoodsBomService extends MPJBaseService<ApsOrderGoodsBom> {

  ApsOrderGoodsBomQueryListRes queryList(ApsOrderGoodsBomQueryListReq req);

  DynamicsPage<ApsOrderGoodsBomExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsBomExportQueryPageListReq req);


  void setName(List<? extends ApsOrderGoodsBomDto> apsOrderGoodsBomDtoList);
}

