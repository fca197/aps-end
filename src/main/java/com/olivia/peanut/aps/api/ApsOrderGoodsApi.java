package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsOrderGoods.*;
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
 * (ApsOrderGoods)对外API
 *
 * @author peanut
 * @since 2024-04-09 13:19:36
 */
// @FeignClient(value = "",contextId = "apsOrderGoods-api",url = "${ portal..center.endpoint:}")
public interface ApsOrderGoodsApi {

  /**
   * 保存
   */
  @PostMapping("/apsOrderGoods/insert")
  ApsOrderGoodsInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsOrderGoodsInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsOrderGoods/deleteByIdList")
  ApsOrderGoodsDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsOrderGoodsDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsOrderGoods/queryList")
  ApsOrderGoodsQueryListRes queryList(@RequestBody @Valid ApsOrderGoodsQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsOrderGoods/updateById")
  ApsOrderGoodsUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsOrderGoodsUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsOrderGoods/queryPageList")
  DynamicsPage<ApsOrderGoodsExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsOrderGoodsExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsOrderGoods/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsOrderGoodsExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsOrderGoods/importData")
  ApsOrderGoodsImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsOrderGoods/queryByIdList")
  ApsOrderGoodsQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsOrderGoodsQueryByIdListReq req);


}
