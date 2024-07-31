package com.olivia.peanut.base.api.impl;

import java.time.LocalDateTime;

import com.olivia.peanut.base.model.BaseRoleResource;
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
import com.olivia.peanut.base.api.entity.baseRoleResource.*;
import com.olivia.peanut.base.service.BaseRoleResourceService;
import com.olivia.peanut.base.model.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.base.api.BaseRoleResourceApi;

import com.olivia.peanut.base.api.impl.listener.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 角色资源表(BaseRoleResource)表服务实现类
 *
 * @author peanut
 * @since 2024-07-31 14:34:06
 */
@RestController
public class BaseRoleResourceApiImpl implements BaseRoleResourceApi {

  private @Autowired BaseRoleResourceService baseRoleResourceService;

  /****
   * insert
   *
   */
  public @Override BaseRoleResourceInsertRes insert(BaseRoleResourceInsertReq req) {
    this.baseRoleResourceService.save($.copy(req, BaseRoleResource.class));
    return new BaseRoleResourceInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BaseRoleResourceDeleteByIdListRes deleteByIdList(BaseRoleResourceDeleteByIdListReq req) {
    baseRoleResourceService.removeByIds(req.getIdList());
    return new BaseRoleResourceDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BaseRoleResourceQueryListRes queryList(BaseRoleResourceQueryListReq req) {
    return baseRoleResourceService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BaseRoleResourceUpdateByIdRes updateById(BaseRoleResourceUpdateByIdReq req) {
    baseRoleResourceService.updateById($.copy(req, BaseRoleResource.class));
    return new BaseRoleResourceUpdateByIdRes();

  }

  public @Override DynamicsPage<BaseRoleResourceExportQueryPageListInfoRes> queryPageList(BaseRoleResourceExportQueryPageListReq req) {
    return baseRoleResourceService.queryPageList(req);
  }

  public @Override void queryPageListExport(BaseRoleResourceExportQueryPageListReq req) {
    DynamicsPage<BaseRoleResourceExportQueryPageListInfoRes> page = queryPageList(req);
    List<BaseRoleResourceExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BaseRoleResourceExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BaseRoleResourceExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BaseRoleResourceExportQueryPageListInfoRes.class, listInfoRes, "角色资源表");
  }

  public @Override BaseRoleResourceImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BaseRoleResourceImportReq> reqList = PoiExcelUtil.readData(file, new BaseRoleResourceImportListener(), BaseRoleResourceImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BaseRoleResource> readList = $.copyList(reqList, BaseRoleResource.class);
    boolean bool = baseRoleResourceService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BaseRoleResourceImportRes().setCount(c);
  }

  public @Override BaseRoleResourceQueryByIdListRes queryByIdListRes(BaseRoleResourceQueryByIdListReq req) {
    MPJLambdaWrapper<BaseRoleResource> q = new MPJLambdaWrapper<BaseRoleResource>(BaseRoleResource.class)
        .selectAll(BaseRoleResource.class).in(BaseRoleResource::getId, req.getIdList());
    List<BaseRoleResource> list = this.baseRoleResourceService.list(q);
    List<BaseRoleResourceDto> dataList = $.copyList(list, BaseRoleResourceDto.class);
    this.baseRoleResourceService.setName(dataList);
    return new BaseRoleResourceQueryByIdListRes().setDataList(dataList);
  }
}
