package com.olivia.peanut.aps.api.impl.listener;


import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.holder.ReadSheetHolder;
import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.api.entity.apsBom.ApsBomImportReq;
import com.olivia.peanut.util.CheckObjectFieldValueUtils;
import com.olivia.sdk.dto.ExcelErrorMsg;
import com.olivia.sdk.exception.RunException;
import com.olivia.sdk.utils.$;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * BOM 清单(ApsBom)文件导入监听
 *
 * @author peanut
 * @since 2024-06-06 11:27:34
 */
@Slf4j
public class ApsBomImportListener extends AnalysisEventListener<ApsBomImportReq> {

  List<ExcelErrorMsg> errorMsgList = new ArrayList<>();

  @Override
  public void invoke(ApsBomImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsBomImportListener invoke data:{}", JSON.toJSONString(data));
    ReadSheetHolder readHolder = analysisContext.readSheetHolder();
    Integer rowIndex = readHolder.getRowIndex();
    List<ExcelErrorMsg> errorMsg = CheckObjectFieldValueUtils.check(data);
    if (CollUtil.isNotEmpty(errorMsg)) {
      errorMsg.forEach(t -> {
        t.setRowIndex(rowIndex);
        errorMsgList.add(t);
      });
    }
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    // 数据处理完毕后的操作（如果需要）
    $.assertTrueException(CollUtil.isEmpty(errorMsgList), () -> new RunException(200, "文件上传失败", errorMsgList));
  }

  @Override
  public void onException(Exception exception, AnalysisContext context) throws Exception {
    // 异常处理
    super.onException(exception, context);
  }

  @Override
  public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    //  log.info("headMap:{}", JSON.toJSONString(headMap));
    super.invokeHeadMap(headMap, context);
  }

}
