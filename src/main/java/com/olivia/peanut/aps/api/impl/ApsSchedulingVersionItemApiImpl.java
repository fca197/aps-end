package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsSchedulingVersionItemApi;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersionItem.*;
import com.olivia.peanut.aps.api.impl.listener.ApsSchedulingVersionItemImportListener;
import com.olivia.peanut.aps.model.ApsSchedulingVersionItem;
import com.olivia.peanut.aps.service.ApsSchedulingVersionItemService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsSchedulingVersionItem)表服务实现类
 *
 * @author peanut
 * @since 2024-04-16 09:24:05
 */
@RestController
public class ApsSchedulingVersionItemApiImpl implements ApsSchedulingVersionItemApi {

  private @Autowired ApsSchedulingVersionItemService apsSchedulingVersionItemService;

  /****
   * insert
   *
   */
  public @Override ApsSchedulingVersionItemInsertRes insert(ApsSchedulingVersionItemInsertReq req) {
    this.apsSchedulingVersionItemService.save($.copy(req, ApsSchedulingVersionItem.class));
    return new ApsSchedulingVersionItemInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSchedulingVersionItemDeleteByIdListRes deleteByIdList(ApsSchedulingVersionItemDeleteByIdListReq req) {
    apsSchedulingVersionItemService.removeByIds(req.getIdList());
    return new ApsSchedulingVersionItemDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSchedulingVersionItemQueryListRes queryList(ApsSchedulingVersionItemQueryListReq req) {
    return apsSchedulingVersionItemService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSchedulingVersionItemUpdateByIdRes updateById(ApsSchedulingVersionItemUpdateByIdReq req) {
    apsSchedulingVersionItemService.updateById($.copy(req, ApsSchedulingVersionItem.class));
    return new ApsSchedulingVersionItemUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsSchedulingVersionItemExportQueryPageListInfoRes> queryPageList(ApsSchedulingVersionItemExportQueryPageListReq req) {
    return apsSchedulingVersionItemService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSchedulingVersionItemExportQueryPageListReq req) {
    DynamicsPage<ApsSchedulingVersionItemExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSchedulingVersionItemExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingVersionItemExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSchedulingVersionItemExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSchedulingVersionItemExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsSchedulingVersionItemImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSchedulingVersionItemImportReq> reqList = PoiExcelUtil.readData(file, new ApsSchedulingVersionItemImportListener(), ApsSchedulingVersionItemImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingVersionItem> readList = $.copyList(reqList, ApsSchedulingVersionItem.class);
    boolean bool = apsSchedulingVersionItemService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSchedulingVersionItemImportRes().setCount(c);
  }

  public @Override ApsSchedulingVersionItemQueryByIdListRes queryByIdListRes(ApsSchedulingVersionItemQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSchedulingVersionItem> q = new MPJLambdaWrapper<ApsSchedulingVersionItem>(ApsSchedulingVersionItem.class)
        .selectAll(ApsSchedulingVersionItem.class).in(ApsSchedulingVersionItem::getId, req.getIdList());
    List<ApsSchedulingVersionItem> list = this.apsSchedulingVersionItemService.list(q);
    List<ApsSchedulingVersionItemDto> dataList = $.copyList(list, ApsSchedulingVersionItemDto.class);
    this.apsSchedulingVersionItemService.setName(dataList);
    return new ApsSchedulingVersionItemQueryByIdListRes().setDataList(dataList);
  }
}
