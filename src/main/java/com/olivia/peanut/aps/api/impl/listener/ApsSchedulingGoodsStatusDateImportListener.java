package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.api.entity.apsSchedulingGoodsStatusDate.ApsSchedulingGoodsStatusDateImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 订单商品状态表(ApsSchedulingGoodsStatusDate)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:31
 */
@Slf4j
public class ApsSchedulingGoodsStatusDateImportListener extends AbstractImportListener<ApsSchedulingGoodsStatusDateImportReq> {

  @Override
  public void invoke(ApsSchedulingGoodsStatusDateImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsSchedulingGoodsStatusDateImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
