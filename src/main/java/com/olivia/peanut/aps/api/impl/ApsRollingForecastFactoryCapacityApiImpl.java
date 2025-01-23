package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsRollingForecastFactoryCapacityApi;
import com.olivia.peanut.aps.api.entity.apsRollingForecastFactoryCapacity.*;
import com.olivia.peanut.aps.api.impl.listener.ApsRollingForecastFactoryCapacityImportListener;
import com.olivia.peanut.aps.model.ApsRollingForecastFactoryCapacity;
import com.olivia.peanut.aps.service.ApsRollingForecastFactoryCapacityService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 滚动预测(ApsRollingForecastFactoryCapacity)表服务实现类
 *
 * @author peanut
 * @since 2024-07-14 20:22:22
 */
@RestController
public class ApsRollingForecastFactoryCapacityApiImpl implements ApsRollingForecastFactoryCapacityApi {

  private @Autowired ApsRollingForecastFactoryCapacityService apsRollingForecastFactoryCapacityService;

  /****
   * insert
   *
   */
  public @Override ApsRollingForecastFactoryCapacityInsertRes insert(ApsRollingForecastFactoryCapacityInsertReq req) {
    return this.apsRollingForecastFactoryCapacityService.save(req);
//    return new ApsRollingForecastFactoryCapacityInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsRollingForecastFactoryCapacityDeleteByIdListRes deleteByIdList(ApsRollingForecastFactoryCapacityDeleteByIdListReq req) {
    apsRollingForecastFactoryCapacityService.removeByIds(req.getIdList());
    return new ApsRollingForecastFactoryCapacityDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsRollingForecastFactoryCapacityQueryListRes queryList(ApsRollingForecastFactoryCapacityQueryListReq req) {
    return apsRollingForecastFactoryCapacityService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsRollingForecastFactoryCapacityUpdateByIdRes updateById(ApsRollingForecastFactoryCapacityUpdateByIdReq req) {
    apsRollingForecastFactoryCapacityService.updateById($.copy(req, ApsRollingForecastFactoryCapacity.class));
    return new ApsRollingForecastFactoryCapacityUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsRollingForecastFactoryCapacityExportQueryPageListInfoRes> queryPageList(ApsRollingForecastFactoryCapacityExportQueryPageListReq req) {
    return apsRollingForecastFactoryCapacityService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsRollingForecastFactoryCapacityExportQueryPageListReq req) {
    DynamicsPage<ApsRollingForecastFactoryCapacityExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsRollingForecastFactoryCapacityExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsRollingForecastFactoryCapacityExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsRollingForecastFactoryCapacityExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsRollingForecastFactoryCapacityExportQueryPageListInfoRes.class, listInfoRes, "滚动预测");
  }

  public @Override ApsRollingForecastFactoryCapacityImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsRollingForecastFactoryCapacityImportReq> reqList = PoiExcelUtil.readData(file, new ApsRollingForecastFactoryCapacityImportListener(),
        ApsRollingForecastFactoryCapacityImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsRollingForecastFactoryCapacity> readList = $.copyList(reqList, ApsRollingForecastFactoryCapacity.class);
    boolean bool = apsRollingForecastFactoryCapacityService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsRollingForecastFactoryCapacityImportRes().setCount(c);
  }

  public @Override ApsRollingForecastFactoryCapacityQueryByIdListRes queryByIdListRes(ApsRollingForecastFactoryCapacityQueryByIdListReq req) {
    MPJLambdaWrapper<ApsRollingForecastFactoryCapacity> q = new MPJLambdaWrapper<ApsRollingForecastFactoryCapacity>(ApsRollingForecastFactoryCapacity.class)
        .selectAll(ApsRollingForecastFactoryCapacity.class).in(ApsRollingForecastFactoryCapacity::getId, req.getIdList());
    List<ApsRollingForecastFactoryCapacity> list = this.apsRollingForecastFactoryCapacityService.list(q);
    List<ApsRollingForecastFactoryCapacityDto> dataList = $.copyList(list, ApsRollingForecastFactoryCapacityDto.class);
    this.apsRollingForecastFactoryCapacityService.setName(dataList);
    return new ApsRollingForecastFactoryCapacityQueryByIdListRes().setDataList(dataList);
  }
}
