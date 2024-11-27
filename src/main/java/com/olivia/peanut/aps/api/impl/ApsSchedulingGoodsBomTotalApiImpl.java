package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsSchedulingGoodsBomTotalApi;
import com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBomTotal.*;
import com.olivia.peanut.aps.api.impl.listener.ApsSchedulingGoodsBomTotalImportListener;
import com.olivia.peanut.aps.model.ApsSchedulingGoodsBomTotal;
import com.olivia.peanut.aps.service.ApsSchedulingGoodsBomTotalService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 订单商品零件汇总表(ApsSchedulingGoodsBomTotal)表服务实现类
 *
 * @author peanut
 * @since 2024-06-02 22:04:08
 */
@RestController
public class ApsSchedulingGoodsBomTotalApiImpl implements ApsSchedulingGoodsBomTotalApi {


  private @Autowired
  ApsSchedulingGoodsBomTotalService apsSchedulingGoodsBomTotalService;

  /****
   * insert
   *
   */
  public @Override ApsSchedulingGoodsBomTotalInsertRes insert(ApsSchedulingGoodsBomTotalInsertReq req) {
    this.apsSchedulingGoodsBomTotalService.save($.copy(req, ApsSchedulingGoodsBomTotal.class));
    return new ApsSchedulingGoodsBomTotalInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSchedulingGoodsBomTotalDeleteByIdListRes deleteByIdList(ApsSchedulingGoodsBomTotalDeleteByIdListReq req) {
    apsSchedulingGoodsBomTotalService.removeByIds(req.getIdList());
    return new ApsSchedulingGoodsBomTotalDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSchedulingGoodsBomTotalQueryListRes queryList(ApsSchedulingGoodsBomTotalQueryListReq req) {
    return apsSchedulingGoodsBomTotalService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSchedulingGoodsBomTotalUpdateByIdRes updateById(ApsSchedulingGoodsBomTotalUpdateByIdReq req) {
    apsSchedulingGoodsBomTotalService.updateById($.copy(req, ApsSchedulingGoodsBomTotal.class));
    return new ApsSchedulingGoodsBomTotalUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsSchedulingGoodsBomTotalExportQueryPageListInfoRes> queryPageList(ApsSchedulingGoodsBomTotalExportQueryPageListReq req) {
    return apsSchedulingGoodsBomTotalService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSchedulingGoodsBomTotalExportQueryPageListReq req) {
    DynamicsPage<ApsSchedulingGoodsBomTotalExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSchedulingGoodsBomTotalExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingGoodsBomTotalExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSchedulingGoodsBomTotalExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSchedulingGoodsBomTotalExportQueryPageListInfoRes.class, listInfoRes, "订单商品零件汇总表");
  }

  public @Override ApsSchedulingGoodsBomTotalImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSchedulingGoodsBomTotalImportReq> reqList = PoiExcelUtil.readData(file, new ApsSchedulingGoodsBomTotalImportListener(), ApsSchedulingGoodsBomTotalImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingGoodsBomTotal> readList = $.copyList(reqList, ApsSchedulingGoodsBomTotal.class);
    boolean bool = apsSchedulingGoodsBomTotalService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSchedulingGoodsBomTotalImportRes().setCount(c);
  }

  public @Override ApsSchedulingGoodsBomTotalQueryByIdListRes queryByIdListRes(ApsSchedulingGoodsBomTotalQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSchedulingGoodsBomTotal> q = new MPJLambdaWrapper<ApsSchedulingGoodsBomTotal>(ApsSchedulingGoodsBomTotal.class)
        .selectAll(ApsSchedulingGoodsBomTotal.class).in(ApsSchedulingGoodsBomTotal::getId, req.getIdList());
    List<ApsSchedulingGoodsBomTotal> list = this.apsSchedulingGoodsBomTotalService.list(q);
    List<ApsSchedulingGoodsBomTotalDto> dataList = $.copyList(list, ApsSchedulingGoodsBomTotalDto.class);
    this.apsSchedulingGoodsBomTotalService.setName(dataList);
    return new ApsSchedulingGoodsBomTotalQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public DynamicsPage<ApsSchedulingGoodsBomTotalQueryBomTotalRes> queryBomTotal(ApsSchedulingGoodsBomTotalQueryBomTotalReq req) {
    return this.apsSchedulingGoodsBomTotalService.queryBomTotal(req);
  }

  @Override
  public ApsSchedulingGoodsBomTotalCreateBomBuyPlanRes createBomBuyPlan(ApsSchedulingGoodsBomTotalCreateBomBuyPlanReq req) {
    return this.apsSchedulingGoodsBomTotalService.createBomBuyPlan(req);
  }
}
