package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.jcxBuyPlanItem.JcxBuyPlanItemExportQueryPageListInfoRes;
import com.olivia.peanut.portal.api.entity.jcxBuyPlanItem.JcxBuyPlanItemExportQueryPageListReq;
import com.olivia.peanut.portal.api.entity.jcxBuyPlanItem.JcxBuyPlanItemQueryListReq;
import com.olivia.peanut.portal.api.entity.jcxBuyPlanItem.JcxBuyPlanItemQueryListRes;
import com.olivia.peanut.portal.model.JcxBuyPlanItem;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * (JcxBuyPlanItem)表服务接口
 *
 * @author peanut
 * @since 2024-03-24 20:27:12
 */
public interface JcxBuyPlanItemService extends MPJBaseService<JcxBuyPlanItem> {

  JcxBuyPlanItemQueryListRes queryList(JcxBuyPlanItemQueryListReq req);

  DynamicsPage<JcxBuyPlanItemExportQueryPageListInfoRes> queryPageList(JcxBuyPlanItemExportQueryPageListReq req);

}

