package com.olivia.peanut.base.service.impl;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.baseH3Code.*;
import com.olivia.peanut.base.mapper.BaseH3CodeMapper;
import com.olivia.peanut.base.model.BaseH3Code;
import com.olivia.peanut.base.service.BaseH3CodeService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.olivia.peanut.util.H3Utils.UBER_H3_CORE;


/**
 * H3对应的值(BaseH3Code)表服务实现类
 *
 * @author makejava
 * @since 2024-11-19 16:09:19
 */
@Service("baseH3CodeService")
@Transactional
public class BaseH3CodeServiceImpl extends MPJBaseServiceImpl<BaseH3CodeMapper, BaseH3Code> implements BaseH3CodeService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override BaseH3CodeQueryListRes queryList(BaseH3CodeQueryListReq req) {

    MPJLambdaWrapper<BaseH3Code> q = getWrapper(req.getData());
    List<BaseH3Code> list = this.list(q);

    List<BaseH3CodeDto> dataList = list.stream().map(t -> $.copy(t, BaseH3CodeDto.class)).collect(Collectors.toList());
    ((BaseH3CodeService) AopContext.currentProxy()).setName(dataList);
    return new BaseH3CodeQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<BaseH3CodeExportQueryPageListInfoRes> queryPageList(BaseH3CodeExportQueryPageListReq req) {

    DynamicsPage<BaseH3Code> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<BaseH3Code> q = getWrapper(req.getData());
    List<BaseH3CodeExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<BaseH3Code> list = this.page(page, q);
      IPage<BaseH3CodeExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BaseH3CodeExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BaseH3CodeExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作 

    List<BaseH3CodeExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BaseH3CodeExportQueryPageListInfoRes.class);
    ((BaseH3CodeService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }


  @Override
  public BaseH3Code saveOrGet(BigDecimal lat, BigDecimal lng) {
    BaseH3Code tmp = new BaseH3Code().setLat(lat).setLng(lng);
    IntStream.range(0, 16).forEach(t -> {
      long lngToCell = UBER_H3_CORE.latLngToCell(lat.doubleValue(), lng.doubleValue(), t);
      ReflectUtil.setFieldValue(tmp, "value", lngToCell);
    });
    RunUtils.run("saveOrUpdate", () -> {
      BaseH3Code one = this.getOne(new LambdaQueryWrapper<BaseH3Code>().eq(BaseH3Code::getLat, lat).eq(BaseH3Code::getLng, lng));
      if (Objects.isNull(one)) {
        this.save(tmp);
      }
    });
    return tmp;
  }
  // 以下为私有对象封装

  public @Override void setName(List<? extends BaseH3CodeDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<BaseH3Code> getWrapper(BaseH3CodeDto obj) {
    MPJLambdaWrapper<BaseH3Code> q = new MPJLambdaWrapper<>();


    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getLat()), BaseH3Code::getLat, obj.getLat())
          .eq(Objects.nonNull(obj.getLng()), BaseH3Code::getLng, obj.getLng())
          .eq(Objects.nonNull(obj.getValue0()), BaseH3Code::getValue0, obj.getValue0())
          .eq(Objects.nonNull(obj.getValue1()), BaseH3Code::getValue1, obj.getValue1())
          .eq(Objects.nonNull(obj.getValue2()), BaseH3Code::getValue2, obj.getValue2())
          .eq(Objects.nonNull(obj.getValue3()), BaseH3Code::getValue3, obj.getValue3())
          .eq(Objects.nonNull(obj.getValue4()), BaseH3Code::getValue4, obj.getValue4())
          .eq(Objects.nonNull(obj.getValue5()), BaseH3Code::getValue5, obj.getValue5())
          .eq(Objects.nonNull(obj.getValue6()), BaseH3Code::getValue6, obj.getValue6())
          .eq(Objects.nonNull(obj.getValue7()), BaseH3Code::getValue7, obj.getValue7())
          .eq(Objects.nonNull(obj.getValue8()), BaseH3Code::getValue8, obj.getValue8())
          .eq(Objects.nonNull(obj.getValue9()), BaseH3Code::getValue9, obj.getValue9())
          .eq(Objects.nonNull(obj.getValue10()), BaseH3Code::getValue10, obj.getValue10())
          .eq(Objects.nonNull(obj.getValue11()), BaseH3Code::getValue11, obj.getValue11())
          .eq(Objects.nonNull(obj.getValue12()), BaseH3Code::getValue12, obj.getValue12())
          .eq(Objects.nonNull(obj.getValue13()), BaseH3Code::getValue13, obj.getValue13())
          .eq(Objects.nonNull(obj.getValue14()), BaseH3Code::getValue14, obj.getValue14())
          .eq(Objects.nonNull(obj.getValue15()), BaseH3Code::getValue15, obj.getValue15())

      ;
    }
    q.orderByDesc(BaseH3Code::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BaseH3Code> page) {

    tableHeaderService.listByBizKey(page, "BaseH3CodeService#queryPageList");

  }


}

