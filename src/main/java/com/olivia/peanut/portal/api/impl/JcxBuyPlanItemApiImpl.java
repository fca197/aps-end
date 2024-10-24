package com.olivia.peanut.portal.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.JcxBuyPlanItemApi;
import com.olivia.peanut.portal.api.entity.jcxBuyPlanItem.*;
import com.olivia.peanut.portal.api.impl.listener.JcxBuyPlanItemImportListener;
import com.olivia.peanut.portal.model.JcxBuyPlanItem;
import com.olivia.peanut.portal.service.JcxBuyPlanItemService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * (JcxBuyPlanItem)表服务实现类
 *
 * @author peanut
 * @since 2024-03-24 20:27:11
 */
@RestController
public class JcxBuyPlanItemApiImpl implements JcxBuyPlanItemApi {

  private @Autowired JcxBuyPlanItemService jcxBuyPlanItemService;

  /****
   * insert
   *
   */
  public @Override JcxBuyPlanItemInsertRes insert(JcxBuyPlanItemInsertReq req) {
    this.jcxBuyPlanItemService.save($.copy(req, JcxBuyPlanItem.class));
    return new JcxBuyPlanItemInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override JcxBuyPlanItemDeleteByIdListRes deleteByIdList(JcxBuyPlanItemDeleteByIdListReq req) {
    jcxBuyPlanItemService.removeByIds(req.getIdList());
    return new JcxBuyPlanItemDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override JcxBuyPlanItemQueryListRes queryList(JcxBuyPlanItemQueryListReq req) {
    return jcxBuyPlanItemService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override JcxBuyPlanItemUpdateByIdRes updateById(JcxBuyPlanItemUpdateByIdReq req) {
    jcxBuyPlanItemService.updateById($.copy(req, JcxBuyPlanItem.class));
    return new JcxBuyPlanItemUpdateByIdRes();

  }

  public @Override DynamicsPage<JcxBuyPlanItemExportQueryPageListInfoRes> queryPageList(JcxBuyPlanItemExportQueryPageListReq req) {
    return jcxBuyPlanItemService.queryPageList(req);
  }

  public @Override void queryPageListExport(JcxBuyPlanItemExportQueryPageListReq req) {
    DynamicsPage<JcxBuyPlanItemExportQueryPageListInfoRes> page = queryPageList(req);
    List<JcxBuyPlanItemExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<JcxBuyPlanItemExportQueryPageListInfoRes> listInfoRes = $.copyList(list, JcxBuyPlanItemExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(JcxBuyPlanItemExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override JcxBuyPlanItemImportRes importData(@RequestParam("file") MultipartFile file) {
    List<JcxBuyPlanItemImportReq> reqList = PoiExcelUtil.readData(file, new JcxBuyPlanItemImportListener(), JcxBuyPlanItemImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<JcxBuyPlanItem> readList = $.copyList(reqList, JcxBuyPlanItem.class);
    boolean bool = jcxBuyPlanItemService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new JcxBuyPlanItemImportRes().setCount(c);
  }

  public @Override JcxBuyPlanItemQueryByIdListRes queryByIdListRes(JcxBuyPlanItemQueryByIdListReq req) {
    MPJLambdaWrapper<JcxBuyPlanItem> q = new MPJLambdaWrapper<JcxBuyPlanItem>(JcxBuyPlanItem.class)
        .selectAll(JcxBuyPlanItem.class).in(JcxBuyPlanItem::getId, req.getIdList());
    List<JcxBuyPlanItem> list = this.jcxBuyPlanItemService.list(q);
    List<JcxBuyPlanItemDto> dataList = $.copyList(list, JcxBuyPlanItemDto.class);
    return new JcxBuyPlanItemQueryByIdListRes().setDataList(dataList);
  }
}
