package com.olivia.peanut.base.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.olivia.peanut.base.api.entity.baseUserRoleGroup.BaseUserRoleGroupImportReq;

import java.util.Map;

/**
 * 用户角色组表(BaseUserRoleGroup)文件导入监听
 *
 * @author peanut
 * @since 2024-07-31 14:36:04
 */
public class BaseUserRoleGroupImportListener extends AnalysisEventListener<BaseUserRoleGroupImportReq> {

  @Override
  public void invoke(BaseUserRoleGroupImportReq data, AnalysisContext analysisContext) {
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
