package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsMakeCapacityGoods.*;
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
 * (ApsMakeCapacityGoods)对外API
 *
 * @author peanut
 * @since 2024-04-13 12:05:05
 */
// @FeignClient(value = "",contextId = "apsMakeCapacityGoods-api",url = "${ portal..center.endpoint:}")
public interface ApsMakeCapacityGoodsApi {

  /**
   * 保存
   */
  @PostMapping("/apsMakeCapacityGoods/insert")
  ApsMakeCapacityGoodsInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsMakeCapacityGoodsInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsMakeCapacityGoods/deleteByIdList")
  ApsMakeCapacityGoodsDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsMakeCapacityGoodsDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsMakeCapacityGoods/queryList")
  ApsMakeCapacityGoodsQueryListRes queryList(@RequestBody @Valid ApsMakeCapacityGoodsQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsMakeCapacityGoods/updateById")
  ApsMakeCapacityGoodsUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsMakeCapacityGoodsUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsMakeCapacityGoods/queryPageList")
  DynamicsPage<ApsMakeCapacityGoodsExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsMakeCapacityGoodsExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsMakeCapacityGoods/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsMakeCapacityGoodsExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsMakeCapacityGoods/importData")
  ApsMakeCapacityGoodsImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsMakeCapacityGoods/queryByIdList")
  ApsMakeCapacityGoodsQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsMakeCapacityGoodsQueryByIdListReq req);


}
