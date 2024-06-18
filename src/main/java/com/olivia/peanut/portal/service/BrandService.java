package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.brand.BrandExportQueryPageListInfoRes;
import com.olivia.peanut.portal.api.entity.brand.BrandExportQueryPageListReq;
import com.olivia.peanut.portal.api.entity.brand.BrandQueryListReq;
import com.olivia.peanut.portal.api.entity.brand.BrandQueryListRes;
import com.olivia.peanut.portal.model.Brand;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * 品牌信息(Brand)表服务接口
 *
 * @author makejava
 * @since 2024-03-11 10:44:02
 */
public interface BrandService extends MPJBaseService<Brand> {

  BrandQueryListRes queryList(BrandQueryListReq req);

  DynamicsPage<BrandExportQueryPageListInfoRes> queryPageList(BrandExportQueryPageListReq req);

}

