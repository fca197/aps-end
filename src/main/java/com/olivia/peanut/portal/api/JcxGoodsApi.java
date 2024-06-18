package com.olivia.peanut.portal.api;


import com.olivia.peanut.portal.api.entity.goods.*;
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
 * 商品信息(Goods)对外API
 *
 * @author makejava
 * @since 2024-03-11 10:44:05
 */
// @FeignClient(value = "",contextId = "goods-api",url = "${ portal..center.endpoint:}")

public interface JcxGoodsApi {

  /**
   * 保存 商品信息
   */
  @PostMapping("/jcx/goods/insert")
  GoodsInsertRes insert(@RequestBody @Validated(InsertCheck.class) JcxGoodsInsertReq req);

  /**
   * 根据ID 删除 商品信息
   */
  @PostMapping("/jcx/goods/deleteByIdList")
  GoodsDeleteByIdListRes deleteByIdList(@RequestBody @Valid GoodsDeleteByIdListReq req);

  /**
   * 查询 商品信息
   */
  @PostMapping("/jcx/goods/queryList")
  GoodsQueryListRes queryList(@RequestBody @Valid GoodsQueryListReq req);

  /**
   * 根据ID 更新 商品信息
   */
  @PostMapping("/jcx/goods/updateById")
  GoodsUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) JcxGoodsUpdateByIdReq req);

  /**
   * 分页查询 商品信息
   */
  @PostMapping("/jcx/goods/queryPageList")
  DynamicsPage<JcxGoodsExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid GoodsExportQueryPageListReq req);

  /**
   * 导出 商品信息
   */
  @PostMapping("/jcx/goods/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid GoodsExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/jcx/goods/importData")
  GoodsImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/jcx/goods/queryByIdList")
  GoodsQueryByIdListRes queryByIdListRes(@RequestBody @Valid GoodsQueryByIdListReq req);


}
