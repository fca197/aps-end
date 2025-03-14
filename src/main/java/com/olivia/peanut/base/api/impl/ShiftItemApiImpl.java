package com.olivia.peanut.base.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.api.ShiftItemApi;
import com.olivia.peanut.base.api.entity.shiftItem.*;
import com.olivia.peanut.base.model.ShiftItem;
import com.olivia.peanut.base.service.ShiftItemService;
import com.olivia.peanut.portal.api.impl.listener.ShiftItemImportListener;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ShiftItem)表服务实现类
 *
 * @author peanut
 * @since 2024-04-04 12:10:16
 */
@RestController
public class ShiftItemApiImpl implements ShiftItemApi {

  private @Autowired ShiftItemService shiftItemService;

  /****
   * insert
   *
   */
  public @Override ShiftItemInsertRes insert(ShiftItemInsertReq req) {
    this.shiftItemService.save($.copy(req, ShiftItem.class));
    return new ShiftItemInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ShiftItemDeleteByIdListRes deleteByIdList(ShiftItemDeleteByIdListReq req) {
    shiftItemService.removeByIds(req.getIdList());
    return new ShiftItemDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ShiftItemQueryListRes queryList(ShiftItemQueryListReq req) {
    return shiftItemService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ShiftItemUpdateByIdRes updateById(ShiftItemUpdateByIdReq req) {
    shiftItemService.updateById($.copy(req, ShiftItem.class));
    return new ShiftItemUpdateByIdRes();

  }

  public @Override DynamicsPage<ShiftItemExportQueryPageListInfoRes> queryPageList(ShiftItemExportQueryPageListReq req) {
    return shiftItemService.queryPageList(req);
  }

  public @Override void queryPageListExport(ShiftItemExportQueryPageListReq req) {
    DynamicsPage<ShiftItemExportQueryPageListInfoRes> page = queryPageList(req);
    List<ShiftItemExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ShiftItemExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ShiftItemExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ShiftItemExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ShiftItemImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ShiftItemImportReq> reqList = PoiExcelUtil.readData(file, new ShiftItemImportListener(), ShiftItemImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ShiftItem> readList = $.copyList(reqList, ShiftItem.class);
    boolean bool = shiftItemService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ShiftItemImportRes().setCount(c);
  }

  public @Override ShiftItemQueryByIdListRes queryByIdListRes(ShiftItemQueryByIdListReq req) {
    MPJLambdaWrapper<ShiftItem> q = new MPJLambdaWrapper<ShiftItem>(ShiftItem.class)
        .selectAll(ShiftItem.class).in(ShiftItem::getId, req.getIdList());
    List<ShiftItem> list = this.shiftItemService.list(q);
    List<ShiftItemDto> dataList = $.copyList(list, ShiftItemDto.class);
    return new ShiftItemQueryByIdListRes().setDataList(dataList);
  }
}
