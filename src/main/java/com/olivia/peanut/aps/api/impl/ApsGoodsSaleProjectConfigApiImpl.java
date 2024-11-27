package com.olivia.peanut.aps.api.impl;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsGoodsSaleProjectConfigApi;
import com.olivia.peanut.aps.api.entity.apsGoodsSaleProjectConfig.*;
import com.olivia.peanut.aps.api.impl.listener.ApsGoodsSaleProjectConfigImportListener;
import com.olivia.peanut.aps.model.ApsGoodsSaleProjectConfig;
import com.olivia.peanut.aps.service.ApsGoodsSaleProjectConfigService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * (ApsGoodsSaleProjectConfig)表服务实现类
 *
 * @author peanut
 * @since 2024-04-27 16:07:22
 */
@RestController
public class ApsGoodsSaleProjectConfigApiImpl implements ApsGoodsSaleProjectConfigApi {

  private @Autowired ApsGoodsSaleProjectConfigService apsGoodsSaleProjectConfigService;

  /****
   * insert
   *
   */
  public @Override ApsGoodsSaleProjectConfigInsertRes insert(ApsGoodsSaleProjectConfigInsertReq req) {
    this.apsGoodsSaleProjectConfigService.save($.copy(req, ApsGoodsSaleProjectConfig.class));
    return new ApsGoodsSaleProjectConfigInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsGoodsSaleProjectConfigDeleteByIdListRes deleteByIdList(ApsGoodsSaleProjectConfigDeleteByIdListReq req) {
    apsGoodsSaleProjectConfigService.removeByIds(req.getIdList());
    return new ApsGoodsSaleProjectConfigDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsSaleProjectConfigQueryListRes queryList(ApsGoodsSaleProjectConfigQueryListReq req) {
    return apsGoodsSaleProjectConfigService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsSaleProjectConfigUpdateByIdRes updateById(ApsGoodsSaleProjectConfigUpdateByIdReq req) {
    apsGoodsSaleProjectConfigService.updateById($.copy(req, ApsGoodsSaleProjectConfig.class));
    return new ApsGoodsSaleProjectConfigUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsSaleProjectConfigExportQueryPageListInfoRes> queryPageList(ApsGoodsSaleProjectConfigExportQueryPageListReq req) {
    return apsGoodsSaleProjectConfigService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsSaleProjectConfigExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsSaleProjectConfigExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsSaleProjectConfigExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsSaleProjectConfigExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsSaleProjectConfigExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsSaleProjectConfigExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsGoodsSaleProjectConfigImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsGoodsSaleProjectConfigImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsSaleProjectConfigImportListener(), ApsGoodsSaleProjectConfigImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsSaleProjectConfig> readList = $.copyList(reqList, ApsGoodsSaleProjectConfig.class);
    boolean bool = apsGoodsSaleProjectConfigService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsSaleProjectConfigImportRes().setCount(c);
  }

  public @Override ApsGoodsSaleProjectConfigQueryByIdListRes queryByIdListRes(ApsGoodsSaleProjectConfigQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoodsSaleProjectConfig> q = new MPJLambdaWrapper<ApsGoodsSaleProjectConfig>(ApsGoodsSaleProjectConfig.class).selectAll(ApsGoodsSaleProjectConfig.class)
        .in(ApsGoodsSaleProjectConfig::getId, req.getIdList());
    List<ApsGoodsSaleProjectConfig> list = this.apsGoodsSaleProjectConfigService.list(q);
    List<ApsGoodsSaleProjectConfigDto> dataList = $.copyList(list, ApsGoodsSaleProjectConfigDto.class);
    this.apsGoodsSaleProjectConfigService.setName(dataList);
    return new ApsGoodsSaleProjectConfigQueryByIdListRes().setDataList(dataList);
  }

  @Override
  @Transactional
  public ApsGoodsSaleProjectConfigInsertRes insertBatch(List<ApsGoodsSaleProjectConfigInsertReq> req) {
    if (CollUtil.isEmpty(req)) {
      return new ApsGoodsSaleProjectConfigInsertRes().setIdList(List.of()).setCount(0);
    }
    List<ApsGoodsSaleProjectConfig> entityList = $.copyList(req, ApsGoodsSaleProjectConfig.class);
    entityList.forEach(s -> {
      s.setId(IdWorker.getId());
    });
    this.apsGoodsSaleProjectConfigService.remove(
        new LambdaQueryWrapper<ApsGoodsSaleProjectConfig>().in(ApsGoodsSaleProjectConfig::getGoodsId,
            entityList.stream().map(ApsGoodsSaleProjectConfig::getGoodsId).toList()));
    this.apsGoodsSaleProjectConfigService.saveBatch(entityList);
    return new ApsGoodsSaleProjectConfigInsertRes().setIdList(entityList.stream().map(BaseEntity::getId).toList()).setCount(entityList.size());
  }

  @Override
  public ApsGoodsSaleProjectConfigSale2ProjectRes sale2project(ApsGoodsSaleProjectConfigSale2ProjectReq req) throws ExecutionException {
    return this.apsGoodsSaleProjectConfigService.sale2project(req);
  }
}
