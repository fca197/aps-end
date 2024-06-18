package com.olivia.peanut.portal.api;


import com.olivia.peanut.portal.api.entity.storey.*;
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
 * 楼层信息(Storey)对外API
 *
 * @author makejava
 * @since 2024-03-11 17:20:55
 */
// @FeignClient(value = "",contextId = "storey-api",url = "${ portal..center.endpoint:}")
public interface StoreyApi {

  /**
   * 保存 楼层信息
   */
  @PostMapping("/storey/insert")
  StoreyInsertRes insert(@RequestBody @Validated(InsertCheck.class) StoreyInsertReq req);

  /**
   * 根据ID 删除 楼层信息
   */
  @PostMapping("/storey/deleteByIdList")
  StoreyDeleteByIdListRes deleteByIdList(@RequestBody @Valid StoreyDeleteByIdListReq req);

  /**
   * 查询 楼层信息
   */
  @PostMapping("/storey/queryList")
  StoreyQueryListRes queryList(@RequestBody @Valid StoreyQueryListReq req);

  /**
   * 根据ID 更新 楼层信息
   */
  @PostMapping("/storey/updateById")
  StoreyUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) StoreyUpdateByIdReq req);

  /**
   * 分页查询 楼层信息
   */
  @PostMapping("/storey/queryPageList")
  DynamicsPage<StoreyExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid StoreyExportQueryPageListReq req);

  /**
   * 导出 楼层信息
   */
  @PostMapping("/storey/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid StoreyExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/storey/importData")
  StoreyImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/storey/queryByIdList")
  StoreyQueryByIdListRes queryByIdListRes(@RequestBody @Valid StoreyQueryByIdListReq req);

  @PostMapping("/storey/addBatch")
  StoreyAddBatchRes addBatch(@RequestBody @Valid StoreyAddBatchReq req);


}
