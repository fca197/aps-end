package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsSchedulingDayConfigItemApi;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigItem.*;
import com.olivia.peanut.aps.api.impl.listener.ApsSchedulingDayConfigItemImportListener;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigItem;
import com.olivia.peanut.aps.service.ApsSchedulingDayConfigItemService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 排程版本配置表(ApsSchedulingDayConfigItem)表服务实现类
 *
 * @author peanut
 * @since 2024-07-19 19:19:51
 */
@RestController
public class ApsSchedulingDayConfigItemApiImpl implements ApsSchedulingDayConfigItemApi {

  private @Autowired ApsSchedulingDayConfigItemService apsSchedulingDayConfigItemService;

  /****
   * insert
   *
   */
  public @Override ApsSchedulingDayConfigItemInsertRes insert(ApsSchedulingDayConfigItemInsertReq req) {
    this.apsSchedulingDayConfigItemService.save($.copy(req, ApsSchedulingDayConfigItem.class));
    return new ApsSchedulingDayConfigItemInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSchedulingDayConfigItemDeleteByIdListRes deleteByIdList(ApsSchedulingDayConfigItemDeleteByIdListReq req) {
    apsSchedulingDayConfigItemService.removeByIds(req.getIdList());
    return new ApsSchedulingDayConfigItemDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSchedulingDayConfigItemQueryListRes queryList(ApsSchedulingDayConfigItemQueryListReq req) {
    return apsSchedulingDayConfigItemService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSchedulingDayConfigItemUpdateByIdRes updateById(ApsSchedulingDayConfigItemUpdateByIdReq req) {
    apsSchedulingDayConfigItemService.updateById($.copy(req, ApsSchedulingDayConfigItem.class));
    return new ApsSchedulingDayConfigItemUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsSchedulingDayConfigItemExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigItemExportQueryPageListReq req) {
    return apsSchedulingDayConfigItemService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSchedulingDayConfigItemExportQueryPageListReq req) {
    DynamicsPage<ApsSchedulingDayConfigItemExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSchedulingDayConfigItemExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingDayConfigItemExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSchedulingDayConfigItemExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSchedulingDayConfigItemExportQueryPageListInfoRes.class, listInfoRes, "排程版本配置表");
  }

  public @Override ApsSchedulingDayConfigItemImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSchedulingDayConfigItemImportReq> reqList = PoiExcelUtil.readData(file, new ApsSchedulingDayConfigItemImportListener(), ApsSchedulingDayConfigItemImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingDayConfigItem> readList = $.copyList(reqList, ApsSchedulingDayConfigItem.class);
    boolean bool = apsSchedulingDayConfigItemService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSchedulingDayConfigItemImportRes().setCount(c);
  }

  public @Override ApsSchedulingDayConfigItemQueryByIdListRes queryByIdListRes(ApsSchedulingDayConfigItemQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSchedulingDayConfigItem> q = new MPJLambdaWrapper<ApsSchedulingDayConfigItem>(ApsSchedulingDayConfigItem.class)
        .selectAll(ApsSchedulingDayConfigItem.class).in(ApsSchedulingDayConfigItem::getId, req.getIdList());
    List<ApsSchedulingDayConfigItem> list = this.apsSchedulingDayConfigItemService.list(q);
    List<ApsSchedulingDayConfigItemDto> dataList = $.copyList(list, ApsSchedulingDayConfigItemDto.class);
    this.apsSchedulingDayConfigItemService.setName(dataList);
    return new ApsSchedulingDayConfigItemQueryByIdListRes().setDataList(dataList);
  }
}
