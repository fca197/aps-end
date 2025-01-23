package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsGoodsBomBuyPlanItemApi;
import com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlanItem.*;
import com.olivia.peanut.aps.api.impl.listener.ApsGoodsBomBuyPlanItemImportListener;
import com.olivia.peanut.aps.model.ApsGoodsBomBuyPlanItem;
import com.olivia.peanut.aps.service.ApsGoodsBomBuyPlanItemService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * BOM 购买清单(ApsGoodsBomBuyPlanItem)表服务实现类
 *
 * @author peanut
 * @since 2024-06-05 14:35:30
 */
@RestController
public class ApsGoodsBomBuyPlanItemApiImpl implements ApsGoodsBomBuyPlanItemApi {

  private @Autowired ApsGoodsBomBuyPlanItemService apsGoodsBomBuyPlanItemService;

  /****
   * insert
   *
   */
  public @Override ApsGoodsBomBuyPlanItemInsertRes insert(ApsGoodsBomBuyPlanItemInsertReq req) {
    this.apsGoodsBomBuyPlanItemService.save($.copy(req, ApsGoodsBomBuyPlanItem.class));
    return new ApsGoodsBomBuyPlanItemInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsGoodsBomBuyPlanItemDeleteByIdListRes deleteByIdList(ApsGoodsBomBuyPlanItemDeleteByIdListReq req) {
    apsGoodsBomBuyPlanItemService.removeByIds(req.getIdList());
    return new ApsGoodsBomBuyPlanItemDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsBomBuyPlanItemQueryListRes queryList(ApsGoodsBomBuyPlanItemQueryListReq req) {
    return apsGoodsBomBuyPlanItemService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsBomBuyPlanItemUpdateByIdRes updateById(ApsGoodsBomBuyPlanItemUpdateByIdReq req) {
    apsGoodsBomBuyPlanItemService.updateById($.copy(req, ApsGoodsBomBuyPlanItem.class));
    return new ApsGoodsBomBuyPlanItemUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes> queryPageList(ApsGoodsBomBuyPlanItemExportQueryPageListReq req) {
    return apsGoodsBomBuyPlanItemService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsBomBuyPlanItemExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes.class, listInfoRes, "BOM 购买清单");
  }

  public @Override ApsGoodsBomBuyPlanItemImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsGoodsBomBuyPlanItemImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsBomBuyPlanItemImportListener(), ApsGoodsBomBuyPlanItemImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsBomBuyPlanItem> readList = $.copyList(reqList, ApsGoodsBomBuyPlanItem.class);
    boolean bool = apsGoodsBomBuyPlanItemService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsBomBuyPlanItemImportRes().setCount(c);
  }

  public @Override ApsGoodsBomBuyPlanItemQueryByIdListRes queryByIdListRes(ApsGoodsBomBuyPlanItemQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoodsBomBuyPlanItem> q = new MPJLambdaWrapper<ApsGoodsBomBuyPlanItem>(ApsGoodsBomBuyPlanItem.class)
        .selectAll(ApsGoodsBomBuyPlanItem.class).in(ApsGoodsBomBuyPlanItem::getId, req.getIdList());
    List<ApsGoodsBomBuyPlanItem> list = this.apsGoodsBomBuyPlanItemService.list(q);
    List<ApsGoodsBomBuyPlanItemDto> dataList = $.copyList(list, ApsGoodsBomBuyPlanItemDto.class);
    this.apsGoodsBomBuyPlanItemService.setName(dataList);
    return new ApsGoodsBomBuyPlanItemQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public SendMail2supplierRes sendMail2supplier(SendMail2supplierReq req) {
    return this.apsGoodsBomBuyPlanItemService.sendMail2supplier(req);

  }
}
