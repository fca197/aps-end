package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsSchedulingIssueItemApi;
import com.olivia.peanut.aps.api.entity.apsSchedulingIssueItem.*;
import com.olivia.peanut.aps.api.impl.listener.ApsSchedulingIssueItemImportListener;
import com.olivia.peanut.aps.model.ApsSchedulingIssueItem;
import com.olivia.peanut.aps.service.ApsSchedulingIssueItemService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 排产下发详情(ApsSchedulingIssueItem)表服务实现类
 *
 * @author peanut
 * @since 2024-07-20 13:55:54
 */
@RestController
public class ApsSchedulingIssueItemApiImpl implements ApsSchedulingIssueItemApi {

  private @Autowired ApsSchedulingIssueItemService apsSchedulingIssueItemService;

  /****
   * insert
   *
   */
  public @Override ApsSchedulingIssueItemInsertRes insert(ApsSchedulingIssueItemInsertReq req) {
    return this.apsSchedulingIssueItemService.save(req);
//    return new ApsSchedulingIssueItemInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSchedulingIssueItemDeleteByIdListRes deleteByIdList(ApsSchedulingIssueItemDeleteByIdListReq req) {
    apsSchedulingIssueItemService.removeByIds(req.getIdList());
    return new ApsSchedulingIssueItemDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSchedulingIssueItemQueryListRes queryList(ApsSchedulingIssueItemQueryListReq req) {
    return apsSchedulingIssueItemService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSchedulingIssueItemUpdateByIdRes updateById(ApsSchedulingIssueItemUpdateByIdReq req) {
    apsSchedulingIssueItemService.updateById($.copy(req, ApsSchedulingIssueItem.class));
    return new ApsSchedulingIssueItemUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsSchedulingIssueItemExportQueryPageListInfoRes> queryPageList(ApsSchedulingIssueItemExportQueryPageListReq req) {
    return apsSchedulingIssueItemService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSchedulingIssueItemExportQueryPageListReq req) {
    DynamicsPage<ApsSchedulingIssueItemExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSchedulingIssueItemExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingIssueItemExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSchedulingIssueItemExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSchedulingIssueItemExportQueryPageListInfoRes.class, listInfoRes, "排产下发详情");
  }

  public @Override ApsSchedulingIssueItemImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSchedulingIssueItemImportReq> reqList = PoiExcelUtil.readData(file, new ApsSchedulingIssueItemImportListener(), ApsSchedulingIssueItemImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingIssueItem> readList = $.copyList(reqList, ApsSchedulingIssueItem.class);
    boolean bool = apsSchedulingIssueItemService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSchedulingIssueItemImportRes().setCount(c);
  }

  public @Override ApsSchedulingIssueItemQueryByIdListRes queryByIdListRes(ApsSchedulingIssueItemQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSchedulingIssueItem> q = new MPJLambdaWrapper<ApsSchedulingIssueItem>(ApsSchedulingIssueItem.class)
        .selectAll(ApsSchedulingIssueItem.class).in(ApsSchedulingIssueItem::getId, req.getIdList());
    List<ApsSchedulingIssueItem> list = this.apsSchedulingIssueItemService.list(q);
    List<ApsSchedulingIssueItemDto> dataList = $.copyList(list, ApsSchedulingIssueItemDto.class);
    this.apsSchedulingIssueItemService.setName(dataList);
    return new ApsSchedulingIssueItemQueryByIdListRes().setDataList(dataList);
  }
}
