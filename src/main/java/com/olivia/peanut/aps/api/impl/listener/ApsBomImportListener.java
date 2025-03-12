package com.olivia.peanut.aps.api.impl.listener;


import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsBom.ApsBomImportReq;
import com.olivia.peanut.aps.model.ApsBomGroup;
import com.olivia.peanut.aps.service.ApsBomGroupService;
import com.olivia.sdk.dto.ExcelErrorMsg;
import com.olivia.sdk.exception.RunException;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * BOM 清单(ApsBom)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:26
 */
@Slf4j
public class ApsBomImportListener extends AbstractImportListener<ApsBomImportReq> {

  final static Cache<String, Map<String, Long>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();
  List<ApsBomImportReq> reqList = new ArrayList<>();

  @Override
  public void invoke(ApsBomImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsBomImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);
    reqList.add(data);
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    Map<String, Long> nameIdMap;
    try {
      nameIdMap = new HashMap<>(cache.get("all", () -> SpringUtil.getBean(ApsBomGroupService.class)
          .list(new LambdaQueryWrapper<ApsBomGroup>().select(BaseEntity::getId, ApsBomGroup::getGroupName))
          .stream().collect(Collectors.toMap(ApsBomGroup::getGroupName, BaseEntity::getId))));
    } catch (Exception e) {
      throw new RunException(e);
    }
    reqList.forEach(t -> {
      Long groupId = nameIdMap.get(t.getGroupName());
      t.setGroupId(groupId);
      if (Objects.isNull(groupId)) {
        addExcelErrorMsg(new ExcelErrorMsg().setRowIndex(t.getRowIndex()).setColumnName("组名称").setErrMsg("对应分组不存在"));
      }
    });
    super.doAfterAllAnalysed(analysisContext);
  }
}
