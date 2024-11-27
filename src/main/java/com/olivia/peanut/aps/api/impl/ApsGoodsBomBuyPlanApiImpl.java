package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsGoodsBomBuyPlanApi;
import com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlan.*;
import com.olivia.peanut.aps.api.impl.listener.ApsGoodsBomBuyPlanImportListener;
import com.olivia.peanut.aps.model.ApsGoodsBomBuyPlan;
import com.olivia.peanut.aps.service.ApsGoodsBomBuyPlanService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.olivia.sdk.utils.RunUtils.noImpl;

/**
 * BOM 购买计划(ApsGoodsBomBuyPlan)表服务实现类
 *
 * @author peanut
 * @since 2024-06-05 14:35:29
 */
@RestController
public class ApsGoodsBomBuyPlanApiImpl implements ApsGoodsBomBuyPlanApi {

  private @Autowired ApsGoodsBomBuyPlanService apsGoodsBomBuyPlanService;

  /****
   * insert
   *
   */
  public @Override ApsGoodsBomBuyPlanInsertRes insert(ApsGoodsBomBuyPlanInsertReq req) {
    this.apsGoodsBomBuyPlanService.save($.copy(req, ApsGoodsBomBuyPlan.class));
    return new ApsGoodsBomBuyPlanInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsGoodsBomBuyPlanDeleteByIdListRes deleteByIdList(ApsGoodsBomBuyPlanDeleteByIdListReq req) {
    apsGoodsBomBuyPlanService.removeByIds(req.getIdList());
    return new ApsGoodsBomBuyPlanDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsBomBuyPlanQueryListRes queryList(ApsGoodsBomBuyPlanQueryListReq req) {
    return apsGoodsBomBuyPlanService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsBomBuyPlanUpdateByIdRes updateById(ApsGoodsBomBuyPlanUpdateByIdReq req) {
    apsGoodsBomBuyPlanService.updateById($.copy(req, ApsGoodsBomBuyPlan.class));
    return new ApsGoodsBomBuyPlanUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsBomBuyPlanExportQueryPageListInfoRes> queryPageList(ApsGoodsBomBuyPlanExportQueryPageListReq req) {
    return apsGoodsBomBuyPlanService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsBomBuyPlanExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsBomBuyPlanExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsBomBuyPlanExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsBomBuyPlanExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsBomBuyPlanExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsBomBuyPlanExportQueryPageListInfoRes.class, listInfoRes, "BOM 购买计划");
  }

  public @Override ApsGoodsBomBuyPlanImportRes importData(@RequestParam("file") MultipartFile file) {
    noImpl();
    List<ApsGoodsBomBuyPlanImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsBomBuyPlanImportListener(), ApsGoodsBomBuyPlanImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsBomBuyPlan> readList = $.copyList(reqList, ApsGoodsBomBuyPlan.class);
    boolean bool = apsGoodsBomBuyPlanService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsBomBuyPlanImportRes().setCount(c);
  }

  public @Override ApsGoodsBomBuyPlanQueryByIdListRes queryByIdListRes(ApsGoodsBomBuyPlanQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoodsBomBuyPlan> q = new MPJLambdaWrapper<ApsGoodsBomBuyPlan>(ApsGoodsBomBuyPlan.class)
        .selectAll(ApsGoodsBomBuyPlan.class).in(ApsGoodsBomBuyPlan::getId, req.getIdList());
    List<ApsGoodsBomBuyPlan> list = this.apsGoodsBomBuyPlanService.list(q);
    List<ApsGoodsBomBuyPlanDto> dataList = $.copyList(list, ApsGoodsBomBuyPlanDto.class);
    this.apsGoodsBomBuyPlanService.setName(dataList);
    return new ApsGoodsBomBuyPlanQueryByIdListRes().setDataList(dataList);
  }
}
