package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsProduceProcessItemApi;
import com.olivia.peanut.aps.api.entity.apsProduceProcessItem.*;
import com.olivia.peanut.aps.api.impl.listener.ApsProduceProcessItemImportListener;
import com.olivia.peanut.aps.model.ApsProduceProcessItem;
import com.olivia.peanut.aps.service.ApsProduceProcessItemService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * aps 生产机器(ApsProduceProcessItem)表服务实现类
 *
 * @author makejava
 * @since 2024-10-24 17:00:20
 */
@RestController
public class ApsProduceProcessItemApiImpl implements ApsProduceProcessItemApi {

  private @Autowired ApsProduceProcessItemService apsProduceProcessItemService;

  /****
   * insert
   *
   */
  public @Override ApsProduceProcessItemInsertRes insert(ApsProduceProcessItemInsertReq req) {
    this.apsProduceProcessItemService.save($.copy(req, ApsProduceProcessItem.class));
    return new ApsProduceProcessItemInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsProduceProcessItemDeleteByIdListRes deleteByIdList(ApsProduceProcessItemDeleteByIdListReq req) {
    apsProduceProcessItemService.removeByIds(req.getIdList());
    return new ApsProduceProcessItemDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsProduceProcessItemQueryListRes queryList(ApsProduceProcessItemQueryListReq req) {
    return apsProduceProcessItemService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsProduceProcessItemUpdateByIdRes updateById(ApsProduceProcessItemUpdateByIdReq req) {
    apsProduceProcessItemService.updateById($.copy(req, ApsProduceProcessItem.class));
    return new ApsProduceProcessItemUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsProduceProcessItemExportQueryPageListInfoRes> queryPageList(ApsProduceProcessItemExportQueryPageListReq req) {
    return apsProduceProcessItemService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsProduceProcessItemExportQueryPageListReq req) {
    DynamicsPage<ApsProduceProcessItemExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsProduceProcessItemExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsProduceProcessItemExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsProduceProcessItemExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsProduceProcessItemExportQueryPageListInfoRes.class, listInfoRes, "aps 生产机器");
  }

  public @Override ApsProduceProcessItemImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsProduceProcessItemImportReq> reqList = PoiExcelUtil.readData(file, new ApsProduceProcessItemImportListener(), ApsProduceProcessItemImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsProduceProcessItem> readList = $.copyList(reqList, ApsProduceProcessItem.class);
    boolean bool = apsProduceProcessItemService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsProduceProcessItemImportRes().setCount(c);
  }

  public @Override ApsProduceProcessItemQueryByIdListRes queryByIdListRes(ApsProduceProcessItemQueryByIdListReq req) {
    MPJLambdaWrapper<ApsProduceProcessItem> q = new MPJLambdaWrapper<ApsProduceProcessItem>(ApsProduceProcessItem.class)
        .selectAll(ApsProduceProcessItem.class).in(ApsProduceProcessItem::getId, req.getIdList());
    List<ApsProduceProcessItem> list = this.apsProduceProcessItemService.list(q);
    List<ApsProduceProcessItemDto> dataList = $.copyList(list, ApsProduceProcessItemDto.class);
    this.apsProduceProcessItemService.setName(dataList);
    return new ApsProduceProcessItemQueryByIdListRes().setDataList(dataList);
  }
}
