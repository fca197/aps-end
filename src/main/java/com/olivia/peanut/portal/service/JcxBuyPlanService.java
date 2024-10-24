package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.jcxBuyPlan.*;
import com.olivia.peanut.portal.model.JcxBuyPlan;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (JcxBuyPlan)表服务接口
 *
 * @author peanut
 * @since 2024-03-24 20:27:11
 */
public interface JcxBuyPlanService extends MPJBaseService<JcxBuyPlan> {

  JcxBuyPlanQueryListRes queryList(JcxBuyPlanQueryListReq req);

  DynamicsPage<JcxBuyPlanExportQueryPageListInfoRes> queryPageList(JcxBuyPlanExportQueryPageListReq req);

  JcxBuyPlanInsertRes save(JcxBuyPlanInsertReq req);

  void setName(List<? extends JcxBuyPlanDto> jcxBuyPlanDtoList);

  void updateById(JcxBuyPlanUpdateByIdReq req);
}

