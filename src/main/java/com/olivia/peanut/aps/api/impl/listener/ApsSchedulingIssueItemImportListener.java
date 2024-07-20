package com.olivia.peanut.aps.api.impl.listener;


import com.olivia.peanut.aps.model.ApsSchedulingIssueItem;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.aps.api.entity.apsSchedulingIssueItem.*;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * 排产下发详情(ApsSchedulingIssueItem)文件导入监听
 *
 * @author peanut
 * @since 2024-07-20 13:55:55
 */
public class ApsSchedulingIssueItemImportListener extends AnalysisEventListener<ApsSchedulingIssueItemImportReq> {

  @Override
  public void invoke(ApsSchedulingIssueItemImportReq data, AnalysisContext analysisContext) {
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
