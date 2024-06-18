package com.olivia.peanut.portal.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.JcxOrderItemApi;
import com.olivia.peanut.portal.api.entity.jcxOrderItem.*;
import com.olivia.peanut.portal.api.impl.listener.JcxOrderItemImportListener;
import com.olivia.peanut.portal.model.JcxOrderItem;
import com.olivia.peanut.portal.service.JcxOrderItemService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * (JcxOrderItem)表服务实现类
 *
 * @author peanut
 * @since 2024-03-22 10:38:06
 */
@RestController
public class JcxOrderItemApiImpl implements JcxOrderItemApi {

  private @Autowired JcxOrderItemService jcxOrderItemService;

  /****
   * insert
   *
   */
  public @Override JcxOrderItemInsertRes insert(JcxOrderItemInsertReq req) {
    JcxOrderItem orderItem = $.copy(req, JcxOrderItem.class);
    this.jcxOrderItemService.save(orderItem);
    return new JcxOrderItemInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override JcxOrderItemDeleteByIdListRes deleteByIdList(JcxOrderItemDeleteByIdListReq req) {
    jcxOrderItemService.removeByIds(req.getIdList());
    return new JcxOrderItemDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override JcxOrderItemQueryListRes queryList(JcxOrderItemQueryListReq req) {
    return jcxOrderItemService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override JcxOrderItemUpdateByIdRes updateById(JcxOrderItemUpdateByIdReq req) {
    jcxOrderItemService.updateById($.copy(req, JcxOrderItem.class));
    return new JcxOrderItemUpdateByIdRes();

  }

  public @Override DynamicsPage<JcxOrderItemExportQueryPageListInfoRes> queryPageList(JcxOrderItemExportQueryPageListReq req) {
    return jcxOrderItemService.queryPageList(req);
  }

  public @Override void queryPageListExport(JcxOrderItemExportQueryPageListReq req) {
    DynamicsPage<JcxOrderItemExportQueryPageListInfoRes> page = queryPageList(req);
    List<JcxOrderItemExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<JcxOrderItemExportQueryPageListInfoRes> listInfoRes = $.copyList(list, JcxOrderItemExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(JcxOrderItemExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override JcxOrderItemImportRes importData(@RequestParam("file") MultipartFile file) {
    List<JcxOrderItemImportReq> reqList = PoiExcelUtil.readData(file, new JcxOrderItemImportListener(), JcxOrderItemImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<JcxOrderItem> readList = $.copyList(reqList, JcxOrderItem.class);
    boolean bool = jcxOrderItemService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new JcxOrderItemImportRes().setCount(c);
  }

  public @Override JcxOrderItemQueryByIdListRes queryByIdListRes(JcxOrderItemQueryByIdListReq req) {
    MPJLambdaWrapper<JcxOrderItem> q = new MPJLambdaWrapper<JcxOrderItem>(JcxOrderItem.class)
        .selectAll(JcxOrderItem.class).in(JcxOrderItem::getId, req.getIdList());
    List<JcxOrderItem> list = this.jcxOrderItemService.list(q);
    List<JcxOrderItemDto> dataList = $.copyList(list, JcxOrderItemDto.class);
    return new JcxOrderItemQueryByIdListRes().setDataList(dataList);
  }
}
