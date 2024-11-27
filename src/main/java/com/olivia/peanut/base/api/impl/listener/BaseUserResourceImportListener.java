package com.olivia.peanut.base.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.olivia.peanut.base.api.entity.baseUserResource.BaseUserResourceImportReq;

import java.util.Map;

/**
 * 用户角色资源表(BaseUserResource)文件导入监听
 *
 * @author peanut
 * @since 2024-08-09 16:26:40
 */
public class BaseUserResourceImportListener extends AnalysisEventListener<BaseUserResourceImportReq> {

  @Override
  public void invoke(BaseUserResourceImportReq data, AnalysisContext analysisContext) {
    //  文件校验
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    // 数据处理完毕后的操作（如果需要）
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
