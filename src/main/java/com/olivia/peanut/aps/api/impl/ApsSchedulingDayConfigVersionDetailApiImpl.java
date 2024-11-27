package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsSchedulingDayConfigVersionDetailApi;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetail.*;
import com.olivia.peanut.aps.api.impl.listener.ApsSchedulingDayConfigVersionDetailImportListener;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersionDetail;
import com.olivia.peanut.aps.service.ApsSchedulingDayConfigVersionDetailService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 排程版本配置明细表(ApsSchedulingDayConfigVersionDetail)表服务实现类
 *
 * @author peanut
 * @since 2024-07-19 19:19:57
 */
@RestController
public class ApsSchedulingDayConfigVersionDetailApiImpl implements ApsSchedulingDayConfigVersionDetailApi {

  private @Autowired ApsSchedulingDayConfigVersionDetailService apsSchedulingDayConfigVersionDetailService;

  /****
   * insert
   *
   */
  public @Override ApsSchedulingDayConfigVersionDetailInsertRes insert(ApsSchedulingDayConfigVersionDetailInsertReq req) {
    this.apsSchedulingDayConfigVersionDetailService.save($.copy(req, ApsSchedulingDayConfigVersionDetail.class));
    return new ApsSchedulingDayConfigVersionDetailInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSchedulingDayConfigVersionDetailDeleteByIdListRes deleteByIdList(ApsSchedulingDayConfigVersionDetailDeleteByIdListReq req) {
    apsSchedulingDayConfigVersionDetailService.removeByIds(req.getIdList());
    return new ApsSchedulingDayConfigVersionDetailDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSchedulingDayConfigVersionDetailQueryListRes queryList(ApsSchedulingDayConfigVersionDetailQueryListReq req) {
    return apsSchedulingDayConfigVersionDetailService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSchedulingDayConfigVersionDetailUpdateByIdRes updateById(ApsSchedulingDayConfigVersionDetailUpdateByIdReq req) {
    apsSchedulingDayConfigVersionDetailService.updateById($.copy(req, ApsSchedulingDayConfigVersionDetail.class));
    return new ApsSchedulingDayConfigVersionDetailUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsSchedulingDayConfigVersionDetailExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigVersionDetailExportQueryPageListReq req) {
    return apsSchedulingDayConfigVersionDetailService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSchedulingDayConfigVersionDetailExportQueryPageListReq req) {
    DynamicsPage<ApsSchedulingDayConfigVersionDetailExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSchedulingDayConfigVersionDetailExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingDayConfigVersionDetailExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSchedulingDayConfigVersionDetailExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSchedulingDayConfigVersionDetailExportQueryPageListInfoRes.class, listInfoRes, "排程版本配置明细表");
  }

  public @Override ApsSchedulingDayConfigVersionDetailImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSchedulingDayConfigVersionDetailImportReq> reqList = PoiExcelUtil.readData(file, new ApsSchedulingDayConfigVersionDetailImportListener(),
        ApsSchedulingDayConfigVersionDetailImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingDayConfigVersionDetail> readList = $.copyList(reqList, ApsSchedulingDayConfigVersionDetail.class);
    boolean bool = apsSchedulingDayConfigVersionDetailService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSchedulingDayConfigVersionDetailImportRes().setCount(c);
  }

  public @Override ApsSchedulingDayConfigVersionDetailQueryByIdListRes queryByIdListRes(ApsSchedulingDayConfigVersionDetailQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetail> q = new MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetail>(ApsSchedulingDayConfigVersionDetail.class)
        .selectAll(ApsSchedulingDayConfigVersionDetail.class).in(ApsSchedulingDayConfigVersionDetail::getId, req.getIdList());
    List<ApsSchedulingDayConfigVersionDetail> list = this.apsSchedulingDayConfigVersionDetailService.list(q);
    List<ApsSchedulingDayConfigVersionDetailDto> dataList = $.copyList(list, ApsSchedulingDayConfigVersionDetailDto.class);
    this.apsSchedulingDayConfigVersionDetailService.setName(dataList);
    return new ApsSchedulingDayConfigVersionDetailQueryByIdListRes().setDataList(dataList);
  }
}
