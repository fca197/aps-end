package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsRollingForecastOrderApi;
import com.olivia.peanut.aps.api.entity.apsRollingForecastOrder.*;
import com.olivia.peanut.aps.api.impl.listener.ApsRollingForecastOrderImportListener;
import com.olivia.peanut.aps.model.ApsRollingForecastOrder;
import com.olivia.peanut.aps.service.ApsRollingForecastOrderService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import com.olivia.sdk.utils.RunUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 滚动预测(ApsRollingForecastOrder)表服务实现类
 *
 * @author peanut
 * @since 2024-07-14 20:22:27
 */
@RestController
public class ApsRollingForecastOrderApiImpl implements ApsRollingForecastOrderApi {

  private @Autowired ApsRollingForecastOrderService apsRollingForecastOrderService;

  /****
   * insert
   *
   */
  public @Override ApsRollingForecastOrderInsertRes insert(ApsRollingForecastOrderInsertReq req) {
//    this.apsRollingForecastOrderService.save($.copy(req, ApsRollingForecastOrder.class));
    return this.apsRollingForecastOrderService.save(req);
//    return new ApsRollingForecastOrderInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsRollingForecastOrderDeleteByIdListRes deleteByIdList(ApsRollingForecastOrderDeleteByIdListReq req) {
    apsRollingForecastOrderService.removeByIds(req.getIdList());
    return new ApsRollingForecastOrderDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsRollingForecastOrderQueryListRes queryList(ApsRollingForecastOrderQueryListReq req) {
    return apsRollingForecastOrderService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsRollingForecastOrderUpdateByIdRes updateById(ApsRollingForecastOrderUpdateByIdReq req) {
    RunUtils.noImpl();
    apsRollingForecastOrderService.updateById($.copy(req, ApsRollingForecastOrder.class));
    return new ApsRollingForecastOrderUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsRollingForecastOrderExportQueryPageListInfoRes> queryPageList(ApsRollingForecastOrderExportQueryPageListReq req) {
    return apsRollingForecastOrderService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsRollingForecastOrderExportQueryPageListReq req) {
    DynamicsPage<ApsRollingForecastOrderExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsRollingForecastOrderExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsRollingForecastOrderExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsRollingForecastOrderExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsRollingForecastOrderExportQueryPageListInfoRes.class, listInfoRes, "滚动预测");
  }

  public @Override ApsRollingForecastOrderImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsRollingForecastOrderImportReq> reqList = PoiExcelUtil.readData(file, new ApsRollingForecastOrderImportListener(), ApsRollingForecastOrderImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsRollingForecastOrder> readList = $.copyList(reqList, ApsRollingForecastOrder.class);
    boolean bool = apsRollingForecastOrderService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsRollingForecastOrderImportRes().setCount(c);
  }

  public @Override ApsRollingForecastOrderQueryByIdListRes queryByIdListRes(ApsRollingForecastOrderQueryByIdListReq req) {
    MPJLambdaWrapper<ApsRollingForecastOrder> q = new MPJLambdaWrapper<ApsRollingForecastOrder>(ApsRollingForecastOrder.class)
        .selectAll(ApsRollingForecastOrder.class).in(ApsRollingForecastOrder::getId, req.getIdList());
    List<ApsRollingForecastOrder> list = this.apsRollingForecastOrderService.list(q);
    List<ApsRollingForecastOrderDto> dataList = $.copyList(list, ApsRollingForecastOrderDto.class);
    this.apsRollingForecastOrderService.setName(dataList);
    return new ApsRollingForecastOrderQueryByIdListRes().setDataList(dataList);
  }
}
