package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMain.*;
import com.olivia.peanut.aps.mapper.ApsGoodsForecastMainMapper;
import com.olivia.peanut.aps.model.ApsGoodsForecastMain;
import com.olivia.peanut.aps.service.ApsGoodsForecastMainService;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
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
 * (ApsGoodsForecastMain)表服务实现类
 *
 * @author peanut
 * @since 2024-04-02 09:42:27
 */
@Service("apsGoodsForecastMainService")
@Transactional
public class ApsGoodsForecastMainServiceImpl extends MPJBaseServiceImpl<ApsGoodsForecastMainMapper, ApsGoodsForecastMain> implements ApsGoodsForecastMainService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();
  @Resource
  SetNameService setNameService;

  public @Override ApsGoodsForecastMainQueryListRes queryList(ApsGoodsForecastMainQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsForecastMain> q = getWrapper(req.getData());
    List<ApsGoodsForecastMain> list = this.list(q);

    List<ApsGoodsForecastMainDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsForecastMainDto.class)).collect(Collectors.toList());
    ((ApsGoodsForecastMainServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsGoodsForecastMainQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsGoodsForecastMainExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMainExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsForecastMain> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsForecastMain> q = getWrapper(req.getData());
    List<ApsGoodsForecastMainExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsForecastMain> list = this.page(page, q);
      IPage<ApsGoodsForecastMainExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsForecastMainExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsForecastMainExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsForecastMainExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsForecastMainExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsGoodsForecastMainServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  @SetUserName
  public @Override void setName(List<? extends ApsGoodsForecastMainDto> apsGoodsForecastMainDtoList) {

    setNameService.setName(apsGoodsForecastMainDtoList, List.of(SetNamePojoUtils.GOODS, SetNamePojoUtils.FACTORY));
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ApsGoodsForecastMain> getWrapper(ApsGoodsForecastMainDto obj) {
    MPJLambdaWrapper<ApsGoodsForecastMain> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getGoodsId()), ApsGoodsForecastMain::getGoodsId, obj.getGoodsId())
          .eq(StringUtils.isNoneBlank(obj.getForecastNo()), ApsGoodsForecastMain::getForecastNo, obj.getForecastNo())
          .likeRight(StringUtils.isNoneBlank(obj.getForecastName()), ApsGoodsForecastMain::getForecastName, obj.getForecastName())
          .eq(StringUtils.isNoneBlank(obj.getForecastBeginDate()), ApsGoodsForecastMain::getForecastBeginDate, obj.getForecastBeginDate())
          .eq(StringUtils.isNoneBlank(obj.getForecastEndDate()), ApsGoodsForecastMain::getForecastEndDate, obj.getForecastEndDate())
          .eq(StringUtils.isNoneBlank(obj.getMonth()), ApsGoodsForecastMain::getMonth, obj.getMonth())
          .eq(Objects.nonNull(obj.getMonths()), ApsGoodsForecastMain::getMonths, obj.getMonths())

      ;
    }
    q.orderByDesc(ApsGoodsForecastMain::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsForecastMain> page) {

    ServiceComment.header(page, "ApsGoodsForecastMainService#queryPageList");

  }


}

