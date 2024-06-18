package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.goods.GoodsExportQueryPageListReq;
import com.olivia.peanut.portal.api.entity.goods.GoodsQueryListReq;
import com.olivia.peanut.portal.api.entity.goods.GoodsQueryListRes;
import com.olivia.peanut.portal.api.entity.goods.JcxGoodsExportQueryPageListInfoRes;
import com.olivia.peanut.portal.model.JcxGoods;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * 商品信息(Goods)表服务接口
 *
 * @author makejava
 * @since 2024-03-11 10:44:06
 */
public interface JcxGoodsService extends MPJBaseService<JcxGoods> {

  GoodsQueryListRes queryList(GoodsQueryListReq req);

  DynamicsPage<JcxGoodsExportQueryPageListInfoRes> queryPageList(GoodsExportQueryPageListReq req);

}

