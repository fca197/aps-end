package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsOrderGoodsForecastMake.*;
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
 * 订单商品节点预测表(ApsOrderGoodsForecastMake)对外API
 *
 * @author peanut
 * @since 2024-06-02 23:11:40
 */
// @FeignClient(value = "",contextId = "apsOrderGoodsForecastMake-api",url = "${ portal..center.endpoint:}")
public interface ApsOrderGoodsForecastMakeApi {

  /**
   * 保存 订单商品节点预测表
   */
  @PostMapping("/apsOrderGoodsForecastMake/insert")
  ApsOrderGoodsForecastMakeInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsOrderGoodsForecastMakeInsertReq req);

  /**
   * 根据ID 删除 订单商品节点预测表
   */
  @PostMapping("/apsOrderGoodsForecastMake/deleteByIdList")
  ApsOrderGoodsForecastMakeDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsOrderGoodsForecastMakeDeleteByIdListReq req);

  /**
   * 查询 订单商品节点预测表
   */
  @PostMapping("/apsOrderGoodsForecastMake/queryList")
  ApsOrderGoodsForecastMakeQueryListRes queryList(@RequestBody @Valid ApsOrderGoodsForecastMakeQueryListReq req);

  /**
   * 根据ID 更新 订单商品节点预测表
   */
  @PostMapping("/apsOrderGoodsForecastMake/updateById")
  ApsOrderGoodsForecastMakeUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsOrderGoodsForecastMakeUpdateByIdReq req);

  /**
   * 分页查询 订单商品节点预测表
   */
  @PostMapping("/apsOrderGoodsForecastMake/queryPageList")
  DynamicsPage<ApsOrderGoodsForecastMakeExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsOrderGoodsForecastMakeExportQueryPageListReq req);

  /**
   * 导出 订单商品节点预测表
   */
  @PostMapping("/apsOrderGoodsForecastMake/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsOrderGoodsForecastMakeExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsOrderGoodsForecastMake/importData")
  ApsOrderGoodsForecastMakeImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsOrderGoodsForecastMake/queryByIdList")
  ApsOrderGoodsForecastMakeQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsOrderGoodsForecastMakeQueryByIdListReq req);


}
