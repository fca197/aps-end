package com.olivia.peanut.portal.api.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.StoreyApi;
import com.olivia.peanut.portal.api.entity.storey.*;
import com.olivia.peanut.portal.api.impl.listener.StoreyImportListener;
import com.olivia.peanut.portal.model.Storey;
import com.olivia.peanut.portal.service.StoreyService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 楼层信息(Storey)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 17:20:55
 */
@RestController
public class StoreyApiImpl implements StoreyApi {

  private @Autowired StoreyService storeyService;

  /****
   * insert
   *
   */
  public @Override StoreyInsertRes insert(StoreyInsertReq req) {
    this.storeyService.save($.copy(req, Storey.class));
    return new StoreyInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override StoreyDeleteByIdListRes deleteByIdList(StoreyDeleteByIdListReq req) {
    storeyService.removeByIds(req.getIdList());
    return new StoreyDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override StoreyQueryListRes queryList(StoreyQueryListReq req) {
    return storeyService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override StoreyUpdateByIdRes updateById(StoreyUpdateByIdReq req) {
    storeyService.updateById($.copy(req, Storey.class));
    return new StoreyUpdateByIdRes();

  }

  public @Override DynamicsPage<StoreyExportQueryPageListInfoRes> queryPageList(StoreyExportQueryPageListReq req) {
    return storeyService.queryPageList(req);
  }

  public @Override void queryPageListExport(StoreyExportQueryPageListReq req) {
    DynamicsPage<StoreyExportQueryPageListInfoRes> page = queryPageList(req);
    List<StoreyExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<StoreyExportQueryPageListInfoRes> listInfoRes = $.copyList(list, StoreyExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(StoreyExportQueryPageListInfoRes.class, listInfoRes, "楼层信息");
  }

  public @Override StoreyImportRes importData(@RequestParam("file") MultipartFile file) {
    List<StoreyImportReq> reqList = PoiExcelUtil.readData(file, new StoreyImportListener(), StoreyImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<Storey> readList = $.copyList(reqList, Storey.class);
    boolean bool = storeyService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new StoreyImportRes().setCount(c);
  }

  public @Override StoreyQueryByIdListRes queryByIdListRes(StoreyQueryByIdListReq req) {
    MPJLambdaWrapper<Storey> q = new MPJLambdaWrapper<Storey>(Storey.class)
        .selectAll(Storey.class).in(Storey::getId, req.getIdList());
    List<Storey> list = this.storeyService.list(q);
    List<StoreyDto> dataList = $.copyList(list, StoreyDto.class);
    return new StoreyQueryByIdListRes().setDataList(dataList);
  }

  @Override
  @Transactional
  public StoreyAddBatchRes addBatch(StoreyAddBatchReq req) {
    List<Storey> storeyList = Lists.newArrayList();
    IntStream.range(1, req.getStoreyCount() + 1).forEach(i -> {
      Storey storey = new Storey();
      storey.setId(IdWorker.getId());
      storey.setFactoryId(req.getFactoryId());
      storey.setStoreyCode(req.isPrefix() ? (req.getStoreyCode() + StringUtils.right("0" + i, 2))
          : (StringUtils.right("0" + i, 2) + req.getStoreyCode()));
      storey.setStoreyName(storey.getStoreyCode());
      storey.setStoreySort(i);
      storeyList.add(storey);
    });
    this.storeyService.saveBatch(storeyList);
    return new StoreyAddBatchRes().setIdList(storeyList.stream().map(Storey::getId).toList());
  }
}
