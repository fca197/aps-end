package com.olivia.peanut.listener;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.holder.ReadSheetHolder;
import com.olivia.peanut.util.CheckObjectFieldValueUtils;
import com.olivia.sdk.dto.ExcelErrorMsg;
import com.olivia.sdk.exception.RunException;
import com.olivia.sdk.utils.$;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractImportListener<T> extends AnalysisEventListener<T> {
  private final List<ExcelErrorMsg> errorMsgList = new ArrayList<>();

  public void checkData(T data, AnalysisContext analysisContext) {
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
