package com.olivia.peanut.aps.api.impl;

import java.time.LocalDateTime;

import com.olivia.peanut.aps.model.ApsProduceProcess;
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
import com.olivia.peanut.aps.api.entity.apsProduceProcess.*;
import com.olivia.peanut.aps.service.ApsProduceProcessService;
import com.olivia.peanut.aps.model.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.aps.api.ApsProduceProcessApi;

import com.olivia.peanut.aps.api.impl.listener.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * aps 生产路径(ApsProduceProcess)表服务实现类
 *
 * @author makejava
 * @since 2024-10-24 17:00:18
 */
@RestController
public class ApsProduceProcessApiImpl implements ApsProduceProcessApi {

  private @Autowired ApsProduceProcessService apsProduceProcessService;

  /****
   * insert
   *
   */
  public @Override ApsProduceProcessInsertRes insert(ApsProduceProcessInsertReq req) {
    this.apsProduceProcessService.save(req);
    return new ApsProduceProcessInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsProduceProcessDeleteByIdListRes deleteByIdList(ApsProduceProcessDeleteByIdListReq req) {
    apsProduceProcessService.removeByIds(req.getIdList());
    return new ApsProduceProcessDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsProduceProcessQueryListRes queryList(ApsProduceProcessQueryListReq req) {
    return apsProduceProcessService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsProduceProcessUpdateByIdRes updateById(ApsProduceProcessUpdateByIdReq req) {
    apsProduceProcessService.updateById(req);
    return new ApsProduceProcessUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsProduceProcessExportQueryPageListInfoRes> queryPageList(ApsProduceProcessExportQueryPageListReq req) {
    return apsProduceProcessService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsProduceProcessExportQueryPageListReq req) {
    DynamicsPage<ApsProduceProcessExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsProduceProcessExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsProduceProcessExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsProduceProcessExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsProduceProcessExportQueryPageListInfoRes.class, listInfoRes, "aps 生产路径");
  }

  public @Override ApsProduceProcessImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsProduceProcessImportReq> reqList = PoiExcelUtil.readData(file, new ApsProduceProcessImportListener(), ApsProduceProcessImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsProduceProcess> readList = $.copyList(reqList, ApsProduceProcess.class);
    boolean bool = apsProduceProcessService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsProduceProcessImportRes().setCount(c);
  }

  public @Override ApsProduceProcessQueryByIdListRes queryByIdListRes(ApsProduceProcessQueryByIdListReq req) {
    MPJLambdaWrapper<ApsProduceProcess> q = new MPJLambdaWrapper<ApsProduceProcess>(ApsProduceProcess.class)
        .selectAll(ApsProduceProcess.class).in(ApsProduceProcess::getId, req.getIdList());
    List<ApsProduceProcess> list = this.apsProduceProcessService.list(q);
    List<ApsProduceProcessDto> dataList = $.copyList(list, ApsProduceProcessDto.class);
    this.apsProduceProcessService.setName(dataList);
    return new ApsProduceProcessQueryByIdListRes().setDataList(dataList);
  }
}
