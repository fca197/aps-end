package com.olivia.peanut.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.portal.api.entity.goods.*;
import com.olivia.peanut.portal.mapper.JcxGoodsMapper;
import com.olivia.peanut.portal.model.JcxGoods;
import com.olivia.peanut.portal.service.JcxGoodsService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品信息(Goods)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:44:06
 */
@Service("goodsService")
@Transactional
public class JcxGoodsServiceImpl extends MPJBaseServiceImpl<JcxGoodsMapper, JcxGoods> implements JcxGoodsService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  public @Override GoodsQueryListRes queryList(GoodsQueryListReq req) {

    MPJLambdaWrapper<JcxGoods> q = getWrapper(req.getData());
    List<JcxGoods> list = this.list(q);

    List<GoodsQueryListRes.Info> dataList = list.stream().map(t -> $.copy(t, GoodsQueryListRes.Info.class)).collect(Collectors.toList());

    return new GoodsQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<JcxGoodsExportQueryPageListInfoRes> queryPageList(GoodsExportQueryPageListReq req) {

    DynamicsPage<JcxGoods> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<JcxGoods> q = getWrapper(req.getData());
    List<JcxGoodsExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<JcxGoods> list = this.page(page, q);
      IPage<JcxGoodsExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, JcxGoodsExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), JcxGoodsExportQueryPageListInfoRes.class);
    }
    // 类型转换，  更换枚举 等操作
    List<JcxGoodsExportQueryPageListInfoRes> listInfoRes = $.copyList(records, JcxGoodsExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<JcxGoods> getWrapper(JcxGoodsDto obj) {
    MPJLambdaWrapper<JcxGoods> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getId()), JcxGoods::getId, obj.getId()).eq(Objects.nonNull(obj.getBrandId()), JcxGoods::getBrandId, obj.getBrandId())
          .like(StringUtils.isNoneBlank(obj.getGoodsName()), JcxGoods::getGoodsName, obj.getGoodsName())
          .eq(StringUtils.isNoneBlank(obj.getGoodsImg()), JcxGoods::getGoodsImg, obj.getGoodsImg())
          .eq(StringUtils.isNoneBlank(obj.getGoodsBarCode()), JcxGoods::getGoodsBarCode, obj.getGoodsBarCode())
          .eq(StringUtils.isNoneBlank(obj.getGoodsQrCode()), JcxGoods::getGoodsQrCode, obj.getGoodsQrCode())
          .eq(StringUtils.isNoneBlank(obj.getGoodsUnit()), JcxGoods::getGoodsUnit, obj.getGoodsUnit())
          .eq(Objects.nonNull(obj.getGoodsType()), JcxGoods::getGoodsType, obj.getGoodsType()).eq(Objects.nonNull(obj.getTenantId()), JcxGoods::getTenantId, obj.getTenantId())
          .eq(Objects.nonNull(obj.getIsInventory()), JcxGoods::getIsInventory, obj.getIsInventory())
          .eq(Objects.nonNull(obj.getVersionNum()), JcxGoods::getVersionNum, obj.getVersionNum());
    }
    return q.orderByDesc(JcxGoods::getId);

  }

  private void setQueryListHeader(DynamicsPage<JcxGoods> page) {
    page.addHeader("id", "id").addHeader("goodsName", "商品名称")
        //
        .addHeader("goodsImg", "商品图片").addHeader("goodsBarCode", "条形码")
//        .addHeader("goodsQrCode", "二维码")
        .addHeader("costPrice", "成本价(分)").addHeader("salesPrice", "售卖价(分)").addHeader("goodsUnit", "单位")
        //
        .addHeader("goodsInventoryCount", "库存(分)")
        //
        .addHeader("goodsGrossProfit", "毛利(分)").addHeader("goodsNetProfit", "净利(分)")
//        .addHeader("isInventory", "盘点")
    ;
  }
}

