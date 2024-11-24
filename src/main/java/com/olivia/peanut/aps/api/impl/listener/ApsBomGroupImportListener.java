package com.olivia.peanut.aps.api.impl.listener;


import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.olivia.peanut.aps.api.entity.apsBomGroup.ApsBomGroupImportReq;
import com.olivia.peanut.aps.model.ApsBomGroup;
import com.olivia.peanut.aps.service.ApsBomGroupService;
import com.olivia.sdk.utils.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 零件组配置(ApsBomGroup)文件导入监听
 *
 * @author peanut
 * @since 2024-06-19 17:41:24
 */
public class ApsBomGroupImportListener extends AnalysisEventListener<ApsBomGroupImportReq> {

  private final List<ApsBomGroupImportReq> list = new ArrayList<>();

  @Override
  public void invoke(ApsBomGroupImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    list.add(data);
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    // 数据处理完毕后的操作（如果需要）
    list.forEach(t -> t.setId(IdWorker.getId()));
    Map<String, Long> nameIdMap = SpringUtil.getBean(ApsBomGroupService.class).list().stream().collect(Collectors.toMap(ApsBomGroup::getGroupName, BaseEntity::getId, (a, b) -> b));
    list.forEach(t -> nameIdMap.put(t.getGroupName(), t.getId()));
    list.forEach(t -> t.setParentId(nameIdMap.get(t.getParentName())));
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
