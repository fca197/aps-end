package com.olivia.peanut.aps.api.impl.listener;


import com.olivia.peanut.aps.model.ApsOrderGoodsHistory;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsHistory.*;
import com.alibaba.excel.context.AnalysisContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import com.olivia.sdk.listener.AbstractImportListener;

import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson2.JSON;

/**
 * 历史订单记录(ApsOrderGoodsHistory)文件导入监听
 *
 * @author makejava
 * @since 2025-02-20 17:18:46
 */
@Slf4j
public class ApsOrderGoodsHistoryImportListener extends AbstractImportListener<ApsOrderGoodsHistoryImportReq> {

  @Override
  public void invoke(ApsOrderGoodsHistoryImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsOrderGoodsHistoryImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
