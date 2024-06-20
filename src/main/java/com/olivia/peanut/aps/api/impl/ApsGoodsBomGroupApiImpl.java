package com.olivia.peanut.aps.api.impl;

import java.time.LocalDateTime;

import com.olivia.peanut.aps.model.ApsGoodsBomGroup;
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
import com.olivia.peanut.aps.api.entity.apsGoodsBomGroup.*;
import com.olivia.peanut.aps.service.ApsGoodsBomGroupService;
import com.olivia.peanut.aps.model.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.aps.api.ApsGoodsBomGroupApi;

import com.olivia.peanut.aps.api.impl.listener.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 零件组配置(ApsGoodsBomGroup)表服务实现类
 *
 * @author peanut
 * @since 2024-06-19 16:49:03
 */
@RestController
public class ApsGoodsBomGroupApiImpl implements ApsGoodsBomGroupApi {

  private @Autowired ApsGoodsBomGroupService apsGoodsBomGroupService;

  /****
   * insert
   *
   */
  public @Override ApsGoodsBomGroupInsertRes insert(ApsGoodsBomGroupInsertReq req) {
    this.apsGoodsBomGroupService.save($.copy(req, ApsGoodsBomGroup.class));
    return new ApsGoodsBomGroupInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsGoodsBomGroupDeleteByIdListRes deleteByIdList(ApsGoodsBomGroupDeleteByIdListReq req) {
    apsGoodsBomGroupService.removeByIds(req.getIdList());
    return new ApsGoodsBomGroupDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsBomGroupQueryListRes queryList(ApsGoodsBomGroupQueryListReq req) {
    return apsGoodsBomGroupService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsBomGroupUpdateByIdRes updateById(ApsGoodsBomGroupUpdateByIdReq req) {
    apsGoodsBomGroupService.updateById($.copy(req, ApsGoodsBomGroup.class));
    return new ApsGoodsBomGroupUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsBomGroupExportQueryPageListInfoRes> queryPageList(ApsGoodsBomGroupExportQueryPageListReq req) {
    return apsGoodsBomGroupService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsBomGroupExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsBomGroupExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsBomGroupExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsBomGroupExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsBomGroupExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsBomGroupExportQueryPageListInfoRes.class, listInfoRes, "零件组配置");
  }

  public @Override ApsGoodsBomGroupImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsGoodsBomGroupImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsBomGroupImportListener(), ApsGoodsBomGroupImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsBomGroup> readList = $.copyList(reqList, ApsGoodsBomGroup.class);
    boolean bool = apsGoodsBomGroupService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsBomGroupImportRes().setCount(c);
  }

  public @Override ApsGoodsBomGroupQueryByIdListRes queryByIdListRes(ApsGoodsBomGroupQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoodsBomGroup> q = new MPJLambdaWrapper<ApsGoodsBomGroup>(ApsGoodsBomGroup.class)
        .selectAll(ApsGoodsBomGroup.class).in(ApsGoodsBomGroup::getId, req.getIdList());
    List<ApsGoodsBomGroup> list = this.apsGoodsBomGroupService.list(q);
    List<ApsGoodsBomGroupDto> dataList = $.copyList(list, ApsGoodsBomGroupDto.class);
    this.apsGoodsBomGroupService.setName(dataList);
    return new ApsGoodsBomGroupQueryByIdListRes().setDataList(dataList);
  }
}
