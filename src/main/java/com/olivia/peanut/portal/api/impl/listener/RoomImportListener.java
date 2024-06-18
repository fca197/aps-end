package com.olivia.peanut.portal.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.olivia.peanut.portal.api.entity.room.RoomImportReq;
import java.util.Map;

/**
 * 房间信息(Room)文件导入监听
 *
 * @author makejava
 * @since 2024-03-11 17:20:55
 */
public class RoomImportListener extends AnalysisEventListener<RoomImportReq> {

  @Override
  public void invoke(RoomImportReq data, AnalysisContext analysisContext) {
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
