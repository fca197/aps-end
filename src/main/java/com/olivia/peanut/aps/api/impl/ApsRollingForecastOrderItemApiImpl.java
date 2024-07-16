package com.olivia.peanut.aps.api.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.olivia.peanut.aps.model.ApsRollingForecastOrderItem;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import com.olivia.peanut.aps.api.entity.apsRollingForecastOrderItem.*;
import com.olivia.peanut.aps.service.ApsRollingForecastOrderItemService;
import com.olivia.peanut.aps.model.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.aps.api.ApsRollingForecastOrderItemApi;

import com.olivia.peanut.aps.api.impl.listener.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 滚动预测订单节点表(ApsRollingForecastOrderItem)表服务实现类
 *
 * @author peanut
 * @since 2024-07-16 10:31:19
 */
@RestController
public class ApsRollingForecastOrderItemApiImpl implements ApsRollingForecastOrderItemApi {

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
    DynamicsPage<ApsRollingForecastOrderItemExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsRollingForecastOrderItemExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsRollingForecastOrderItemExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsRollingForecastOrderItemExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsRollingForecastOrderItemExportQueryPageListInfoRes.class, listInfoRes, "滚动预测订单节点表");
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
    MPJLambdaWrapper<ApsRollingForecastOrderItem> q = new MPJLambdaWrapper<ApsRollingForecastOrderItem>(ApsRollingForecastOrderItem.class)
        .selectAll(ApsRollingForecastOrderItem.class).in(ApsRollingForecastOrderItem::getId, req.getIdList());
    List<ApsRollingForecastOrderItem> list = this.apsRollingForecastOrderItemService.list(q);
    List<ApsRollingForecastOrderItemDto> dataList = $.copyList(list, ApsRollingForecastOrderItemDto.class);
    this.apsRollingForecastOrderItemService.setName(dataList);
    return new ApsRollingForecastOrderItemQueryByIdListRes().setDataList(dataList);
  }
}
