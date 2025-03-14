package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.baseApp.*;
import com.olivia.peanut.base.mapper.BaseAppMapper;
import com.olivia.peanut.base.model.BaseApp;
import com.olivia.peanut.base.service.BaseAppService;
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
 * 应用表(BaseApp)表服务实现类
 *
 * @author peanut
 * @since 2024-08-05 11:18:58
 */
@Service("baseAppService")
@Transactional
public class BaseAppServiceImpl extends MPJBaseServiceImpl<BaseAppMapper, BaseApp> implements BaseAppService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override BaseAppQueryListRes queryList(BaseAppQueryListReq req) {

    MPJLambdaWrapper<BaseApp> q = getWrapper(req.getData());
    List<BaseApp> list = this.list(q);

    List<BaseAppDto> dataList = list.stream().map(t -> $.copy(t, BaseAppDto.class)).collect(Collectors.toList());
    ((BaseAppService) AopContext.currentProxy()).setName(dataList);
    return new BaseAppQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<BaseAppExportQueryPageListInfoRes> queryPageList(BaseAppExportQueryPageListReq req) {

    DynamicsPage<BaseApp> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<BaseApp> q = getWrapper(req.getData());
    List<BaseAppExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<BaseApp> list = this.page(page, q);
      IPage<BaseAppExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BaseAppExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BaseAppExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<BaseAppExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BaseAppExportQueryPageListInfoRes.class);
    ((BaseAppService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends BaseAppDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<BaseApp> getWrapper(BaseAppDto obj) {
    MPJLambdaWrapper<BaseApp> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getAppCode()), BaseApp::getAppCode, obj.getAppCode())
          .eq(StringUtils.isNoneBlank(obj.getAppName()), BaseApp::getAppName, obj.getAppName())

      ;
    }
    q.orderByDesc(BaseApp::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BaseApp> page) {

    tableHeaderService.listByBizKey(page, "BaseAppService#queryPageList");

  }


}

