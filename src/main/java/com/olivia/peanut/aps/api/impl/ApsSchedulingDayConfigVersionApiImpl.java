package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsSchedulingDayConfigVersionApi;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersion.*;
import com.olivia.peanut.aps.api.impl.listener.ApsSchedulingDayConfigVersionImportListener;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersion;
import com.olivia.peanut.aps.service.ApsSchedulingDayConfigVersionService;
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
 * 排程版本(ApsSchedulingDayConfigVersion)表服务实现类
 *
 * @author peanut
 * @since 2024-07-19 19:19:54
 */
@RestController
public class ApsSchedulingDayConfigVersionApiImpl implements ApsSchedulingDayConfigVersionApi {

  private @Autowired ApsSchedulingDayConfigVersionService apsSchedulingDayConfigVersionService;

  /****
   * insert
   *
   */
  public @Override ApsSchedulingDayConfigVersionInsertRes insert(ApsSchedulingDayConfigVersionInsertReq req) {
//    this.apsSchedulingDayConfigVersionService.save($.copy(req, ApsSchedulingDayConfigVersion.class));
    return this.apsSchedulingDayConfigVersionService.save(req);
//    return new ApsSchedulingDayConfigVersionInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSchedulingDayConfigVersionDeleteByIdListRes deleteByIdList(ApsSchedulingDayConfigVersionDeleteByIdListReq req) {
    apsSchedulingDayConfigVersionService.removeByIds(req.getIdList());
    return new ApsSchedulingDayConfigVersionDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSchedulingDayConfigVersionQueryListRes queryList(ApsSchedulingDayConfigVersionQueryListReq req) {
    return apsSchedulingDayConfigVersionService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSchedulingDayConfigVersionUpdateByIdRes updateById(ApsSchedulingDayConfigVersionUpdateByIdReq req) {
    RunUtils.noImpl();
    apsSchedulingDayConfigVersionService.updateById($.copy(req, ApsSchedulingDayConfigVersion.class));
    return new ApsSchedulingDayConfigVersionUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsSchedulingDayConfigVersionExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigVersionExportQueryPageListReq req) {
    return apsSchedulingDayConfigVersionService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSchedulingDayConfigVersionExportQueryPageListReq req) {
    DynamicsPage<ApsSchedulingDayConfigVersionExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSchedulingDayConfigVersionExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingDayConfigVersionExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSchedulingDayConfigVersionExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSchedulingDayConfigVersionExportQueryPageListInfoRes.class, listInfoRes, "排程版本");
  }

  public @Override ApsSchedulingDayConfigVersionImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSchedulingDayConfigVersionImportReq> reqList = PoiExcelUtil.readData(file, new ApsSchedulingDayConfigVersionImportListener(),
        ApsSchedulingDayConfigVersionImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingDayConfigVersion> readList = $.copyList(reqList, ApsSchedulingDayConfigVersion.class);
    boolean bool = apsSchedulingDayConfigVersionService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSchedulingDayConfigVersionImportRes().setCount(c);
  }

  public @Override ApsSchedulingDayConfigVersionQueryByIdListRes queryByIdListRes(ApsSchedulingDayConfigVersionQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSchedulingDayConfigVersion> q = new MPJLambdaWrapper<ApsSchedulingDayConfigVersion>(ApsSchedulingDayConfigVersion.class)
        .selectAll(ApsSchedulingDayConfigVersion.class).in(ApsSchedulingDayConfigVersion::getId, req.getIdList());
    List<ApsSchedulingDayConfigVersion> list = this.apsSchedulingDayConfigVersionService.list(q);
    List<ApsSchedulingDayConfigVersionDto> dataList = $.copyList(list, ApsSchedulingDayConfigVersionDto.class);
    this.apsSchedulingDayConfigVersionService.setName(dataList);
    return new ApsSchedulingDayConfigVersionQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public ApsSchedulingDayConfigVersionDetailListRes detailList(ApsSchedulingDayConfigVersionDetailListReq req) {
    return this.apsSchedulingDayConfigVersionService.detailList(req);
  }


  @Override
  public ApsSchedulingDayConfigVersionUpdateOrderSortIndexRes updateOrderSortIndex(ApsSchedulingDayConfigVersionUpdateOrderSortIndexReq req) {
    return this.apsSchedulingDayConfigVersionService.updateOrderSortIndex(req);
  }
}
