package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.dictionary.*;
import com.olivia.peanut.base.mapper.DictionaryMapper;
import com.olivia.peanut.base.model.Dictionary;
import com.olivia.peanut.base.service.DictionaryService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 字典值(Dictionary)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:44:04
 */
@Service("dictionaryService")
@Transactional
public class DictionaryServiceImpl extends MPJBaseServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  public @Override DictionaryQueryListRes queryList(DictionaryQueryListReq req) {

    MPJLambdaWrapper<Dictionary> q = getWrapper(req.getData());
    List<Dictionary> list = this.list(q);

    List<DictionaryQueryListRes.Info> dataList = list.stream().map(t -> $.copy(t, DictionaryQueryListRes.Info.class)).collect(Collectors.toList());

    return new DictionaryQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<DictionaryExportQueryPageListInfoRes> queryPageList(DictionaryExportQueryPageListReq req) {

    DynamicsPage<Dictionary> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<Dictionary> q = getWrapper(req.getData());
    List<DictionaryExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<Dictionary> list = this.page(page, q);
      IPage<DictionaryExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, DictionaryExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), DictionaryExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<DictionaryExportQueryPageListInfoRes> listInfoRes = $.copyList(records, DictionaryExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<Dictionary> getWrapper(DictionaryDto obj) {
    MPJLambdaWrapper<Dictionary> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), Dictionary::getId, obj.getId())
          .eq(StringUtils.isNoneBlank(obj.getDictionaryGroup()), Dictionary::getDictionaryGroup, obj.getDictionaryGroup())
          .eq(StringUtils.isNoneBlank(obj.getDictionaryValue()), Dictionary::getDictionaryValue, obj.getDictionaryValue())
          .eq(Objects.nonNull(obj.getDictionarySort()), Dictionary::getDictionarySort, obj.getDictionarySort())
          .eq(StringUtils.isNoneBlank(obj.getDictionaryExt()), Dictionary::getDictionaryExt, obj.getDictionaryExt())
          .eq(Objects.nonNull(obj.getTenantId()), Dictionary::getTenantId, obj.getTenantId())
          .eq(Objects.nonNull(obj.getCreateTime()), Dictionary::getCreateTime, obj.getCreateTime())
          .eq(Objects.nonNull(obj.getCreateBy()), Dictionary::getCreateBy, obj.getCreateBy())
          .eq(Objects.nonNull(obj.getUpdateTime()), Dictionary::getUpdateTime, obj.getUpdateTime())
          .eq(Objects.nonNull(obj.getUpdateBy()), Dictionary::getUpdateBy, obj.getUpdateBy())
          .eq(Objects.nonNull(obj.getVersionNum()), Dictionary::getVersionNum, obj.getVersionNum())
          .orderByDesc(Dictionary::getId)
      ;
    }

    return q;

  }

  private void setQueryListHeader(DynamicsPage<Dictionary> page) {
    page
        .addHeader("id", "id")
        .addHeader("dictionaryGroup", "字典组")
        .addHeader("dictionaryValue", "字典值")
        .addHeader("dictionarySort", "排序")
        .addHeader("dictionaryExt", "而外信息")
        .addHeader("tenantId", "所属租户id")
        .addHeader("isDelete", "是否删除(0:否 1:是)")
        .addHeader("createTime", "创建时间")
        .addHeader("createBy", "创建人id")
        .addHeader("updateTime", "更新时间")
        .addHeader("updateBy", "更新人id")
        .addHeader("traceId", "链路追踪ID")
        .addHeader("version", "版本号")
    ;
  }


}

