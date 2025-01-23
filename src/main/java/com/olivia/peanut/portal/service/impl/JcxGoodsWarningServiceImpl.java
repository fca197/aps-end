package com.olivia.peanut.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.portal.api.entity.jcxGoodsWarning.*;
import com.olivia.peanut.portal.mapper.JcxGoodsWarningMapper;
import com.olivia.peanut.portal.model.JcxGoodsWarning;
import com.olivia.peanut.portal.service.JcxGoodsWarningService;
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
 * (JcxGoodsWarning)表服务实现类
 *
 * @author peanut
 * @since 2024-03-24 14:10:55
 */
@Service("jcxGoodsWarningService")
@Transactional
public class JcxGoodsWarningServiceImpl extends MPJBaseServiceImpl<JcxGoodsWarningMapper, JcxGoodsWarning> implements JcxGoodsWarningService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  public @Override JcxGoodsWarningQueryListRes queryList(JcxGoodsWarningQueryListReq req) {

    MPJLambdaWrapper<JcxGoodsWarning> q = getWrapper(req.getData());
    List<JcxGoodsWarning> list = this.list(q);

    List<JcxGoodsWarningDto> dataList = list.stream().map(t -> $.copy(t, JcxGoodsWarningDto.class)).collect(Collectors.toList());

    return new JcxGoodsWarningQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<JcxGoodsWarningExportQueryPageListInfoRes> queryPageList(JcxGoodsWarningExportQueryPageListReq req) {

    DynamicsPage<JcxGoodsWarning> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<JcxGoodsWarning> q = getWrapper(req.getData());
    List<JcxGoodsWarningExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<JcxGoodsWarning> list = this.page(page, q);
      IPage<JcxGoodsWarningExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, JcxGoodsWarningExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), JcxGoodsWarningExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<JcxGoodsWarningExportQueryPageListInfoRes> listInfoRes = $.copyList(records, JcxGoodsWarningExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<JcxGoodsWarning> getWrapper(JcxGoodsWarningDto obj) {
    MPJLambdaWrapper<JcxGoodsWarning> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), JcxGoodsWarning::getId, obj.getId())
          .eq(StringUtils.isNoneBlank(obj.getReportNo()), JcxGoodsWarning::getReportNo, obj.getReportNo())
          .eq(Objects.nonNull(obj.getGoodsId()), JcxGoodsWarning::getGoodsId, obj.getGoodsId())
          .eq(StringUtils.isNoneBlank(obj.getGoodsName()), JcxGoodsWarning::getGoodsName, obj.getGoodsName())
          .eq(Objects.nonNull(obj.getCostPrice()), JcxGoodsWarning::getCostPrice, obj.getCostPrice())
          .eq(Objects.nonNull(obj.getSalesPrice()), JcxGoodsWarning::getSalesPrice, obj.getSalesPrice())
          .eq(Objects.nonNull(obj.getWarningCount()), JcxGoodsWarning::getWarningCount, obj.getWarningCount())
          .eq(Objects.nonNull(obj.getGoodsGrossProfit()), JcxGoodsWarning::getGoodsGrossProfit, obj.getGoodsGrossProfit())
          .eq(Objects.nonNull(obj.getGoodsNetProfit()), JcxGoodsWarning::getGoodsNetProfit, obj.getGoodsNetProfit())
          .eq(Objects.nonNull(obj.getGoodsInventoryCount()), JcxGoodsWarning::getGoodsInventoryCount, obj.getGoodsInventoryCount())
          .eq(Objects.nonNull(obj.getIsInventory()), JcxGoodsWarning::getIsInventory, obj.getIsInventory())
          .eq(Objects.nonNull(obj.getIsDone()), JcxGoodsWarning::getIsDone, obj.getIsDone())
          .eq(Objects.nonNull(obj.getCreateTime()), JcxGoodsWarning::getCreateTime, obj.getCreateTime())
          .eq(Objects.nonNull(obj.getUpdateTime()), JcxGoodsWarning::getUpdateTime, obj.getUpdateTime())
      ;
    }

    return q.orderByDesc(JcxGoodsWarning::getId);

  }

  private void setQueryListHeader(DynamicsPage<JcxGoodsWarning> page) {
    page
        .addHeader("reportNo", "盘点编号", 200)

        .addHeader("goodsName", "商品名称", 200)
        .addHeader("costPrice", "成本价(分)")
        .addHeader("salesPrice", "售卖价(分)")
//        .addHeader("goodsUnit", "规格")
        //
        .addHeader("goodsInventoryCount", "库存(分)")
        //
        .addHeader("goodsGrossProfit", "毛利(分)")
        .addHeader("goodsNetProfit", "净利(分)")
    ;
  }


}

