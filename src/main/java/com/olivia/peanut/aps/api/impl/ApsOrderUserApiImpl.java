package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsOrderUserApi;
import com.olivia.peanut.aps.api.entity.apsOrderUser.*;
import com.olivia.peanut.aps.api.impl.listener.ApsOrderUserImportListener;
import com.olivia.peanut.aps.model.ApsOrderUser;
import com.olivia.peanut.aps.service.ApsOrderUserService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsOrderUser)表服务实现类
 *
 * @author peanut
 * @since 2024-04-09 13:19:38
 */
@RestController
public class ApsOrderUserApiImpl implements ApsOrderUserApi {

  private @Autowired ApsOrderUserService apsOrderUserService;

  /****
   * insert
   *
   */
  public @Override ApsOrderUserInsertRes insert(ApsOrderUserInsertReq req) {
    this.apsOrderUserService.save($.copy(req, ApsOrderUser.class));
    return new ApsOrderUserInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsOrderUserDeleteByIdListRes deleteByIdList(ApsOrderUserDeleteByIdListReq req) {
    apsOrderUserService.removeByIds(req.getIdList());
    return new ApsOrderUserDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsOrderUserQueryListRes queryList(ApsOrderUserQueryListReq req) {
    return apsOrderUserService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsOrderUserUpdateByIdRes updateById(ApsOrderUserUpdateByIdReq req) {
    apsOrderUserService.updateById($.copy(req, ApsOrderUser.class));
    return new ApsOrderUserUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsOrderUserExportQueryPageListInfoRes> queryPageList(ApsOrderUserExportQueryPageListReq req) {
    return apsOrderUserService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsOrderUserExportQueryPageListReq req) {
    DynamicsPage<ApsOrderUserExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsOrderUserExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsOrderUserExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsOrderUserExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsOrderUserExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsOrderUserImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsOrderUserImportReq> reqList = PoiExcelUtil.readData(file, new ApsOrderUserImportListener(), ApsOrderUserImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsOrderUser> readList = $.copyList(reqList, ApsOrderUser.class);
    boolean bool = apsOrderUserService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsOrderUserImportRes().setCount(c);
  }

  public @Override ApsOrderUserQueryByIdListRes queryByIdListRes(ApsOrderUserQueryByIdListReq req) {
    MPJLambdaWrapper<ApsOrderUser> q = new MPJLambdaWrapper<ApsOrderUser>(ApsOrderUser.class)
        .selectAll(ApsOrderUser.class).in(ApsOrderUser::getId, req.getIdList());
    List<ApsOrderUser> list = this.apsOrderUserService.list(q);
    List<ApsOrderUserDto> dataList = $.copyList(list, ApsOrderUserDto.class);
    this.apsOrderUserService.setName(dataList);
    return new ApsOrderUserQueryByIdListRes().setDataList(dataList);
  }
}
