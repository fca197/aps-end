package com.olivia.peanut.aps.api.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.olivia.peanut.aps.model.ApsOrderGoodsSaleHistory;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;

import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory.*;
import com.olivia.peanut.aps.service.ApsOrderGoodsSaleHistoryService;
import com.olivia.peanut.aps.model.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.aps.api.ApsOrderGoodsSaleHistoryApi;

import com.olivia.peanut.aps.api.impl.listener.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 销售规划订单历史销售占比(ApsOrderGoodsSaleHistory)表服务实现类
 *
 * @author makejava
 * @since 2025-02-18 14:28:40
 */
@RestController
public class ApsOrderGoodsSaleHistoryApiImpl implements ApsOrderGoodsSaleHistoryApi {

  private @Autowired ApsOrderGoodsSaleHistoryService apsOrderGoodsSaleHistoryService;

  /****
   * insert
   *
   */
  public @Override ApsOrderGoodsSaleHistoryInsertRes insert(ApsOrderGoodsSaleHistoryInsertReq req) {
    this.apsOrderGoodsSaleHistoryService.save($.copy(req, ApsOrderGoodsSaleHistory.class));
    return new ApsOrderGoodsSaleHistoryInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsOrderGoodsSaleHistoryDeleteByIdListRes deleteByIdList(ApsOrderGoodsSaleHistoryDeleteByIdListReq req) {
    apsOrderGoodsSaleHistoryService.removeByIds(req.getIdList());
    return new ApsOrderGoodsSaleHistoryDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsOrderGoodsSaleHistoryQueryListRes queryList(ApsOrderGoodsSaleHistoryQueryListReq req) {
    return apsOrderGoodsSaleHistoryService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsOrderGoodsSaleHistoryUpdateByIdRes updateById(ApsOrderGoodsSaleHistoryUpdateByIdReq req) {
    apsOrderGoodsSaleHistoryService.updateById($.copy(req, ApsOrderGoodsSaleHistory.class));
    return new ApsOrderGoodsSaleHistoryUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsOrderGoodsSaleHistoryExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsSaleHistoryExportQueryPageListReq req) {
    return apsOrderGoodsSaleHistoryService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsOrderGoodsSaleHistoryExportQueryPageListReq req) {
    DynamicsPage<ApsOrderGoodsSaleHistoryExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsOrderGoodsSaleHistoryExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsOrderGoodsSaleHistoryExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsOrderGoodsSaleHistoryExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsOrderGoodsSaleHistoryExportQueryPageListInfoRes.class, listInfoRes, "销售规划订单历史销售占比");
  }

  public @Override ApsOrderGoodsSaleHistoryImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsOrderGoodsSaleHistoryImportReq> reqList = PoiExcelUtil.readData(file, new ApsOrderGoodsSaleHistoryImportListener(), ApsOrderGoodsSaleHistoryImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsOrderGoodsSaleHistory> readList = $.copyList(reqList, ApsOrderGoodsSaleHistory.class);
    boolean bool = apsOrderGoodsSaleHistoryService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsOrderGoodsSaleHistoryImportRes().setCount(c);
  }

  public @Override ApsOrderGoodsSaleHistoryQueryByIdListRes queryByIdListRes(ApsOrderGoodsSaleHistoryQueryByIdListReq req) {
    MPJLambdaWrapper<ApsOrderGoodsSaleHistory> q = new MPJLambdaWrapper<ApsOrderGoodsSaleHistory>(ApsOrderGoodsSaleHistory.class)
        .selectAll(ApsOrderGoodsSaleHistory.class).in(ApsOrderGoodsSaleHistory::getId, req.getIdList());
    List<ApsOrderGoodsSaleHistory> list = this.apsOrderGoodsSaleHistoryService.list(q);
    List<ApsOrderGoodsSaleHistoryDto> dataList = $.copyList(list, ApsOrderGoodsSaleHistoryDto.class);
    this.apsOrderGoodsSaleHistoryService.setName(dataList);
    return new ApsOrderGoodsSaleHistoryQueryByIdListRes().setDataList(dataList);
  }
}
