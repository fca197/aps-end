package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsBom.*;
import com.olivia.peanut.aps.converter.ApsBomConverter;
import com.olivia.peanut.aps.mapper.ApsBomMapper;
import com.olivia.peanut.aps.model.ApsBom;
import com.olivia.peanut.aps.model.ApsBomGroup;
import com.olivia.peanut.aps.service.ApsBomGroupService;
import com.olivia.peanut.aps.service.ApsBomService;
import com.olivia.peanut.aps.service.ApsProduceProcessService;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.LambdaQueryUtil;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.olivia.sdk.utils.Str.UN_CHECKED;

/**
 * BOM 清单(ApsBom)表服务实现类
 *
 * @author peanut
 * @since 2024-06-06 11:27:34
 */
@Service("apsBomService")
@Transactional
public class ApsBomServiceImpl extends MPJBaseServiceImpl<ApsBomMapper, ApsBom> implements ApsBomService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  // 以下为私有对象封装
  @Resource
  ApsBomGroupService apsBomGroupService;
  @Resource
  SetNameService setNameService;

  public @Override ApsBomQueryListRes queryList(ApsBomQueryListReq req) {

    MPJLambdaWrapper<ApsBom> q = getWrapper(req.getData());
    List<ApsBom> list = this.list(q);

    List<ApsBomDto> dataList = ApsBomConverter.INSTANCE.queryListRes(list);
//   //  this.setName(dataList);
    ((ApsBomService) AopContext.currentProxy()).setName(dataList);

    return new ApsBomQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsBomExportQueryPageListInfoRes> queryPageList(ApsBomExportQueryPageListReq req) {

    DynamicsPage<ApsBom> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsBom> q = getWrapper(req.getData());
    List<ApsBomExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsBom> list = this.page(page, q);
      IPage<ApsBomExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsBomExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = ApsBomConverter.INSTANCE.queryPageListRes(this.list(q));
    }
    ((ApsBomService) AopContext.currentProxy()).setName(records);
    return DynamicsPage.init(page, records);
  }


  public @Override void setName(List<? extends ApsBomDto> apsBomDtoList) {
    setNameService.setName(apsBomDtoList, SetNamePojoUtils.OP_USER_NAME, SetNamePojoUtils.getSetNamePojo(ApsProduceProcessService.class, "produceProcessName", "produceProcessId", "produceProcessName"));
  }


  @SuppressWarnings(UN_CHECKED)
  private MPJLambdaWrapper<ApsBom> getWrapper(ApsBomDto obj) {
    MPJLambdaWrapper<ApsBom> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      LambdaQueryUtil.lambdaQueryWrapper(q, obj, ApsBom.class, ApsBom::getGroupId,//
          ApsBom::getBomCode, ApsBom::getBomName, ApsBom::getSupplyMode, BaseEntity::getId);
      if (Objects.nonNull(obj.getGroupId())) {
        ApsBomGroup apsBomGroup = apsBomGroupService.getById(obj.getGroupId());
        if (Objects.nonNull(apsBomGroup)) {
          List<Long> idList = this.apsBomGroupService.list(new LambdaQueryWrapper<ApsBomGroup>().select(BaseEntity::getId).likeRight(ApsBomGroup::getPathId, apsBomGroup.getPathId())).stream().map(BaseEntity::getId).toList();
          if (CollUtil.isNotEmpty(idList)) {
            q.in(ApsBom::getGroupId, idList);
          }
        }
      }
    }
    q.orderByDesc(ApsBom::getId);
    return q;
  }

  private void setQueryListHeader(DynamicsPage<ApsBom> page) {

    ServiceComment.header(page, "ApsBomService#queryPageList");

  }


}

