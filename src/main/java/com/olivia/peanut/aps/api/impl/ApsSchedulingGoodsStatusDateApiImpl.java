package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsSchedulingGoodsStatusDateApi;
import com.olivia.peanut.aps.api.entity.apsSchedulingGoodsStatusDate.*;
import com.olivia.peanut.aps.api.impl.listener.ApsSchedulingGoodsStatusDateImportListener;
import com.olivia.peanut.aps.model.ApsSchedulingGoodsStatusDate;
import com.olivia.peanut.aps.service.ApsSchedulingGoodsStatusDateService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 订单商品状态表(ApsSchedulingGoodsStatusDate)表服务实现类
 *
 * @author peanut
 * @since 2024-06-14 21:35:08
 */
@RestController
public class ApsSchedulingGoodsStatusDateApiImpl implements ApsSchedulingGoodsStatusDateApi {

  private @Autowired ApsSchedulingGoodsStatusDateService apsSchedulingGoodsStatusDateService;

  /****
   * insert
   *
   */
  public @Override ApsSchedulingGoodsStatusDateInsertRes insert(ApsSchedulingGoodsStatusDateInsertReq req) {
    this.apsSchedulingGoodsStatusDateService.save($.copy(req, ApsSchedulingGoodsStatusDate.class));
    return new ApsSchedulingGoodsStatusDateInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSchedulingGoodsStatusDateDeleteByIdListRes deleteByIdList(ApsSchedulingGoodsStatusDateDeleteByIdListReq req) {
    apsSchedulingGoodsStatusDateService.removeByIds(req.getIdList());
    return new ApsSchedulingGoodsStatusDateDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSchedulingGoodsStatusDateQueryListRes queryList(ApsSchedulingGoodsStatusDateQueryListReq req) {
    return apsSchedulingGoodsStatusDateService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSchedulingGoodsStatusDateUpdateByIdRes updateById(ApsSchedulingGoodsStatusDateUpdateByIdReq req) {
    apsSchedulingGoodsStatusDateService.updateById($.copy(req, ApsSchedulingGoodsStatusDate.class));
    return new ApsSchedulingGoodsStatusDateUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsSchedulingGoodsStatusDateExportQueryPageListInfoRes> queryPageList(ApsSchedulingGoodsStatusDateExportQueryPageListReq req) {
    return apsSchedulingGoodsStatusDateService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSchedulingGoodsStatusDateExportQueryPageListReq req) {
    DynamicsPage<ApsSchedulingGoodsStatusDateExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSchedulingGoodsStatusDateExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingGoodsStatusDateExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSchedulingGoodsStatusDateExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSchedulingGoodsStatusDateExportQueryPageListInfoRes.class, listInfoRes, "订单商品状态表");
  }

  public @Override ApsSchedulingGoodsStatusDateImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSchedulingGoodsStatusDateImportReq> reqList = PoiExcelUtil.readData(file, new ApsSchedulingGoodsStatusDateImportListener(),
        ApsSchedulingGoodsStatusDateImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingGoodsStatusDate> readList = $.copyList(reqList, ApsSchedulingGoodsStatusDate.class);
    boolean bool = apsSchedulingGoodsStatusDateService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSchedulingGoodsStatusDateImportRes().setCount(c);
  }

  public @Override ApsSchedulingGoodsStatusDateQueryByIdListRes queryByIdListRes(ApsSchedulingGoodsStatusDateQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSchedulingGoodsStatusDate> q = new MPJLambdaWrapper<ApsSchedulingGoodsStatusDate>(ApsSchedulingGoodsStatusDate.class)
        .selectAll(ApsSchedulingGoodsStatusDate.class).in(ApsSchedulingGoodsStatusDate::getId, req.getIdList());
    List<ApsSchedulingGoodsStatusDate> list = this.apsSchedulingGoodsStatusDateService.list(q);
    List<ApsSchedulingGoodsStatusDateDto> dataList = $.copyList(list, ApsSchedulingGoodsStatusDateDto.class);
    this.apsSchedulingGoodsStatusDateService.setName(dataList);
    return new ApsSchedulingGoodsStatusDateQueryByIdListRes().setDataList(dataList);
  }
}
