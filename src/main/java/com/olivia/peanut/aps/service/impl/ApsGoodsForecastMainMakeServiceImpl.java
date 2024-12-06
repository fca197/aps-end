package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMake.*;
import com.olivia.peanut.aps.mapper.ApsGoodsForecastMainMakeMapper;
import com.olivia.peanut.aps.model.ApsGoods;
import com.olivia.peanut.aps.model.ApsGoodsForecastMainMake;
import com.olivia.peanut.aps.model.ApsGoodsForecastMainMakeSaleData;
import com.olivia.peanut.aps.service.ApsGoodsForecastMainMakeSaleDataService;
import com.olivia.peanut.aps.service.ApsGoodsForecastMainMakeService;
import com.olivia.peanut.aps.service.ApsGoodsService;
import com.olivia.peanut.portal.service.CalendarService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.DynamicsPage.Header;
import com.olivia.sdk.utils.model.WeekInfo;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.olivia.sdk.utils.FieldUtils.getField;

/**
 * (ApsGoodsForecastMainMake)表服务实现类
 *
 * @author peanut
 * @since 2024-04-08 09:52:50
 */
@Service("apsGoodsForecastMainMakeService")
@Transactional
public class ApsGoodsForecastMainMakeServiceImpl extends MPJBaseServiceImpl<ApsGoodsForecastMainMakeMapper, ApsGoodsForecastMainMake> implements ApsGoodsForecastMainMakeService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  ApsGoodsService apsGoodsService;
  @Resource
  CalendarService calendarService;
  @Resource
  ApsGoodsForecastMainMakeSaleDataService apsGoodsForecastMainMakeSaleDataService;

  // 以下为私有对象封装

  public @Override ApsGoodsForecastMainMakeQueryListRes queryList(ApsGoodsForecastMainMakeQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsForecastMainMake> q = getWrapper(req.getData());
    List<ApsGoodsForecastMainMake> list = this.list(q);

    List<ApsGoodsForecastMainMakeDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsForecastMainMakeDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsGoodsForecastMainMakeServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsGoodsForecastMainMakeQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsGoodsForecastMainMakeExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMainMakeExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsForecastMainMake> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsForecastMainMake> q = getWrapper(req.getData());
    List<ApsGoodsForecastMainMakeExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsForecastMainMake> list = this.page(page, q);
      IPage<ApsGoodsForecastMainMakeExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsForecastMainMakeExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsForecastMainMakeExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsForecastMainMakeExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsForecastMainMakeExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsGoodsForecastMainMakeServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  @SetUserName
  public @Override void setName(List<? extends ApsGoodsForecastMainMakeDto> apsGoodsForecastMainMakeDtoList) {

    if (CollUtil.isEmpty(apsGoodsForecastMainMakeDtoList)) {
      return;
    }
    Set<Long> gidSet = apsGoodsForecastMainMakeDtoList.stream().map(ApsGoodsForecastMainMakeDto::getGoodsId).collect(Collectors.toSet());
    Map<Long, String> gnMap = apsGoodsService.listByIds(gidSet).stream().collect(Collectors.toMap(BaseEntity::getId, ApsGoods::getGoodsName));
    apsGoodsForecastMainMakeDtoList.forEach(t -> t.setGoodsName(gnMap.get(t.getGoodsId())));
  }

  private MPJLambdaWrapper<ApsGoodsForecastMainMake> getWrapper(ApsGoodsForecastMainMakeDto obj) {
    MPJLambdaWrapper<ApsGoodsForecastMainMake> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getGoodsId()), ApsGoodsForecastMainMake::getGoodsId, obj.getGoodsId())
          .eq(StringUtils.isNoneBlank(obj.getForecastMakeMainNo()), ApsGoodsForecastMainMake::getForecastMakeMainNo, obj.getForecastMakeMainNo())
          .eq(StringUtils.isNoneBlank(obj.getForecastMakeMainName()), ApsGoodsForecastMainMake::getForecastMakeMainName, obj.getForecastMakeMainName())
          .eq(StringUtils.isNoneBlank(obj.getForecastMakeMainBeginDate()), ApsGoodsForecastMainMake::getForecastMakeMainBeginDate, obj.getForecastMakeMainBeginDate())
          .eq(StringUtils.isNoneBlank(obj.getForecastMakeMainEndDate()), ApsGoodsForecastMainMake::getForecastMakeMainEndDate, obj.getForecastMakeMainEndDate())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsGoodsForecastMainMake::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsGoodsForecastMainMake::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsForecastMainMake> page) {
    ServiceComment.header(page, "ApsGoodsForecastMainMakeService#queryPageList");
  }

  @Override
  public DynamicsPage<ApsGoodsForecastMainMakeQueryDataByIdRes> queryDataById(ApsGoodsForecastMainMakeQueryDataByIdReq req) {
    ApsGoodsForecastMainMake mainMake = this.getById(req.getId());
    Map<String, List<ApsGoodsForecastMainMakeSaleData>> dataList = apsGoodsForecastMainMakeSaleDataService.list(
            new LambdaQueryWrapper<ApsGoodsForecastMainMakeSaleData>().eq(ApsGoodsForecastMainMakeSaleData::getMainMakeId, req.getId())).stream()
        .collect(Collectors.groupingBy(ApsGoodsForecastMainMakeSaleData::getSaleConfigCode));

    List<WeekInfo> weekInfoList;
    if (StringUtils.isNoneBlank(req.getBeginDate(), req.getEndDate())) {
      weekInfoList = calendarService.getWeekList(mainMake.getFactoryId(), req.getBeginDate(), req.getEndDate());
    } else {
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.MONTH, 6);
      weekInfoList = calendarService.getWeekList(mainMake.getFactoryId(), DateUtil.format(new Date(), "yyyy-MM"), DateUtil.format(calendar.getTime(), "yyyy-MM"));
    }

    List<ApsGoodsForecastMainMakeQueryDataByIdRes> resArrayList = new ArrayList<>();
    dataList.forEach((code, saleDataList) -> {
      ApsGoodsForecastMainMakeQueryDataByIdRes data = new ApsGoodsForecastMainMakeQueryDataByIdRes().setSaleConfigCode(code);
      data.put("saleConfigCode", code);
      weekInfoList.stream().collect(Collectors.groupingBy(t -> t.getCurrentDate().getYear())).forEach((y, weekInfoListTmp) -> {
        saleDataList.stream().filter(t -> t.getSaleConfigCode().equals(code)).forEach(saleData -> {
          weekInfoListTmp.stream().collect(Collectors.groupingBy(t -> t.getCurrentDay().substring(5, 7) + "月" + t.getWeekNumber() + "周"))
              .forEach((mw, weekInfoListTmpTmp) -> {
                AtomicLong sum = new AtomicLong(0);
                weekInfoListTmpTmp.forEach(d -> {
                  Field field = getField(saleData, "dayNum" + d.getCurrentDate().getDayOfYear());
                  Long value = (Long) ReflectUtil.getFieldValue(saleData, field);
                  if (Objects.nonNull(value)) {
                    sum.addAndGet(value);
                  }
                });
                data.put(mw, sum.get());
              });
        });
      });
      resArrayList.add(data);
    });
    List<Header> headerList = new ArrayList<>();

    weekInfoList.stream().collect(Collectors.groupingBy(t -> t.getCurrentDate().getYear())).forEach((y, weekInfoListTmp) -> {
      weekInfoListTmp.stream().collect(Collectors.groupingBy(t -> t.getCurrentDay().substring(5, 7) + "月" + t.getWeekNumber() + "周"))
          .forEach((mw, weekInfoListTmpTmp) -> {
            headerList.add(new Header().setWidth(80).setFieldName(mw).setShowName(mw).setSortValue(weekInfoListTmpTmp.get(0).getCurrentDay()));
          });
    });
    headerList.sort(Comparator.comparing(Header::getSortValue));
    headerList.add(0, new Header().setWidth(80).setFieldName("saleConfigCode").setShowName("销售配置编码"));
    resArrayList.sort(Comparator.comparing(ApsGoodsForecastMainMakeQueryDataByIdRes::getSaleConfigCode));
    return new DynamicsPage<ApsGoodsForecastMainMakeQueryDataByIdRes>().setHeaderList(headerList).setDataList(resArrayList);
  }
}

