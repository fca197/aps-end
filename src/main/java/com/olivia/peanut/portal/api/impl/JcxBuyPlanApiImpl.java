package com.olivia.peanut.portal.api.impl;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.JcxBuyPlanApi;
import com.olivia.peanut.portal.api.entity.jcxBuyPlan.*;
import com.olivia.peanut.portal.api.impl.listener.JcxBuyPlanImportListener;
import com.olivia.peanut.portal.model.JcxBuyPlan;
import com.olivia.peanut.portal.service.JcxBuyPlanService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (JcxBuyPlan)表服务实现类
 *
 * @author peanut
 * @since 2024-03-24 20:27:10
 */
@RestController
public class JcxBuyPlanApiImpl implements JcxBuyPlanApi {

  private @Autowired JcxBuyPlanService jcxBuyPlanService;

  /****
   * insert
   *
   */
  public @Override JcxBuyPlanInsertRes insert(JcxBuyPlanInsertReq req) {
    return this.jcxBuyPlanService.save(req);
  }

  /****
   * deleteByIds
   *
   */
  public @Override JcxBuyPlanDeleteByIdListRes deleteByIdList(JcxBuyPlanDeleteByIdListReq req) {
    jcxBuyPlanService.removeByIds(req.getIdList());
    return new JcxBuyPlanDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override JcxBuyPlanQueryListRes queryList(JcxBuyPlanQueryListReq req) {
    return jcxBuyPlanService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override JcxBuyPlanUpdateByIdRes updateById(JcxBuyPlanUpdateByIdReq req) {
    jcxBuyPlanService.updateById(req);
    return new JcxBuyPlanUpdateByIdRes();

  }

  public @Override DynamicsPage<JcxBuyPlanExportQueryPageListInfoRes> queryPageList(JcxBuyPlanExportQueryPageListReq req) {
    return jcxBuyPlanService.queryPageList(req);
  }

  public @Override void queryPageListExport(JcxBuyPlanExportQueryPageListReq req) {
    DynamicsPage<JcxBuyPlanExportQueryPageListInfoRes> page = queryPageList(req);
    List<JcxBuyPlanExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<JcxBuyPlanExportQueryPageListInfoRes> listInfoRes = $.copyList(list, JcxBuyPlanExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(JcxBuyPlanExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override JcxBuyPlanImportRes importData(@RequestParam("file") MultipartFile file) {
    List<JcxBuyPlanImportReq> reqList = PoiExcelUtil.readData(file, new JcxBuyPlanImportListener(), JcxBuyPlanImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<JcxBuyPlan> readList = $.copyList(reqList, JcxBuyPlan.class);
    boolean bool = jcxBuyPlanService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new JcxBuyPlanImportRes().setCount(c);
  }

  public @Override JcxBuyPlanQueryByIdListRes queryByIdListRes(JcxBuyPlanQueryByIdListReq req) {
    MPJLambdaWrapper<JcxBuyPlan> q = new MPJLambdaWrapper<JcxBuyPlan>(JcxBuyPlan.class)
        .selectAll(JcxBuyPlan.class).in(JcxBuyPlan::getId, req.getIdList());
    List<JcxBuyPlan> list = this.jcxBuyPlanService.list(q);
    List<JcxBuyPlanDto> dataList = $.copyList(list, JcxBuyPlanDto.class);
    this.jcxBuyPlanService.setName(dataList);
    return new JcxBuyPlanQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public JcxBuyPlanUpdateStatusRes updateStatus(JcxBuyPlanUpdateStatusReq req) {
    boolean update = this.jcxBuyPlanService.update(new LambdaUpdateWrapper<JcxBuyPlan>().set(JcxBuyPlan::getPlanStatus, req.getPlanStatus())
        .eq(BaseEntity::getId, req.getId()).eq(BaseEntity::getVersionNum, req.getVersionNum()));
    $.requireNonNullCanIgnoreException(update, "更新失败");
    return new JcxBuyPlanUpdateStatusRes().setIsSuccess(update);
  }
}
