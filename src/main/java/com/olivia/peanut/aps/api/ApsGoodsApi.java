package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsGoods.*;
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
 * (ApsGoods)对外API
 *
 * @author peanut
 * @since 2024-03-29 16:11:22
 */
// @FeignClient(value = "",contextId = "apsGoods-api",url = "${ portal..center.endpoint:}")
public interface ApsGoodsApi {

  /**
   * 保存
   */
  @PostMapping("/apsGoods/insert")
  ApsGoodsInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsGoodsInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsGoods/deleteByIdList")
  ApsGoodsDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsGoodsDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsGoods/queryList")
  ApsGoodsQueryListRes queryList(@RequestBody @Valid ApsGoodsQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsGoods/updateById")
  ApsGoodsUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsGoodsUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsGoods/queryPageList")
  DynamicsPage<ApsGoodsExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsGoodsExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsGoods/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsGoodsExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsGoods/importData")
  ApsGoodsImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsGoods/queryByIdList")
  ApsGoodsQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsGoodsQueryByIdListReq req);


}
