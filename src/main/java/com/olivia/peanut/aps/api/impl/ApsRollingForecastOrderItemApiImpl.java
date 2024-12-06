package com.olivia.peanut.aps.api.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.collect.Lists;
import com.olivia.peanut.aps.api.ApsRollingForecastOrderItemApi;
import com.olivia.peanut.aps.api.entity.apsProcessPath.ApsProcessPathDto;
import com.olivia.peanut.aps.api.entity.apsRollingForecastOrderItem.*;
import com.olivia.peanut.aps.api.impl.listener.ApsRollingForecastOrderItemImportListener;
import com.olivia.peanut.aps.con.ApsStr;
import com.olivia.peanut.aps.model.ApsOrder;
import com.olivia.peanut.aps.model.ApsRollingForecastOrder;
import com.olivia.peanut.aps.model.ApsRollingForecastOrderItem;
import com.olivia.peanut.aps.model.ApsStatus;
import com.olivia.peanut.aps.service.*;
import com.olivia.peanut.aps.service.pojo.FactoryConfigReq;
import com.olivia.peanut.aps.service.pojo.FactoryConfigRes;
import com.olivia.peanut.aps.utils.model.ApsProcessPathVo;
import com.olivia.peanut.aps.utils.process.ProcessUtils;
import com.olivia.sdk.utils.*;
import com.olivia.sdk.utils.EasyExcelUtilExportMultipleData.SheetData;
import com.olivia.sdk.utils.EasyExcelUtilExportMultipleData.SheetHeader;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 滚动预测订单节点表(ApsRollingForecastOrderItem)表服务实现类
 *
 * @author peanut
 * @since 2024-07-16 10:31:19
 */
@RestController
public class ApsRollingForecastOrderItemApiImpl implements ApsRollingForecastOrderItemApi {

  @Resource
  ApsOrderService apsOrderService;
  @Resource
  ApsStatusService apsStatusService;
  @Resource
  ApsFactoryService apsFactoryService;
  @Resource
  ApsRollingForecastOrderService apsRollingForecastOrderService;
  private @Autowired ApsRollingForecastOrderItemService apsRollingForecastOrderItemService;

  /****
   * insert
   *
   */
  public @Override ApsRollingForecastOrderItemInsertRes insert(ApsRollingForecastOrderItemInsertReq req) {
    this.apsRollingForecastOrderItemService.save($.copy(req, ApsRollingForecastOrderItem.class));
    return new ApsRollingForecastOrderItemInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsRollingForecastOrderItemDeleteByIdListRes deleteByIdList(ApsRollingForecastOrderItemDeleteByIdListReq req) {
    apsRollingForecastOrderItemService.removeByIds(req.getIdList());
    return new ApsRollingForecastOrderItemDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsRollingForecastOrderItemQueryListRes queryList(ApsRollingForecastOrderItemQueryListReq req) {
    return apsRollingForecastOrderItemService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsRollingForecastOrderItemUpdateByIdRes updateById(ApsRollingForecastOrderItemUpdateByIdReq req) {
    apsRollingForecastOrderItemService.updateById($.copy(req, ApsRollingForecastOrderItem.class));
    return new ApsRollingForecastOrderItemUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsRollingForecastOrderItemExportQueryPageListInfoRes> queryPageList(ApsRollingForecastOrderItemExportQueryPageListReq req) {
    return apsRollingForecastOrderItemService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsRollingForecastOrderItemExportQueryPageListReq req) {
    $.assertTrueCanIgnoreException(Objects.nonNull(req.getData()) && Objects.nonNull(req.getData().getForecastId()), "预测版本不能为空");
    Map<Long, Map<Long, ApsRollingForecastOrderItem>> orderMap = this.apsRollingForecastOrderItemService.list(
            new LambdaQueryWrapper<ApsRollingForecastOrderItem>().eq(ApsRollingForecastOrderItem::getForecastId, req.getData().getForecastId())).stream()
        .collect(Collectors.groupingBy(ApsRollingForecastOrderItem::getOrderId, Collectors.toMap(ApsRollingForecastOrderItem::getGoodsStatusId, Function.identity())));
    if (CollUtil.isEmpty(orderMap)) {
      DownLoadErrorDefaultFile.printTxt("无数据", "明细记录.xlsx");
      return;
    }
    ApsRollingForecastOrder forecastOrder = apsRollingForecastOrderService.getById(req.getData().getForecastId());

    Map<Long, String> statusIdNameMap = apsStatusService.list().stream().collect(Collectors.toMap(BaseEntity::getId, ApsStatus::getStatusName));
    Long factoryId = forecastOrder.getFactoryId();
    FactoryConfigRes factoryConfig = apsFactoryService.getFactoryConfig(new FactoryConfigReq().setGetPathDefault(Boolean.TRUE).setGetPath(Boolean.TRUE).setFactoryId(factoryId));
    ApsProcessPathDto defaultApsProcessPathDto = factoryConfig.getDefaultApsProcessPathDto();
    List<Long> statusBetween = ProcessUtils.getStatusBetween($.copy(defaultApsProcessPathDto, ApsProcessPathVo.class), forecastOrder.getBeginStatusId(),
        forecastOrder.getEndStatusId());

    List<Long> orderIdList = new ArrayList<>(orderMap.keySet());

    List<Runnable> runnableList = new ArrayList<>();

    Map<Long, ApsOrder> apsOrderMap = Collections.synchronizedMap(new HashMap<>(orderMap.size()));
    ListUtil.partition(orderIdList, 100).forEach(idList -> {
      runnableList.add(() -> apsOrderMap.putAll(apsOrderService.listByIds(idList).stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()))));
    });
    RunUtils.run("getApsOrder", runnableList);
    EasyExcelUtilExportMultipleData multipleData = new EasyExcelUtilExportMultipleData();
    multipleData.setFileName("排产结果");
    SheetData sheetData = new SheetData();
    sheetData.setSheetName("sheet");
    List<SheetHeader> sheetHeaderList = Lists.newLinkedList();
    sheetHeaderList.add(new SheetHeader().setShowName("订单号").setFieldName(ApsStr.ORDER_NO).setWidth(200));
    sheetHeaderList.add(new SheetHeader().setShowName("紧急度").setFieldName("urgencyLevel").setWidth(150));
    sheetHeaderList.add(new SheetHeader().setShowName("当前订单状态").setFieldName("orderStatusName").setWidth(150));
    statusBetween.forEach(statusId -> {
      sheetHeaderList.add(new SheetHeader().setShowName(statusIdNameMap.get(statusId)).setFieldName("status_" + statusId).setWidth(200));
    });
    sheetData.setHeaderList(sheetHeaderList);
    ArrayList<Object> dataList = Lists.newArrayList();

    apsOrderMap.values().forEach(order -> {
      Map<String, Object> orderMapTmp = new HashMap<>();
      orderMapTmp.put(ApsStr.ORDER_NO, order.getOrderNo());
      orderMapTmp.put("urgencyLevel", order.getUrgencyLevel());
      orderMapTmp.put("orderStatusName", statusIdNameMap.get(order.getOrderStatus()));
      Map<Long, ApsRollingForecastOrderItem> orderItemMap = orderMap.get(order.getId());
      if (CollUtil.isEmpty(orderItemMap)) {
        return;
      }
      statusBetween.forEach(statusId -> {
        ApsRollingForecastOrderItem orderItem = orderItemMap.get(statusId);
        if (Objects.nonNull(orderItem)) {
          orderMapTmp.put("status_" + statusId, orderItem.getStatusBeginDate().toString());
        }
      });

      dataList.add(orderMapTmp);
    });

    sheetData.setDataList(dataList);

    multipleData.setSheetDataList(List.of(sheetData));

    PoiExcelUtil.exportMultipleData(multipleData);
  }


  public @Override ApsRollingForecastOrderItemImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsRollingForecastOrderItemImportReq> reqList = PoiExcelUtil.readData(file, new ApsRollingForecastOrderItemImportListener(), ApsRollingForecastOrderItemImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsRollingForecastOrderItem> readList = $.copyList(reqList, ApsRollingForecastOrderItem.class);
    boolean bool = apsRollingForecastOrderItemService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsRollingForecastOrderItemImportRes().setCount(c);
  }

  public @Override ApsRollingForecastOrderItemQueryByIdListRes queryByIdListRes(ApsRollingForecastOrderItemQueryByIdListReq req) {
    MPJLambdaWrapper<ApsRollingForecastOrderItem> q = new MPJLambdaWrapper<ApsRollingForecastOrderItem>(ApsRollingForecastOrderItem.class).selectAll(
        ApsRollingForecastOrderItem.class).in(ApsRollingForecastOrderItem::getId, req.getIdList());
    List<ApsRollingForecastOrderItem> list = this.apsRollingForecastOrderItemService.list(q);
    List<ApsRollingForecastOrderItemDto> dataList = $.copyList(list, ApsRollingForecastOrderItemDto.class);
    this.apsRollingForecastOrderItemService.setName(dataList);
    return new ApsRollingForecastOrderItemQueryByIdListRes().setDataList(dataList);
  }
}
