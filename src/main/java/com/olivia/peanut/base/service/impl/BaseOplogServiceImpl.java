package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.baseOplog.*;
import com.olivia.peanut.base.mapper.BaseOplogMapper;
import com.olivia.peanut.base.model.BaseOplog;
import com.olivia.peanut.base.service.BaseOplogService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 操作日志(BaseOplog)表服务实现类
 *
 * @author makejava
 * @since 2024-11-30 16:01:02
 */
@Service("baseOplogService")
@Transactional
public class BaseOplogServiceImpl extends MPJBaseServiceImpl<BaseOplogMapper, BaseOplog> implements BaseOplogService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override BaseOplogQueryListRes queryList(BaseOplogQueryListReq req) {

    MPJLambdaWrapper<BaseOplog> q = getWrapper(req.getData());
    List<BaseOplog> list = this.list(q);

    List<BaseOplogDto> dataList = list.stream().map(t -> $.copy(t, BaseOplogDto.class)).collect(Collectors.toList());
    ((BaseOplogService) AopContext.currentProxy()).setName(dataList);
    return new BaseOplogQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<BaseOplogExportQueryPageListInfoRes> queryPageList(BaseOplogExportQueryPageListReq req) {

    DynamicsPage<BaseOplog> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<BaseOplog> q = getWrapper(req.getData());
    List<BaseOplogExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<BaseOplog> list = this.page(page, q);
      IPage<BaseOplogExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BaseOplogExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BaseOplogExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作 

    List<BaseOplogExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BaseOplogExportQueryPageListInfoRes.class);
    ((BaseOplogService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends BaseOplogDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<BaseOplog> getWrapper(BaseOplogDto obj) {
    MPJLambdaWrapper<BaseOplog> q = new MPJLambdaWrapper<>();


    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getContent()), BaseOplog::getContent, obj.getContent())
          .eq(StringUtils.isNoneBlank(obj.getBusinessType()), BaseOplog::getBusinessType, obj.getBusinessType())
          .eq(StringUtils.isNoneBlank(obj.getBusinessKey()), BaseOplog::getBusinessKey, obj.getBusinessKey())
          .eq(StringUtils.isNoneBlank(obj.getUrl()), BaseOplog::getUrl, obj.getUrl())
          .eq(StringUtils.isNoneBlank(obj.getTraceId()), BaseOplog::getTraceId, obj.getTraceId())
          .eq(StringUtils.isNoneBlank(obj.getUseTime()), BaseOplog::getUseTime, obj.getUseTime())
          .eq(StringUtils.isNoneBlank(obj.getParamName()), BaseOplog::getParamName, obj.getParamName())
          .eq(StringUtils.isNoneBlank(obj.getReqBody()), BaseOplog::getReqBody, obj.getReqBody())
          .eq(StringUtils.isNoneBlank(obj.getResultStr()), BaseOplog::getResultStr, obj.getResultStr())
          .eq(StringUtils.isNoneBlank(obj.getRemark()), BaseOplog::getRemark, obj.getRemark())

      ;
    }
    q.orderByDesc(BaseOplog::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BaseOplog> page) {

    tableHeaderService.listByBizKey(page, "BaseOplogService#queryPageList");

  }


}

