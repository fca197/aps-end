package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsOrderGoodsApi;
import com.olivia.peanut.aps.api.entity.apsOrderGoods.*;
import com.olivia.peanut.aps.api.impl.listener.ApsOrderGoodsImportListener;
import com.olivia.peanut.aps.model.ApsOrderGoods;
import com.olivia.peanut.aps.service.ApsOrderGoodsService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsOrderGoods)表服务实现类
 *
 * @author peanut
 * @since 2024-04-09 13:19:36
 */
@RestController
public class ApsOrderGoodsApiImpl implements ApsOrderGoodsApi {

  private @Autowired ApsOrderGoodsService apsOrderGoodsService;

  /****
   * insert
   *
   */
  public @Override ApsOrderGoodsInsertRes insert(ApsOrderGoodsInsertReq req) {
    this.apsOrderGoodsService.save($.copy(req, ApsOrderGoods.class));
    return new ApsOrderGoodsInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsOrderGoodsDeleteByIdListRes deleteByIdList(ApsOrderGoodsDeleteByIdListReq req) {
    apsOrderGoodsService.removeByIds(req.getIdList());
    return new ApsOrderGoodsDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsOrderGoodsQueryListRes queryList(ApsOrderGoodsQueryListReq req) {
    return apsOrderGoodsService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsOrderGoodsUpdateByIdRes updateById(ApsOrderGoodsUpdateByIdReq req) {
    apsOrderGoodsService.updateById($.copy(req, ApsOrderGoods.class));
    return new ApsOrderGoodsUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsOrderGoodsExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsExportQueryPageListReq req) {
    return apsOrderGoodsService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsOrderGoodsExportQueryPageListReq req) {
    DynamicsPage<ApsOrderGoodsExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsOrderGoodsExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsOrderGoodsExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsOrderGoodsExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsOrderGoodsExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsOrderGoodsImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsOrderGoodsImportReq> reqList = PoiExcelUtil.readData(file, new ApsOrderGoodsImportListener(), ApsOrderGoodsImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsOrderGoods> readList = $.copyList(reqList, ApsOrderGoods.class);
    boolean bool = apsOrderGoodsService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsOrderGoodsImportRes().setCount(c);
  }

  public @Override ApsOrderGoodsQueryByIdListRes queryByIdListRes(ApsOrderGoodsQueryByIdListReq req) {
    MPJLambdaWrapper<ApsOrderGoods> q = new MPJLambdaWrapper<ApsOrderGoods>(ApsOrderGoods.class)
        .selectAll(ApsOrderGoods.class).in(ApsOrderGoods::getId, req.getIdList());
    List<ApsOrderGoods> list = this.apsOrderGoodsService.list(q);
    List<ApsOrderGoodsDto> dataList = $.copyList(list, ApsOrderGoodsDto.class);
    this.apsOrderGoodsService.setName(dataList);
    return new ApsOrderGoodsQueryByIdListRes().setDataList(dataList);
  }
}
