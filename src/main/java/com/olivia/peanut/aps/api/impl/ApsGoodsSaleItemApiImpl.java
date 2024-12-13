package com.olivia.peanut.aps.api.impl;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsGoodsSaleItemApi;
import com.olivia.peanut.aps.api.entity.apsGoodsSaleItem.*;
import com.olivia.peanut.aps.api.impl.listener.ApsGoodsSaleItemImportListener;
import com.olivia.peanut.aps.model.ApsGoodsSaleItem;
import com.olivia.peanut.aps.service.ApsGoodsSaleItemService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsGoodsSaleItem)表服务实现类
 *
 * @author peanut
 * @since 2024-03-30 10:38:35
 */
@RestController
public class ApsGoodsSaleItemApiImpl implements ApsGoodsSaleItemApi {

  private @Autowired ApsGoodsSaleItemService apsGoodsSaleItemService;

  /****
   * insert
   *
   */
  public @Override ApsGoodsSaleItemInsertRes insert(ApsGoodsSaleItemInsertReq req) {
    this.apsGoodsSaleItemService.save($.copy(req, ApsGoodsSaleItem.class));
    return new ApsGoodsSaleItemInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsGoodsSaleItemDeleteByIdListRes deleteByIdList(ApsGoodsSaleItemDeleteByIdListReq req) {
    apsGoodsSaleItemService.removeByIds(req.getIdList());
    return new ApsGoodsSaleItemDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsSaleItemQueryListRes queryList(ApsGoodsSaleItemQueryListReq req) {
    return apsGoodsSaleItemService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsSaleItemUpdateByIdRes updateById(ApsGoodsSaleItemUpdateByIdReq req) {
    apsGoodsSaleItemService.updateById($.copy(req, ApsGoodsSaleItem.class));
    return new ApsGoodsSaleItemUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsSaleItemExportQueryPageListInfoRes> queryPageList(ApsGoodsSaleItemExportQueryPageListReq req) {
    return apsGoodsSaleItemService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsSaleItemExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsSaleItemExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsSaleItemExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsSaleItemExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsSaleItemExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsSaleItemExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsGoodsSaleItemImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsGoodsSaleItemImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsSaleItemImportListener(), ApsGoodsSaleItemImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsSaleItem> readList = $.copyList(reqList, ApsGoodsSaleItem.class);
    boolean bool = apsGoodsSaleItemService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsSaleItemImportRes().setCount(c);
  }

  public @Override ApsGoodsSaleItemQueryByIdListRes queryByIdListRes(ApsGoodsSaleItemQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoodsSaleItem> q = new MPJLambdaWrapper<ApsGoodsSaleItem>(ApsGoodsSaleItem.class).selectAll(ApsGoodsSaleItem.class)
        .in(ApsGoodsSaleItem::getId, req.getIdList());
    List<ApsGoodsSaleItem> list = this.apsGoodsSaleItemService.list(q);
    List<ApsGoodsSaleItemDto> dataList = $.copyList(list, ApsGoodsSaleItemDto.class);
    return new ApsGoodsSaleItemQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public UpdateForecastRes updateForecast(UpdateForecastReq req) {
    List<ApsGoodsSaleItem> saleItemList = this.apsGoodsSaleItemService.list(
        new LambdaQueryWrapper<ApsGoodsSaleItem>().eq(ApsGoodsSaleItem::getGoodsId, req.getGoodsId()).eq(ApsGoodsSaleItem::getSaleConfigId, req.getSaleConfigId()));
    $.assertTrueCanIgnoreException(CollUtil.isNotEmpty(saleItemList), "没有该记录");
    $.assertTrueCanIgnoreException(saleItemList.size() == 1, "没有该记录或有多条记录");
    this.apsGoodsSaleItemService.update(
        new LambdaUpdateWrapper<ApsGoodsSaleItem>().eq(BaseEntity::getId, saleItemList.getFirst().getId())
            .set(ApsGoodsSaleItem::getUseForecast, req.getUseForecast()));
    return new UpdateForecastRes();
  }

  @Override
  public UpdateSaleConfigRes updateSaleConfig(UpdateSaleConfigReq req) {
    this.apsGoodsSaleItemService.remove(
        new LambdaQueryWrapper<ApsGoodsSaleItem>().eq(ApsGoodsSaleItem::getGoodsId, req.getGoodsId()).eq(ApsGoodsSaleItem::getSaleConfigId, req.getSaleConfigId()));
    if (Boolean.TRUE.equals(req.getIsAdd())) {
      ApsGoodsSaleItem saleItem = new ApsGoodsSaleItem();
      saleItem.setId(IdWorker.getId());
      saleItem.setGoodsId(req.getGoodsId()).setUseForecast(0).setSaleConfigId(req.getSaleConfigId());
      this.apsGoodsSaleItemService.save(saleItem);
      return new UpdateSaleConfigRes().setId(saleItem.getId());
    }
    return new UpdateSaleConfigRes();
  }
}
