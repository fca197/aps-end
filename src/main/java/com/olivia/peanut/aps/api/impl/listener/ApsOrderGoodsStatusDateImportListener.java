package com.olivia.peanut.aps.api.impl.listener;


import com.olivia.peanut.aps.model.ApsOrderGoodsStatusDate;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsStatusDate.*;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * 订单商品状态表(ApsOrderGoodsStatusDate)文件导入监听
 *
 * @author peanut
 * @since 2024-06-14 10:26:59
 */
public class ApsOrderGoodsStatusDateImportListener extends AnalysisEventListener<ApsOrderGoodsStatusDateImportReq> {

  @Override
  public void invoke(ApsOrderGoodsStatusDateImportReq data, AnalysisContext analysisContext) {
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
