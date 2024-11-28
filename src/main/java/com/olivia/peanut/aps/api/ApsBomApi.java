package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsBom.*;
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
 * BOM 清单(ApsBom)对外API
 *
 * @author peanut
 * @since 2024-06-06 11:27:33
 */
// @FeignClient(value = "",contextId = "apsBom-api",url = "${ portal..center.endpoint:}")
public interface ApsBomApi {

  /**
   * 保存 BOM 清单
   */
  @PostMapping("/apsBom/insert")
  ApsBomInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsBomInsertReq req);

  /**
   * 根据ID 删除 BOM 清单
   */
  @PostMapping("/apsBom/deleteByIdList")
  ApsBomDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsBomDeleteByIdListReq req);

  /**
   * 查询 BOM 清单
   */
  @PostMapping("/apsBom/queryList")
  ApsBomQueryListRes queryList(@RequestBody @Valid ApsBomQueryListReq req);

  /**
   * 根据ID 更新 BOM 清单
   */
  @PostMapping("/apsBom/updateById")
  ApsBomUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsBomUpdateByIdReq req);

  /**
   * 分页查询 BOM 清单
   */
  @PostMapping("/apsBom/queryPageList")
  DynamicsPage<ApsBomExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsBomExportQueryPageListReq req);

  /**
   * 导出 BOM 清单
   */
  @PostMapping("/apsBom/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsBomExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsBom/importData")
  ApsBomImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsBom/queryByIdList")
  ApsBomQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsBomQueryByIdListReq req);


}
