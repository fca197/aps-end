package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsLogisticsPathItemApi;
import com.olivia.peanut.aps.api.entity.apsLogisticsPathItem.*;
import com.olivia.peanut.aps.api.impl.listener.ApsLogisticsPathItemImportListener;
import com.olivia.peanut.aps.model.ApsLogisticsPathItem;
import com.olivia.peanut.aps.service.ApsLogisticsPathItemService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 物流路详情径表(ApsLogisticsPathItem)表服务实现类
 *
 * @author peanut
 * @since 2024-07-18 13:27:39
 */
@RestController
public class ApsLogisticsPathItemApiImpl implements ApsLogisticsPathItemApi {

  private @Autowired ApsLogisticsPathItemService apsLogisticsPathItemService;

  /****
   * insert
   *
   */
  public @Override ApsLogisticsPathItemInsertRes insert(ApsLogisticsPathItemInsertReq req) {
    this.apsLogisticsPathItemService.save($.copy(req, ApsLogisticsPathItem.class));
    return new ApsLogisticsPathItemInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsLogisticsPathItemDeleteByIdListRes deleteByIdList(ApsLogisticsPathItemDeleteByIdListReq req) {
    apsLogisticsPathItemService.removeByIds(req.getIdList());
    return new ApsLogisticsPathItemDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsLogisticsPathItemQueryListRes queryList(ApsLogisticsPathItemQueryListReq req) {
    return apsLogisticsPathItemService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsLogisticsPathItemUpdateByIdRes updateById(ApsLogisticsPathItemUpdateByIdReq req) {
    apsLogisticsPathItemService.updateById($.copy(req, ApsLogisticsPathItem.class));
    return new ApsLogisticsPathItemUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsLogisticsPathItemExportQueryPageListInfoRes> queryPageList(ApsLogisticsPathItemExportQueryPageListReq req) {
    return apsLogisticsPathItemService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsLogisticsPathItemExportQueryPageListReq req) {
    DynamicsPage<ApsLogisticsPathItemExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsLogisticsPathItemExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsLogisticsPathItemExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsLogisticsPathItemExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsLogisticsPathItemExportQueryPageListInfoRes.class, listInfoRes, "物流路详情径表");
  }

  public @Override ApsLogisticsPathItemImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsLogisticsPathItemImportReq> reqList = PoiExcelUtil.readData(file, new ApsLogisticsPathItemImportListener(), ApsLogisticsPathItemImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsLogisticsPathItem> readList = $.copyList(reqList, ApsLogisticsPathItem.class);
    boolean bool = apsLogisticsPathItemService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsLogisticsPathItemImportRes().setCount(c);
  }

  public @Override ApsLogisticsPathItemQueryByIdListRes queryByIdListRes(ApsLogisticsPathItemQueryByIdListReq req) {
    MPJLambdaWrapper<ApsLogisticsPathItem> q = new MPJLambdaWrapper<ApsLogisticsPathItem>(ApsLogisticsPathItem.class)
        .selectAll(ApsLogisticsPathItem.class).in(ApsLogisticsPathItem::getId, req.getIdList());
    List<ApsLogisticsPathItem> list = this.apsLogisticsPathItemService.list(q);
    List<ApsLogisticsPathItemDto> dataList = $.copyList(list, ApsLogisticsPathItemDto.class);
    this.apsLogisticsPathItemService.setName(dataList);
    return new ApsLogisticsPathItemQueryByIdListRes().setDataList(dataList);
  }
}
