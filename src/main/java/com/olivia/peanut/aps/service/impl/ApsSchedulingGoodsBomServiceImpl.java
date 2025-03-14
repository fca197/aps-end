package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBom.*;
import com.olivia.peanut.aps.mapper.ApsSchedulingGoodsBomMapper;
import com.olivia.peanut.aps.model.ApsSchedulingGoodsBom;
import com.olivia.peanut.aps.service.ApsSchedulingGoodsBomService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.LambdaQueryUtil;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 订单商品零件表(ApsSchedulingGoodsBom)表服务实现类
 *
 * @author makejava
 * @since 2024-12-07 20:20:17
 */
@Service("apsSchedulingGoodsBomService")
@Transactional
public class ApsSchedulingGoodsBomServiceImpl extends MPJBaseServiceImpl<ApsSchedulingGoodsBomMapper, ApsSchedulingGoodsBom> implements ApsSchedulingGoodsBomService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override ApsSchedulingGoodsBomQueryListRes queryList(ApsSchedulingGoodsBomQueryListReq req) {

    MPJLambdaWrapper<ApsSchedulingGoodsBom> q = getWrapper(req.getData());
    List<ApsSchedulingGoodsBom> list = this.list(q);

    List<ApsSchedulingGoodsBomDto> dataList = list.stream().map(t -> $.copy(t, ApsSchedulingGoodsBomDto.class)).collect(Collectors.toList());
    ((ApsSchedulingGoodsBomService) AopContext.currentProxy()).setName(dataList);
    return new ApsSchedulingGoodsBomQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsSchedulingGoodsBomExportQueryPageListInfoRes> queryPageList(ApsSchedulingGoodsBomExportQueryPageListReq req) {

    DynamicsPage<ApsSchedulingGoodsBom> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSchedulingGoodsBom> q = getWrapper(req.getData());
    List<ApsSchedulingGoodsBomExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSchedulingGoodsBom> list = this.page(page, q);
      IPage<ApsSchedulingGoodsBomExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSchedulingGoodsBomExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSchedulingGoodsBomExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作 

    List<ApsSchedulingGoodsBomExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSchedulingGoodsBomExportQueryPageListInfoRes.class);
    ((ApsSchedulingGoodsBomService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsSchedulingGoodsBomDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  @SuppressWarnings("unchecked")
  private MPJLambdaWrapper<ApsSchedulingGoodsBom> getWrapper(ApsSchedulingGoodsBomDto obj) {
    MPJLambdaWrapper<ApsSchedulingGoodsBom> q = new MPJLambdaWrapper<>();

    LambdaQueryUtil.lambdaQueryWrapper(q, obj, ApsSchedulingGoodsBom.class
        // 查询条件
        , ApsSchedulingGoodsBom::getSchedulingId //
        , ApsSchedulingGoodsBom::getOrderId //
        , ApsSchedulingGoodsBom::getGoodsId //
        , ApsSchedulingGoodsBom::getGoodsStatusId //
        , ApsSchedulingGoodsBom::getBomId //
        , ApsSchedulingGoodsBom::getBomCode //
        , ApsSchedulingGoodsBom::getBomName //
        , ApsSchedulingGoodsBom::getBomUsage //
        , ApsSchedulingGoodsBom::getBomUnit //
        , ApsSchedulingGoodsBom::getBomCostPrice //
        , ApsSchedulingGoodsBom::getBomCostPriceUnit //
        , ApsSchedulingGoodsBom::getBomUseWorkStation //
        , ApsSchedulingGoodsBom::getBomUseDate //
        , ApsSchedulingGoodsBom::getFactoryId //
        , ApsSchedulingGoodsBom::getGoodsBomId //
    );
    q.orderByDesc(ApsSchedulingGoodsBom::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingGoodsBom> page) {

    tableHeaderService.listByBizKey(page, "ApsSchedulingGoodsBomService#queryPageList");

  }


}

