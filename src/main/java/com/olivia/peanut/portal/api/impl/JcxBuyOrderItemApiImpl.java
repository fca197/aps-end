package com.olivia.peanut.portal.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.JcxBuyOrderItemApi;
import com.olivia.peanut.portal.api.entity.jcxBuyOrderItem.*;
import com.olivia.peanut.portal.api.impl.listener.JcxBuyOrderItemImportListener;
import com.olivia.peanut.portal.model.JcxBuyOrderItem;
import com.olivia.peanut.portal.service.JcxBuyOrderItemService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (JcxBuyOrderItem)表服务实现类
 *
 * @author peanut
 * @since 2024-03-27 13:51:37
 */
@RestController
public class JcxBuyOrderItemApiImpl implements JcxBuyOrderItemApi {

  private @Autowired JcxBuyOrderItemService jcxBuyOrderItemService;

  /****
   * insert
   *
   */
  public @Override JcxBuyOrderItemInsertRes insert(JcxBuyOrderItemInsertReq req) {
    this.jcxBuyOrderItemService.save($.copy(req, JcxBuyOrderItem.class));
    return new JcxBuyOrderItemInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override JcxBuyOrderItemDeleteByIdListRes deleteByIdList(JcxBuyOrderItemDeleteByIdListReq req) {
    jcxBuyOrderItemService.removeByIds(req.getIdList());
    return new JcxBuyOrderItemDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override JcxBuyOrderItemQueryListRes queryList(JcxBuyOrderItemQueryListReq req) {
    return jcxBuyOrderItemService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override JcxBuyOrderItemUpdateByIdRes updateById(JcxBuyOrderItemUpdateByIdReq req) {
    jcxBuyOrderItemService.updateById($.copy(req, JcxBuyOrderItem.class));
    return new JcxBuyOrderItemUpdateByIdRes();

  }

  public @Override DynamicsPage<JcxBuyOrderItemExportQueryPageListInfoRes> queryPageList(JcxBuyOrderItemExportQueryPageListReq req) {
    return jcxBuyOrderItemService.queryPageList(req);
  }

  public @Override void queryPageListExport(JcxBuyOrderItemExportQueryPageListReq req) {
    DynamicsPage<JcxBuyOrderItemExportQueryPageListInfoRes> page = queryPageList(req);
    List<JcxBuyOrderItemExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<JcxBuyOrderItemExportQueryPageListInfoRes> listInfoRes = $.copyList(list, JcxBuyOrderItemExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(JcxBuyOrderItemExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override JcxBuyOrderItemImportRes importData(@RequestParam("file") MultipartFile file) {
    List<JcxBuyOrderItemImportReq> reqList = PoiExcelUtil.readData(file, new JcxBuyOrderItemImportListener(), JcxBuyOrderItemImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<JcxBuyOrderItem> readList = $.copyList(reqList, JcxBuyOrderItem.class);
    boolean bool = jcxBuyOrderItemService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new JcxBuyOrderItemImportRes().setCount(c);
  }

  public @Override JcxBuyOrderItemQueryByIdListRes queryByIdListRes(JcxBuyOrderItemQueryByIdListReq req) {
    MPJLambdaWrapper<JcxBuyOrderItem> q = new MPJLambdaWrapper<JcxBuyOrderItem>(JcxBuyOrderItem.class)
        .selectAll(JcxBuyOrderItem.class).in(JcxBuyOrderItem::getId, req.getIdList());
    List<JcxBuyOrderItem> list = this.jcxBuyOrderItemService.list(q);
    List<JcxBuyOrderItemDto> dataList = $.copyList(list, JcxBuyOrderItemDto.class);
    return new JcxBuyOrderItemQueryByIdListRes().setDataList(dataList);
  }
}
