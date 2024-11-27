package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsMachineApi;
import com.olivia.peanut.aps.api.entity.apsMachine.*;
import com.olivia.peanut.aps.api.impl.listener.ApsMachineImportListener;
import com.olivia.peanut.aps.model.ApsMachine;
import com.olivia.peanut.aps.service.ApsMachineService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * aps 生产机器(ApsMachine)表服务实现类
 *
 * @author makejava
 * @since 2024-10-24 16:31:13
 */
@RestController
public class ApsMachineApiImpl implements ApsMachineApi {

  private @Autowired ApsMachineService apsMachineService;

  /****
   * insert
   *
   */
  public @Override ApsMachineInsertRes insert(ApsMachineInsertReq req) {
    this.apsMachineService.save($.copy(req, ApsMachine.class));
    return new ApsMachineInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsMachineDeleteByIdListRes deleteByIdList(ApsMachineDeleteByIdListReq req) {
    apsMachineService.removeByIds(req.getIdList());
    return new ApsMachineDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsMachineQueryListRes queryList(ApsMachineQueryListReq req) {
    return apsMachineService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsMachineUpdateByIdRes updateById(ApsMachineUpdateByIdReq req) {
    apsMachineService.updateById($.copy(req, ApsMachine.class));
    return new ApsMachineUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsMachineExportQueryPageListInfoRes> queryPageList(ApsMachineExportQueryPageListReq req) {
    return apsMachineService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsMachineExportQueryPageListReq req) {
    DynamicsPage<ApsMachineExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsMachineExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsMachineExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsMachineExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsMachineExportQueryPageListInfoRes.class, listInfoRes, "aps 生产机器");
  }

  public @Override ApsMachineImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsMachineImportReq> reqList = PoiExcelUtil.readData(file, new ApsMachineImportListener(), ApsMachineImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsMachine> readList = $.copyList(reqList, ApsMachine.class);
    boolean bool = apsMachineService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsMachineImportRes().setCount(c);
  }

  public @Override ApsMachineQueryByIdListRes queryByIdListRes(ApsMachineQueryByIdListReq req) {
    MPJLambdaWrapper<ApsMachine> q = new MPJLambdaWrapper<ApsMachine>(ApsMachine.class)
        .selectAll(ApsMachine.class).in(ApsMachine::getId, req.getIdList());
    List<ApsMachine> list = this.apsMachineService.list(q);
    List<ApsMachineDto> dataList = $.copyList(list, ApsMachineDto.class);
    this.apsMachineService.setName(dataList);
    return new ApsMachineQueryByIdListRes().setDataList(dataList);
  }
}
