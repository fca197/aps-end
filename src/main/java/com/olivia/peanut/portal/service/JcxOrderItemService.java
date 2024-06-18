package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.jcxOrderItem.JcxOrderItemExportQueryPageListInfoRes;
import com.olivia.peanut.portal.api.entity.jcxOrderItem.JcxOrderItemExportQueryPageListReq;
import com.olivia.peanut.portal.api.entity.jcxOrderItem.JcxOrderItemQueryListReq;
import com.olivia.peanut.portal.api.entity.jcxOrderItem.JcxOrderItemQueryListRes;
import com.olivia.peanut.portal.model.JcxOrderItem;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * (JcxOrderItem)表服务接口
 *
 * @author peanut
 * @since 2024-03-22 10:38:07
 */
public interface JcxOrderItemService extends MPJBaseService<JcxOrderItem> {

  JcxOrderItemQueryListRes queryList(JcxOrderItemQueryListReq req);

  DynamicsPage<JcxOrderItemExportQueryPageListInfoRes> queryPageList(JcxOrderItemExportQueryPageListReq req);

}

