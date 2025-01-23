package com.olivia.peanut.aps.api.impl;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsGoodsApi;
import com.olivia.peanut.aps.api.entity.apsGoods.*;
import com.olivia.peanut.aps.api.impl.listener.ApsGoodsImportListener;
import com.olivia.peanut.aps.model.ApsGoods;
import com.olivia.peanut.aps.service.ApsGoodsService;
import com.olivia.sdk.ann.Oplog;
import com.olivia.sdk.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsGoods)表服务实现类
 *
 * @author peanut
 * @since 2024-03-29 16:11:22
 */
@RestController
public class ApsGoodsApiImpl implements ApsGoodsApi {

  private static final String businessType = "apsGoods";
  private @Autowired ApsGoodsService apsGoodsService;

  /****
   * insert
   *
   */
  @Oplog(content = "商品存储", businessKey = "#req.bomCode", businessType = businessType, paramName = "保存零件")

  public @Override ApsGoodsInsertRes insert(ApsGoodsInsertReq req) {
    $.assertNotAllNull("工艺路径与制造路径必须选择一种", req.getProcessPathId(), req.getProduceProcessId());
    this.apsGoodsService.save($.copy(req, ApsGoods.class));
    return new ApsGoodsInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  @Oplog(content = "商品存删除", businessKey = "#req.idList", businessType = businessType, paramName = "删除商品ID")
  public @Override ApsGoodsDeleteByIdListRes deleteByIdList(ApsGoodsDeleteByIdListReq req) {
    apsGoodsService.removeByIds(req.getIdList());
    return new ApsGoodsDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsQueryListRes queryList(ApsGoodsQueryListReq req) {
    return apsGoodsService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsUpdateByIdRes updateById(ApsGoodsUpdateByIdReq req) {
    $.assertNotAllNull("工艺路径与制造路径必须选择一种", req.getProcessPathId(), req.getProduceProcessId());
    apsGoodsService.updateById($.copy(req, ApsGoods.class));
    apsGoodsService.update(new LambdaUpdateWrapper<ApsGoods>().set(ApsGoods::getProcessPathId, req.getProcessPathId())
        .set(ApsGoods::getProduceProcessId, req.getProduceProcessId()).eq(BaseEntity::getId, req.getId()));
    return new ApsGoodsUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsExportQueryPageListInfoRes> queryPageList(ApsGoodsExportQueryPageListReq req) {
    return apsGoodsService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsGoodsImportRes importData(@RequestParam("file") MultipartFile file) {
    RunUtils.noImpl();
    List<ApsGoodsImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsImportListener(), ApsGoodsImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoods> readList = $.copyList(reqList, ApsGoods.class);
    boolean bool = apsGoodsService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsImportRes().setCount(c);
  }

  public @Override ApsGoodsQueryByIdListRes queryByIdListRes(ApsGoodsQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoods> q = new MPJLambdaWrapper<ApsGoods>(ApsGoods.class)
        .selectAll(ApsGoods.class).in(ApsGoods::getId, req.getIdList());
    List<ApsGoods> list = this.apsGoodsService.list(q);
    List<ApsGoodsDto> dataList = $.copyList(list, ApsGoodsDto.class);
    return new ApsGoodsQueryByIdListRes().setDataList(dataList);
  }
}
