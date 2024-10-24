package com.olivia.peanut.aps.api.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.olivia.peanut.aps.model.ApsOrderGoodsBom;
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
import com.olivia.peanut.aps.api.entity.apsOrderGoodsBom.*;
import com.olivia.peanut.aps.service.ApsOrderGoodsBomService;
import com.olivia.peanut.aps.model.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.aps.api.ApsOrderGoodsBomApi;

import com.olivia.peanut.aps.api.impl.listener.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 订单商品零件表(ApsOrderGoodsBom)表服务实现类
 *
 * @author peanut
 * @since 2024-07-30 10:28:23
 */
@RestController
public class ApsOrderGoodsBomApiImpl implements ApsOrderGoodsBomApi {

  private @Autowired ApsOrderGoodsBomService apsOrderGoodsBomService;

  /****
   * insert
   *
   */
  public @Override ApsOrderGoodsBomInsertRes insert(ApsOrderGoodsBomInsertReq req) {
    this.apsOrderGoodsBomService.save($.copy(req, ApsOrderGoodsBom.class));
    return new ApsOrderGoodsBomInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsOrderGoodsBomDeleteByIdListRes deleteByIdList(ApsOrderGoodsBomDeleteByIdListReq req) {
    apsOrderGoodsBomService.removeByIds(req.getIdList());
    return new ApsOrderGoodsBomDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsOrderGoodsBomQueryListRes queryList(ApsOrderGoodsBomQueryListReq req) {
    return apsOrderGoodsBomService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsOrderGoodsBomUpdateByIdRes updateById(ApsOrderGoodsBomUpdateByIdReq req) {
    apsOrderGoodsBomService.updateById($.copy(req, ApsOrderGoodsBom.class));
    return new ApsOrderGoodsBomUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsOrderGoodsBomExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsBomExportQueryPageListReq req) {
    return apsOrderGoodsBomService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsOrderGoodsBomExportQueryPageListReq req) {
    DynamicsPage<ApsOrderGoodsBomExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsOrderGoodsBomExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsOrderGoodsBomExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsOrderGoodsBomExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsOrderGoodsBomExportQueryPageListInfoRes.class, listInfoRes, "订单商品零件表");
  }

  public @Override ApsOrderGoodsBomImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsOrderGoodsBomImportReq> reqList = PoiExcelUtil.readData(file, new ApsOrderGoodsBomImportListener(), ApsOrderGoodsBomImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsOrderGoodsBom> readList = $.copyList(reqList, ApsOrderGoodsBom.class);
    boolean bool = apsOrderGoodsBomService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsOrderGoodsBomImportRes().setCount(c);
  }

  public @Override ApsOrderGoodsBomQueryByIdListRes queryByIdListRes(ApsOrderGoodsBomQueryByIdListReq req) {
    MPJLambdaWrapper<ApsOrderGoodsBom> q = new MPJLambdaWrapper<ApsOrderGoodsBom>(ApsOrderGoodsBom.class)
        .selectAll(ApsOrderGoodsBom.class).in(ApsOrderGoodsBom::getId, req.getIdList());
    List<ApsOrderGoodsBom> list = this.apsOrderGoodsBomService.list(q);
    List<ApsOrderGoodsBomDto> dataList = $.copyList(list, ApsOrderGoodsBomDto.class);
    this.apsOrderGoodsBomService.setName(dataList);
    return new ApsOrderGoodsBomQueryByIdListRes().setDataList(dataList);
  }
}
