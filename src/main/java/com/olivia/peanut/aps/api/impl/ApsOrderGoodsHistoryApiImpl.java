package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsOrderGoodsHistoryApi;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsHistory.*;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory.SelectOrder2HistoryReq;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory.SelectOrder2HistoryRes;
import com.olivia.peanut.aps.api.impl.listener.ApsOrderGoodsHistoryImportListener;
import com.olivia.peanut.aps.model.ApsOrderGoodsHistory;
import com.olivia.peanut.aps.service.ApsOrderGoodsHistoryService;
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
 * 历史订单记录(ApsOrderGoodsHistory)表服务实现类
 *
 * @author makejava
 * @since 2025-02-20 17:18:46
 */
@RestController
public class ApsOrderGoodsHistoryApiImpl implements ApsOrderGoodsHistoryApi {

  private @Autowired ApsOrderGoodsHistoryService apsOrderGoodsHistoryService;

  /****
   * insert
   *
   */
  public @Override ApsOrderGoodsHistoryInsertRes insert(ApsOrderGoodsHistoryInsertReq req) {
    RunUtils.noImpl();
    this.apsOrderGoodsHistoryService.save($.copy(req, ApsOrderGoodsHistory.class));
    return new ApsOrderGoodsHistoryInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsOrderGoodsHistoryDeleteByIdListRes deleteByIdList(ApsOrderGoodsHistoryDeleteByIdListReq req) {
    RunUtils.noImpl();
    apsOrderGoodsHistoryService.removeByIds(req.getIdList());
    return new ApsOrderGoodsHistoryDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsOrderGoodsHistoryQueryListRes queryList(ApsOrderGoodsHistoryQueryListReq req) {
    return apsOrderGoodsHistoryService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsOrderGoodsHistoryUpdateByIdRes updateById(ApsOrderGoodsHistoryUpdateByIdReq req) {
    RunUtils.noImpl();
    apsOrderGoodsHistoryService.updateById($.copy(req, ApsOrderGoodsHistory.class));
    return new ApsOrderGoodsHistoryUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsOrderGoodsHistoryExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsHistoryExportQueryPageListReq req) {
    return apsOrderGoodsHistoryService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsOrderGoodsHistoryExportQueryPageListReq req) {
    DynamicsPage<ApsOrderGoodsHistoryExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsOrderGoodsHistoryExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsOrderGoodsHistoryExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsOrderGoodsHistoryExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsOrderGoodsHistoryExportQueryPageListInfoRes.class, listInfoRes, "历史订单记录");
  }

  public @Override ApsOrderGoodsHistoryImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsOrderGoodsHistoryImportReq> reqList = PoiExcelUtil.readData(file, new ApsOrderGoodsHistoryImportListener(), ApsOrderGoodsHistoryImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsOrderGoodsHistory> readList = $.copyList(reqList, ApsOrderGoodsHistory.class);
    boolean bool = apsOrderGoodsHistoryService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsOrderGoodsHistoryImportRes().setCount(c);
  }

  public @Override ApsOrderGoodsHistoryQueryByIdListRes queryByIdListRes(ApsOrderGoodsHistoryQueryByIdListReq req) {
    MPJLambdaWrapper<ApsOrderGoodsHistory> q = new MPJLambdaWrapper<ApsOrderGoodsHistory>(ApsOrderGoodsHistory.class)
        .selectAll(ApsOrderGoodsHistory.class).in(ApsOrderGoodsHistory::getId, req.getIdList());
    List<ApsOrderGoodsHistory> list = this.apsOrderGoodsHistoryService.list(q);
    List<ApsOrderGoodsHistoryDto> dataList = $.copyList(list, ApsOrderGoodsHistoryDto.class);
    this.apsOrderGoodsHistoryService.setName(dataList);
    return new ApsOrderGoodsHistoryQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public SelectOrder2HistoryRes selectOrder2History(SelectOrder2HistoryReq req) {
    return this.apsOrderGoodsHistoryService.selectOrder2History(req);
  }
}
