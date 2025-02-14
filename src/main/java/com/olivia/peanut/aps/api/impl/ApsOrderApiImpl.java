package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsOrderApi;
import com.olivia.peanut.aps.api.entity.apsOrder.*;
import com.olivia.peanut.aps.api.impl.listener.ApsOrderImportListener;
import com.olivia.peanut.aps.model.ApsOrder;
import com.olivia.peanut.aps.service.ApsOrderService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * (ApsOrder)表服务实现类
 *
 * @author peanut
 * @since 2024-04-09 13:19:35
 */
@RestController
public class ApsOrderApiImpl implements ApsOrderApi {

  private @Autowired ApsOrderService apsOrderService;

  /****
   * insert
   *
   */
  public @Override ApsOrderInsertRes insert(ApsOrderInsertReq req) {
    return this.apsOrderService.save(req);
//    return new ApsOrderInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsOrderDeleteByIdListRes deleteByIdList(ApsOrderDeleteByIdListReq req) {
    apsOrderService.removeByIds(req.getIdList());
    return new ApsOrderDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsOrderQueryListRes queryList(ApsOrderQueryListReq req) {
    return apsOrderService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsOrderUpdateByIdRes updateById(ApsOrderUpdateByIdReq req) {
    apsOrderService.updateById($.copy(req, ApsOrder.class));
    return new ApsOrderUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsOrderExportQueryPageListInfoRes> queryPageList(ApsOrderExportQueryPageListReq req) {
    return apsOrderService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsOrderExportQueryPageListReq req) {
    DynamicsPage<ApsOrderExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsOrderExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsOrderExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsOrderExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsOrderExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsOrderImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsOrderImportReq> reqList = PoiExcelUtil.readData(file, new ApsOrderImportListener(), ApsOrderImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsOrder> readList = $.copyList(reqList, ApsOrder.class);
    boolean bool = apsOrderService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsOrderImportRes().setCount(c);
  }

  public @Override ApsOrderQueryByIdListRes queryByIdListRes(ApsOrderQueryByIdListReq req) {
    MPJLambdaWrapper<ApsOrder> q = new MPJLambdaWrapper<ApsOrder>(ApsOrder.class)
        .selectAll(ApsOrder.class).in(ApsOrder::getId, req.getIdList());
    List<ApsOrder> list = this.apsOrderService.list(q);
    List<ApsOrderDto> dataList = $.copyList(list, ApsOrderDto.class);
    this.apsOrderService.setName(dataList);
    return new ApsOrderQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public DynamicsPage<ApsOrderTimeLineRes> timeLine(ApsOrderTimeLineReq req) {
    return this.apsOrderService.timeLine(req);
  }

  @Override
  public ApsOrderBatchInsertRes batchInsert(ApsOrderBatchInsertReq req) {
    return apsOrderService.saveBatch(req);
  }

  @Override
  public ApsOrderUpdateOrderStatusRes updateOrderStatus(ApsOrderUpdateOrderStatusReq req) {
    return this.apsOrderService.updateOrderStatus(req);
  }

  @Override
  public ApsOrderUpdateSchedulingDateRes updateSchedulingDate(ApsOrderUpdateSchedulingDateReq req) {
    return this.apsOrderService.updateSchedulingDate(req);
  }

  @Override
  public OrderCreateDayCountRes orderCreateDayCount(OrderCreateDayCountReq req) {
    return this.apsOrderService.orderCreateDayCount(req);
  }

  @Override
  public OrderStatusListRes orderStatusList(OrderStatusListReq req) {
    return new OrderStatusListRes().setDataList(Arrays.stream(ApsOrderStatusEnum.values())
        .map(t -> new OrderStatusListRes.Info().setCode(t.getCode()).setDesc(t.getDesc())).toList());
  }
}
