package com.olivia.peanut.portal.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.JcxOrderApi;
import com.olivia.peanut.portal.api.entity.jcxOrder.*;
import com.olivia.peanut.portal.api.impl.listener.JcxOrderImportListener;
import com.olivia.peanut.portal.model.JcxOrder;
import com.olivia.peanut.portal.service.JcxOrderService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (JcxOrder)表服务实现类
 *
 * @author peanut
 * @since 2024-03-22 10:38:06
 */
@Slf4j
@RestController
public class JcxOrderApiImpl implements JcxOrderApi {


  private @Autowired JcxOrderService jcxOrderService;

  /****
   * insert
   *
   */
  @Transactional
  public @Override JcxOrderInsertRes insert(JcxOrderInsertReq req) {

    this.jcxOrderService.save(req);
    return new JcxOrderInsertRes().setCount(1);
  }


  /****
   * deleteByIds
   *
   */
  public @Override JcxOrderDeleteByIdListRes deleteByIdList(JcxOrderDeleteByIdListReq req) {
    jcxOrderService.removeByIds(req.getIdList());
    return new JcxOrderDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override JcxOrderQueryListRes queryList(JcxOrderQueryListReq req) {
    return jcxOrderService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override JcxOrderUpdateByIdRes updateById(JcxOrderUpdateByIdReq req) {
    jcxOrderService.updateById($.copy(req, JcxOrder.class));
    return new JcxOrderUpdateByIdRes();

  }

  public @Override DynamicsPage<JcxOrderExportQueryPageListInfoRes> queryPageList(JcxOrderExportQueryPageListReq req) {
    return jcxOrderService.queryPageList(req);
  }

  public @Override void queryPageListExport(JcxOrderExportQueryPageListReq req) {
    DynamicsPage<JcxOrderExportQueryPageListInfoRes> page = queryPageList(req);
    List<JcxOrderExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<JcxOrderExportQueryPageListInfoRes> listInfoRes = $.copyList(list, JcxOrderExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(JcxOrderExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override JcxOrderImportRes importData(@RequestParam("file") MultipartFile file) {
    List<JcxOrderImportReq> reqList = PoiExcelUtil.readData(file, new JcxOrderImportListener(), JcxOrderImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<JcxOrder> readList = $.copyList(reqList, JcxOrder.class);
    boolean bool = jcxOrderService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new JcxOrderImportRes().setCount(c);
  }

  public @Override JcxOrderQueryByIdListRes queryByIdListRes(JcxOrderQueryByIdListReq req) {
    MPJLambdaWrapper<JcxOrder> q = new MPJLambdaWrapper<JcxOrder>(JcxOrder.class).selectAll(JcxOrder.class).in(JcxOrder::getId, req.getIdList());
    List<JcxOrder> list = this.jcxOrderService.list(q);
    List<JcxOrderDto> dataList = $.copyList(list, JcxOrderDto.class);
    this.jcxOrderService.setName(true, dataList);
    return new JcxOrderQueryByIdListRes().setDataList(dataList);
  }

  public @Override GetOrderStatusRes getOrderStatus(GetOrderStatusReq req) {
    return new GetOrderStatusRes().setOrderStatusName(OrderStatusEnum.orderStatusNameMap);
  }
}
