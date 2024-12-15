package com.olivia.peanut.aps.api.impl;

import java.time.LocalDateTime;

import com.olivia.peanut.aps.model.ApsBomSupplier;
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
import com.olivia.peanut.aps.api.entity.apsBomSupplier.*;
import com.olivia.peanut.aps.service.ApsBomSupplierService;
import com.olivia.peanut.aps.model.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.aps.api.ApsBomSupplierApi;

import com.olivia.peanut.aps.api.impl.listener.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 供应商表(ApsBomSupplier)表服务实现类
 *
 * @author makejava
 * @since 2024-12-15 14:39:43
 */
@RestController
public class ApsBomSupplierApiImpl implements ApsBomSupplierApi {

  private @Autowired ApsBomSupplierService apsBomSupplierService;

  /****
   * insert
   *
   */
  public @Override ApsBomSupplierInsertRes insert(ApsBomSupplierInsertReq req) {
    this.apsBomSupplierService.save($.copy(req, ApsBomSupplier.class));
    return new ApsBomSupplierInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsBomSupplierDeleteByIdListRes deleteByIdList(ApsBomSupplierDeleteByIdListReq req) {
    apsBomSupplierService.removeByIds(req.getIdList());
    return new ApsBomSupplierDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsBomSupplierQueryListRes queryList(ApsBomSupplierQueryListReq req) {
    return apsBomSupplierService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsBomSupplierUpdateByIdRes updateById(ApsBomSupplierUpdateByIdReq req) {
    apsBomSupplierService.updateById($.copy(req, ApsBomSupplier.class));
    return new ApsBomSupplierUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsBomSupplierExportQueryPageListInfoRes> queryPageList(ApsBomSupplierExportQueryPageListReq req) {
    return apsBomSupplierService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsBomSupplierExportQueryPageListReq req) {
    DynamicsPage<ApsBomSupplierExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsBomSupplierExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsBomSupplierExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsBomSupplierExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsBomSupplierExportQueryPageListInfoRes.class, listInfoRes, "供应商表");
  }

  public @Override ApsBomSupplierImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsBomSupplierImportReq> reqList = PoiExcelUtil.readData(file, new ApsBomSupplierImportListener(), ApsBomSupplierImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsBomSupplier> readList = $.copyList(reqList, ApsBomSupplier.class);
    boolean bool = apsBomSupplierService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsBomSupplierImportRes().setCount(c);
  }

  public @Override ApsBomSupplierQueryByIdListRes queryByIdListRes(ApsBomSupplierQueryByIdListReq req) {
    MPJLambdaWrapper<ApsBomSupplier> q = new MPJLambdaWrapper<ApsBomSupplier>(ApsBomSupplier.class)
        .selectAll(ApsBomSupplier.class).in(ApsBomSupplier::getId, req.getIdList());
    List<ApsBomSupplier> list = this.apsBomSupplierService.list(q);
    List<ApsBomSupplierDto> dataList = $.copyList(list, ApsBomSupplierDto.class);
    this.apsBomSupplierService.setName(dataList);
    return new ApsBomSupplierQueryByIdListRes().setDataList(dataList);
  }
}
