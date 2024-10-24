package com.olivia.peanut.base.api.impl;

import java.time.LocalDateTime;

import com.olivia.peanut.base.model.BaseUserRoleGroup;
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
import com.olivia.peanut.base.api.entity.baseUserRoleGroup.*;
import com.olivia.peanut.base.service.BaseUserRoleGroupService;
import com.olivia.peanut.base.model.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.base.api.BaseUserRoleGroupApi;

import com.olivia.peanut.base.api.impl.listener.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户角色组表(BaseUserRoleGroup)表服务实现类
 *
 * @author peanut
 * @since 2024-07-31 14:36:03
 */
@RestController
public class BaseUserRoleGroupApiImpl implements BaseUserRoleGroupApi {

  private @Autowired BaseUserRoleGroupService baseUserRoleGroupService;

  /****
   * insert
   *
   */
  public @Override BaseUserRoleGroupInsertRes insert(BaseUserRoleGroupInsertReq req) {
    this.baseUserRoleGroupService.save($.copy(req, BaseUserRoleGroup.class));
    return new BaseUserRoleGroupInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BaseUserRoleGroupDeleteByIdListRes deleteByIdList(BaseUserRoleGroupDeleteByIdListReq req) {
    baseUserRoleGroupService.removeByIds(req.getIdList());
    return new BaseUserRoleGroupDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BaseUserRoleGroupQueryListRes queryList(BaseUserRoleGroupQueryListReq req) {
    return baseUserRoleGroupService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BaseUserRoleGroupUpdateByIdRes updateById(BaseUserRoleGroupUpdateByIdReq req) {
    baseUserRoleGroupService.updateById($.copy(req, BaseUserRoleGroup.class));
    return new BaseUserRoleGroupUpdateByIdRes();

  }

  public @Override DynamicsPage<BaseUserRoleGroupExportQueryPageListInfoRes> queryPageList(BaseUserRoleGroupExportQueryPageListReq req) {
    return baseUserRoleGroupService.queryPageList(req);
  }

  public @Override void queryPageListExport(BaseUserRoleGroupExportQueryPageListReq req) {
    DynamicsPage<BaseUserRoleGroupExportQueryPageListInfoRes> page = queryPageList(req);
    List<BaseUserRoleGroupExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BaseUserRoleGroupExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BaseUserRoleGroupExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BaseUserRoleGroupExportQueryPageListInfoRes.class, listInfoRes, "用户角色组表");
  }

  public @Override BaseUserRoleGroupImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BaseUserRoleGroupImportReq> reqList = PoiExcelUtil.readData(file, new BaseUserRoleGroupImportListener(), BaseUserRoleGroupImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BaseUserRoleGroup> readList = $.copyList(reqList, BaseUserRoleGroup.class);
    boolean bool = baseUserRoleGroupService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BaseUserRoleGroupImportRes().setCount(c);
  }

  public @Override BaseUserRoleGroupQueryByIdListRes queryByIdListRes(BaseUserRoleGroupQueryByIdListReq req) {
    MPJLambdaWrapper<BaseUserRoleGroup> q = new MPJLambdaWrapper<BaseUserRoleGroup>(BaseUserRoleGroup.class)
        .selectAll(BaseUserRoleGroup.class).in(BaseUserRoleGroup::getId, req.getIdList());
    List<BaseUserRoleGroup> list = this.baseUserRoleGroupService.list(q);
    List<BaseUserRoleGroupDto> dataList = $.copyList(list, BaseUserRoleGroupDto.class);
    this.baseUserRoleGroupService.setName(dataList);
    return new BaseUserRoleGroupQueryByIdListRes().setDataList(dataList);
  }
}
