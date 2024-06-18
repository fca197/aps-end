package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.jcxGoodsWarning.JcxGoodsWarningExportQueryPageListInfoRes;
import com.olivia.peanut.portal.api.entity.jcxGoodsWarning.JcxGoodsWarningExportQueryPageListReq;
import com.olivia.peanut.portal.api.entity.jcxGoodsWarning.JcxGoodsWarningQueryListReq;
import com.olivia.peanut.portal.api.entity.jcxGoodsWarning.JcxGoodsWarningQueryListRes;
import com.olivia.peanut.portal.model.JcxGoodsWarning;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * (JcxGoodsWarning)表服务接口
 *
 * @author peanut
 * @since 2024-03-24 14:10:55
 */
public interface JcxGoodsWarningService extends MPJBaseService<JcxGoodsWarning> {

  JcxGoodsWarningQueryListRes queryList(JcxGoodsWarningQueryListReq req);

  DynamicsPage<JcxGoodsWarningExportQueryPageListInfoRes> queryPageList(JcxGoodsWarningExportQueryPageListReq req);

}

