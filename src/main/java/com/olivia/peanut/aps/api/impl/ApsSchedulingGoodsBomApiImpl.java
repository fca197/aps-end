package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsSchedulingGoodsBomApi;
import com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBom.*;
import com.olivia.peanut.aps.api.impl.listener.ApsSchedulingGoodsBomImportListener;
import com.olivia.peanut.aps.model.ApsSchedulingGoodsBom;
import com.olivia.peanut.aps.service.ApsSchedulingGoodsBomService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 订单商品零件表(ApsSchedulingGoodsBom)表服务实现类
 *
 * @author peanut
 * @since 2024-06-02 21:50:24
 */
@RestController
public class ApsSchedulingGoodsBomApiImpl implements ApsSchedulingGoodsBomApi {

  private @Autowired ApsSchedulingGoodsBomService apsSchedulingGoodsBomService;

  /****
   * insert
   *
   */
  public @Override ApsSchedulingGoodsBomInsertRes insert(ApsSchedulingGoodsBomInsertReq req) {
    this.apsSchedulingGoodsBomService.save($.copy(req, ApsSchedulingGoodsBom.class));
    return new ApsSchedulingGoodsBomInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSchedulingGoodsBomDeleteByIdListRes deleteByIdList(ApsSchedulingGoodsBomDeleteByIdListReq req) {
    apsSchedulingGoodsBomService.removeByIds(req.getIdList());
    return new ApsSchedulingGoodsBomDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSchedulingGoodsBomQueryListRes queryList(ApsSchedulingGoodsBomQueryListReq req) {
    return apsSchedulingGoodsBomService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSchedulingGoodsBomUpdateByIdRes updateById(ApsSchedulingGoodsBomUpdateByIdReq req) {
    apsSchedulingGoodsBomService.updateById($.copy(req, ApsSchedulingGoodsBom.class));
    return new ApsSchedulingGoodsBomUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsSchedulingGoodsBomExportQueryPageListInfoRes> queryPageList(ApsSchedulingGoodsBomExportQueryPageListReq req) {
    return apsSchedulingGoodsBomService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSchedulingGoodsBomExportQueryPageListReq req) {
    DynamicsPage<ApsSchedulingGoodsBomExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSchedulingGoodsBomExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingGoodsBomExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSchedulingGoodsBomExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSchedulingGoodsBomExportQueryPageListInfoRes.class, listInfoRes, "订单商品零件表");
  }

  public @Override ApsSchedulingGoodsBomImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSchedulingGoodsBomImportReq> reqList = PoiExcelUtil.readData(file, new ApsSchedulingGoodsBomImportListener(), ApsSchedulingGoodsBomImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingGoodsBom> readList = $.copyList(reqList, ApsSchedulingGoodsBom.class);
    boolean bool = apsSchedulingGoodsBomService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSchedulingGoodsBomImportRes().setCount(c);
  }

  public @Override ApsSchedulingGoodsBomQueryByIdListRes queryByIdListRes(ApsSchedulingGoodsBomQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSchedulingGoodsBom> q = new MPJLambdaWrapper<ApsSchedulingGoodsBom>(ApsSchedulingGoodsBom.class)
        .selectAll(ApsSchedulingGoodsBom.class).in(ApsSchedulingGoodsBom::getId, req.getIdList());
    List<ApsSchedulingGoodsBom> list = this.apsSchedulingGoodsBomService.list(q);
    List<ApsSchedulingGoodsBomDto> dataList = $.copyList(list, ApsSchedulingGoodsBomDto.class);
    this.apsSchedulingGoodsBomService.setName(dataList);
    return new ApsSchedulingGoodsBomQueryByIdListRes().setDataList(dataList);
  }
}
