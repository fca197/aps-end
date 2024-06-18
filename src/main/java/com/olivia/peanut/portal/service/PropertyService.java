package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.property.PropertyExportQueryPageListInfoRes;
import com.olivia.peanut.portal.api.entity.property.PropertyExportQueryPageListReq;
import com.olivia.peanut.portal.api.entity.property.PropertyQueryListReq;
import com.olivia.peanut.portal.api.entity.property.PropertyQueryListRes;
import com.olivia.peanut.portal.model.Property;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * 资产信息(Property)表服务接口
 *
 * @author makejava
 * @since 2024-03-11 17:20:53
 */
public interface PropertyService extends MPJBaseService<Property> {

  PropertyQueryListRes queryList(PropertyQueryListReq req);

  DynamicsPage<PropertyExportQueryPageListInfoRes> queryPageList(PropertyExportQueryPageListReq req);

}

