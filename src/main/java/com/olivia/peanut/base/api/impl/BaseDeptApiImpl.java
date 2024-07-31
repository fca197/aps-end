package com.olivia.peanut.base.api.impl;

import java.time.LocalDateTime;

import com.olivia.peanut.base.model.BaseDept;
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
import com.olivia.peanut.base.api.entity.baseDept.*;
import com.olivia.peanut.base.service.BaseDeptService;
import com.olivia.peanut.base.model.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.base.api.BaseDeptApi;

import com.olivia.peanut.base.api.impl.listener.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 部门表(BaseDept)表服务实现类
 *
 * @author peanut
 * @since 2024-07-31 14:33:30
 */
@RestController
public class BaseDeptApiImpl implements BaseDeptApi {

  private @Autowired BaseDeptService baseDeptService;

  /****
   * insert
   *
   */
  public @Override BaseDeptInsertRes insert(BaseDeptInsertReq req) {
    this.baseDeptService.save($.copy(req, BaseDept.class));
    return new BaseDeptInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BaseDeptDeleteByIdListRes deleteByIdList(BaseDeptDeleteByIdListReq req) {
    baseDeptService.removeByIds(req.getIdList());
    return new BaseDeptDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BaseDeptQueryListRes queryList(BaseDeptQueryListReq req) {
    return baseDeptService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BaseDeptUpdateByIdRes updateById(BaseDeptUpdateByIdReq req) {
    baseDeptService.updateById($.copy(req, BaseDept.class));
    return new BaseDeptUpdateByIdRes();

  }

  public @Override DynamicsPage<BaseDeptExportQueryPageListInfoRes> queryPageList(BaseDeptExportQueryPageListReq req) {
    return baseDeptService.queryPageList(req);
  }

  public @Override void queryPageListExport(BaseDeptExportQueryPageListReq req) {
    DynamicsPage<BaseDeptExportQueryPageListInfoRes> page = queryPageList(req);
    List<BaseDeptExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BaseDeptExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BaseDeptExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BaseDeptExportQueryPageListInfoRes.class, listInfoRes, "部门表");
  }

  public @Override BaseDeptImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BaseDeptImportReq> reqList = PoiExcelUtil.readData(file, new BaseDeptImportListener(), BaseDeptImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BaseDept> readList = $.copyList(reqList, BaseDept.class);
    boolean bool = baseDeptService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BaseDeptImportRes().setCount(c);
  }

  public @Override BaseDeptQueryByIdListRes queryByIdListRes(BaseDeptQueryByIdListReq req) {
    MPJLambdaWrapper<BaseDept> q = new MPJLambdaWrapper<BaseDept>(BaseDept.class)
        .selectAll(BaseDept.class).in(BaseDept::getId, req.getIdList());
    List<BaseDept> list = this.baseDeptService.list(q);
    List<BaseDeptDto> dataList = $.copyList(list, BaseDeptDto.class);
    this.baseDeptService.setName(dataList);
    return new BaseDeptQueryByIdListRes().setDataList(dataList);
  }
}
