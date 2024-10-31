package com.olivia.peanut.portal.api.impl;


import cn.hutool.core.collection.CollUtil;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.DistrictCodeApi;
import com.olivia.peanut.portal.api.entity.districtCode.*;
import com.olivia.peanut.portal.api.impl.listener.DistrictCodeImportListener;
import com.olivia.peanut.portal.model.DistrictCode;
import com.olivia.peanut.portal.service.DistrictCodeService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * (DistrictCode)表服务实现类
 *
 * @author peanut
 * @since 2024-04-09 13:19:06
 */
@RestController
public class DistrictCodeApiImpl implements DistrictCodeApi {

  private @Autowired DistrictCodeService districtCodeService;

  /****
   * insert
   *
   */
  public @Override DistrictCodeInsertRes insert(DistrictCodeInsertReq req) {
    this.districtCodeService.save($.copy(req, DistrictCode.class));
    return new DistrictCodeInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override DistrictCodeDeleteByIdListRes deleteByIdList(DistrictCodeDeleteByIdListReq req) {
    districtCodeService.removeByIds(req.getIdList());
    return new DistrictCodeDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override DistrictCodeQueryListRes queryList(DistrictCodeQueryListReq req) {
    return districtCodeService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override DistrictCodeUpdateByIdRes updateById(DistrictCodeUpdateByIdReq req) {
    districtCodeService.updateById($.copy(req, DistrictCode.class));
    return new DistrictCodeUpdateByIdRes();

  }

  public @Override DynamicsPage<DistrictCodeExportQueryPageListInfoRes> queryPageList(DistrictCodeExportQueryPageListReq req) {
    return districtCodeService.queryPageList(req);
  }

  public @Override void queryPageListExport(DistrictCodeExportQueryPageListReq req) {
    DynamicsPage<DistrictCodeExportQueryPageListInfoRes> page = queryPageList(req);
    List<DistrictCodeExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<DistrictCodeExportQueryPageListInfoRes> listInfoRes = $.copyList(list, DistrictCodeExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(DistrictCodeExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override DistrictCodeImportRes importData(@RequestParam("file") MultipartFile file) {
    List<DistrictCodeImportReq> reqList = PoiExcelUtil.readData(file, new DistrictCodeImportListener(), DistrictCodeImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<DistrictCode> readList = $.copyList(reqList, DistrictCode.class);
    boolean bool = districtCodeService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new DistrictCodeImportRes().setCount(c);
  }

  public @Override DistrictCodeQueryByIdListRes queryByIdListRes(DistrictCodeQueryByIdListReq req) {
    MPJLambdaWrapper<DistrictCode> q = new MPJLambdaWrapper<DistrictCode>(DistrictCode.class).selectAll(DistrictCode.class).in(DistrictCode::getId, req.getIdList());
    List<DistrictCode> list = this.districtCodeService.list(q);
    List<DistrictCodeDto> dataList = $.copyList(list, DistrictCodeDto.class);
    this.districtCodeService.setName(dataList);
    return new DistrictCodeQueryByIdListRes().setDataList(dataList);
  }

  public @Override DistrictCodeUpdateLevelRes updateLevel(DistrictCodeUpdateLevelReq req) {
    List<DistrictCode> districtCodeList = this.districtCodeService.list();

    districtCodeList.stream().filter(t -> Objects.equals("0", t.getParentCode())).forEach(t -> {
      t.setPath("0/" + t.getCode());
      this.distinct(districtCodeList, t);
    });
    this.districtCodeService.updateBatchById(districtCodeList);
    return new DistrictCodeUpdateLevelRes();
  }

  // 转tree
  private void distinct(List<DistrictCode> list, DistrictCode dto) {

    List<DistrictCode> districtCodeList = list.stream()//
        .filter(t -> Objects.equals(dto.getCode(), t.getParentCode())).peek(t -> {
          t.setPath(dto.getPath() + "/" + t.getCode());
        }).toList();
    dto.setChildren(districtCodeList);
    if (CollUtil.isEmpty(districtCodeList)) {
      return;
    }
    districtCodeList.forEach(t -> {
      this.distinct(list, t);
    });
  }

}
