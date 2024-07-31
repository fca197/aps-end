package com.olivia.peanut.base.api.impl;

import java.time.LocalDateTime;

import com.olivia.peanut.base.model.BaseRoleGroup;
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
import com.olivia.peanut.base.api.entity.baseRoleGroup.*;
import com.olivia.peanut.base.service.BaseRoleGroupService;
import com.olivia.peanut.base.model.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.base.api.BaseRoleGroupApi;

import com.olivia.peanut.base.api.impl.listener.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 角色组表(BaseRoleGroup)表服务实现类
 *
 * @author peanut
 * @since 2024-07-31 14:33:36
 */
@RestController
public class BaseRoleGroupApiImpl implements BaseRoleGroupApi {

  private @Autowired BaseRoleGroupService baseRoleGroupService;

  /****
   * insert
   *
   */
  public @Override BaseRoleGroupInsertRes insert(BaseRoleGroupInsertReq req) {
    this.baseRoleGroupService.save($.copy(req, BaseRoleGroup.class));
    return new BaseRoleGroupInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BaseRoleGroupDeleteByIdListRes deleteByIdList(BaseRoleGroupDeleteByIdListReq req) {
    baseRoleGroupService.removeByIds(req.getIdList());
    return new BaseRoleGroupDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BaseRoleGroupQueryListRes queryList(BaseRoleGroupQueryListReq req) {
    return baseRoleGroupService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BaseRoleGroupUpdateByIdRes updateById(BaseRoleGroupUpdateByIdReq req) {
    baseRoleGroupService.updateById($.copy(req, BaseRoleGroup.class));
    return new BaseRoleGroupUpdateByIdRes();

  }

  public @Override DynamicsPage<BaseRoleGroupExportQueryPageListInfoRes> queryPageList(BaseRoleGroupExportQueryPageListReq req) {
    return baseRoleGroupService.queryPageList(req);
  }

  public @Override void queryPageListExport(BaseRoleGroupExportQueryPageListReq req) {
    DynamicsPage<BaseRoleGroupExportQueryPageListInfoRes> page = queryPageList(req);
    List<BaseRoleGroupExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BaseRoleGroupExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BaseRoleGroupExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BaseRoleGroupExportQueryPageListInfoRes.class, listInfoRes, "角色组表");
  }

  public @Override BaseRoleGroupImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BaseRoleGroupImportReq> reqList = PoiExcelUtil.readData(file, new BaseRoleGroupImportListener(), BaseRoleGroupImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BaseRoleGroup> readList = $.copyList(reqList, BaseRoleGroup.class);
    boolean bool = baseRoleGroupService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BaseRoleGroupImportRes().setCount(c);
  }

  public @Override BaseRoleGroupQueryByIdListRes queryByIdListRes(BaseRoleGroupQueryByIdListReq req) {
    MPJLambdaWrapper<BaseRoleGroup> q = new MPJLambdaWrapper<BaseRoleGroup>(BaseRoleGroup.class)
        .selectAll(BaseRoleGroup.class).in(BaseRoleGroup::getId, req.getIdList());
    List<BaseRoleGroup> list = this.baseRoleGroupService.list(q);
    List<BaseRoleGroupDto> dataList = $.copyList(list, BaseRoleGroupDto.class);
    this.baseRoleGroupService.setName(dataList);
    return new BaseRoleGroupQueryByIdListRes().setDataList(dataList);
  }
}
