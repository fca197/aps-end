package com.olivia.peanut.base.api;


import com.olivia.peanut.base.api.entity.districtCode.*;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * (DistrictCode)对外API
 *
 * @author peanut
 * @since 2024-04-09 13:19:06
 */
// @FeignClient(value = "",contextId = "districtCode-api",url = "${ portal..center.endpoint:}")
public interface DistrictCodeApi {

  /**
   * 保存
   */
  @PostMapping("/districtCode/insert")
  DistrictCodeInsertRes insert(@RequestBody @Validated(InsertCheck.class) DistrictCodeInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/districtCode/deleteByIdList")
  DistrictCodeDeleteByIdListRes deleteByIdList(@RequestBody @Valid DistrictCodeDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/districtCode/queryList")
  DistrictCodeQueryListRes queryList(@RequestBody @Valid DistrictCodeQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/districtCode/updateById")
  DistrictCodeUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) DistrictCodeUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/districtCode/queryPageList")
  DynamicsPage<DistrictCodeExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid DistrictCodeExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/districtCode/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid DistrictCodeExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/districtCode/importData")
  DistrictCodeImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/districtCode/queryByIdList")
  DistrictCodeQueryByIdListRes queryByIdListRes(@RequestBody @Valid DistrictCodeQueryByIdListReq req);

  //更新level
  @PostMapping("/districtCode/updateLevel")
  DistrictCodeUpdateLevelRes updateLevel(@RequestBody @Valid DistrictCodeUpdateLevelReq req);

}
