package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsSaleConfig.ApsSaleConfigExportQueryPageListInfoRes;
import com.olivia.peanut.aps.api.entity.apsSaleConfig.ApsSaleConfigExportQueryPageListReq;
import com.olivia.peanut.aps.api.entity.apsSchedulingConstraints.*;
import com.olivia.peanut.aps.mapper.ApsSchedulingConstraintsMapper;
import com.olivia.peanut.aps.model.ApsSchedulingConstraints;
import com.olivia.peanut.aps.service.ApsSaleConfigService;
import com.olivia.peanut.aps.service.ApsSchedulingConstraintsService;
import com.olivia.peanut.aps.utils.constrained.model.sub.OperatorEnum;
import com.olivia.peanut.aps.utils.constrained.model.sub.constrained.FieldConfig;
import com.olivia.peanut.aps.utils.constrained.model.sub.constrained.Operator;
import com.olivia.peanut.aps.utils.constrained.model.sub.constrained.ValueItem;
import com.olivia.peanut.aps.utils.constrained.model.sub.constrained.ValueType;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (ApsSchedulingConstraints)表服务实现类
 *
 * @author peanut
 * @since 2024-04-13 21:32:13
 */
@Service("apsSchedulingConstraintsService")
@Transactional
public class ApsSchedulingConstraintsServiceImpl extends MPJBaseServiceImpl<ApsSchedulingConstraintsMapper, ApsSchedulingConstraints> implements ApsSchedulingConstraintsService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  ApsSaleConfigService apsSaleConfigService;

  // 以下为私有对象封装
  private static List<Operator> operatorList(OperatorEnum... ignoreEnum) {
    Set<String> ignoreSet = new HashSet<>();
    if (ignoreEnum != null && ignoreEnum.length > 0) {
      ignoreSet.addAll(Arrays.stream(ignoreEnum).map(OperatorEnum::getValue).collect(Collectors.toSet()));
    }
    return Arrays.stream(OperatorEnum.values()).filter(t -> !ignoreSet.contains(t.getValue())).map(t -> new Operator().setValue(t.getValue()).setName(t.getName())).toList();
  }

  public @Override ApsSchedulingConstraintsQueryListRes queryList(ApsSchedulingConstraintsQueryListReq req) {

    MPJLambdaWrapper<ApsSchedulingConstraints> q = getWrapper(req.getData());
    List<ApsSchedulingConstraints> list = this.list(q);

    List<ApsSchedulingConstraintsDto> dataList = list.stream().map(t -> $.copy(t, ApsSchedulingConstraintsDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsSchedulingConstraintsServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsSchedulingConstraintsQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsSchedulingConstraintsExportQueryPageListInfoRes> queryPageList(ApsSchedulingConstraintsExportQueryPageListReq req) {

    DynamicsPage<ApsSchedulingConstraints> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSchedulingConstraints> q = getWrapper(req.getData());
    List<ApsSchedulingConstraintsExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSchedulingConstraints> list = this.page(page, q);
      IPage<ApsSchedulingConstraintsExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSchedulingConstraintsExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSchedulingConstraintsExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsSchedulingConstraintsExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSchedulingConstraintsExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsSchedulingConstraintsServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  @Override
  public ApsSchedulingConstraintsGetUseFieldRes getUseField() {
    ApsSchedulingConstraintsGetUseFieldRes res = new ApsSchedulingConstraintsGetUseFieldRes();
    List<Operator> operator = operatorList(OperatorEnum.LIKE);

    FieldConfig createTimeField = new FieldConfig().setShowName("订单-创建时间").setFieldName("createTime").setValueType(ValueType.DATE).setOperator(operator);
    FieldConfig deliveryDateField = new FieldConfig().setShowName("订单-交付时间").setFieldName("deliveryDate").setValueType(ValueType.DATE).setOperator(operator);
    FieldConfig finishPayedDatetime = new FieldConfig().setShowName("订单-尾款支付时间").setFieldName("finishPayedDatetime").setValueType(ValueType.DATE).setOperator(operator);
    FieldConfig updateTimeField = new FieldConfig().setShowName("订单-修改时间").setFieldName("updateTime").setValueType(ValueType.DATE).setOperator(operator);
    FieldConfig orderTotalPrice = new FieldConfig().setShowName("订单-总价").setFieldName("orderTotalPrice").setValueType(ValueType.TEXT).setOperator(operator);
    List<FieldConfig> values = List.of(createTimeField, deliveryDateField, finishPayedDatetime, updateTimeField, orderTotalPrice);
    List<ApsSaleConfigExportQueryPageListInfoRes> saleConfigList = apsSaleConfigService.queryPageList(new ApsSaleConfigExportQueryPageListReq().setQueryPage(false))
        .getDataList();

    List<FieldConfig> retList = new ArrayList<>(values);
    saleConfigList.forEach(sale -> {
      FieldConfig fieldConfig = new FieldConfig().setShowName("销售-" + sale.getSaleName()).setFieldName("sale_" + sale.getId()).setValueType(ValueType.SELECT)
          .setOperator(Operator.of(OperatorEnum.EQ, OperatorEnum.IN))
          .setValueItemList(sale.getChildren().stream().map(c -> new ValueItem().setValue(c.getId()).setValueName(c.getSaleName())).toList());
      retList.add(fieldConfig);
    });
    res.setValues(retList);
    return res;
  }

  @SetUserName
  public @Override void setName(List<? extends ApsSchedulingConstraintsDto> apsSchedulingConstraintsDtoList) {

    if (CollUtil.isEmpty(apsSchedulingConstraintsDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsSchedulingConstraints> getWrapper(ApsSchedulingConstraintsDto obj) {
    MPJLambdaWrapper<ApsSchedulingConstraints> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(StringUtils.isNoneBlank(obj.getConstraintsNo()), ApsSchedulingConstraints::getConstraintsNo, obj.getConstraintsNo())
          .eq(StringUtils.isNoneBlank(obj.getConstraintsName()), ApsSchedulingConstraints::getConstraintsName, obj.getConstraintsName())
          .eq(StringUtils.isNoneBlank(obj.getConstraintsContext()), ApsSchedulingConstraints::getConstraintsContext, obj.getConstraintsContext())
          .eq(StringUtils.isNoneBlank(obj.getConstraintsRemark()), ApsSchedulingConstraints::getConstraintsRemark, obj.getConstraintsRemark())

      ;
    }
    q.orderByDesc(ApsSchedulingConstraints::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingConstraints> page) {

    ServiceComment.header(page, "ApsSchedulingConstraintsService#queryPageList");

  }


}

