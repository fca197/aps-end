package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersionDetail;

import java.util.List;

import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetail.*;

/**
 * 排程版本配置明细表(ApsSchedulingDayConfigVersionDetail)表服务接口
 *
 * @author peanut
 * @since 2024-07-19 19:19:58
 */
public interface ApsSchedulingDayConfigVersionDetailService extends MPJBaseService<ApsSchedulingDayConfigVersionDetail> {

  ApsSchedulingDayConfigVersionDetailQueryListRes queryList(ApsSchedulingDayConfigVersionDetailQueryListReq req);

  DynamicsPage<ApsSchedulingDayConfigVersionDetailExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigVersionDetailExportQueryPageListReq req);


  void setName(List<? extends ApsSchedulingDayConfigVersionDetailDto> apsSchedulingDayConfigVersionDetailDtoList);
}

