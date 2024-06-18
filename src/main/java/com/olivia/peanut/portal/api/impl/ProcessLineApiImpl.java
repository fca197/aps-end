package com.olivia.peanut.portal.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.ProcessLineApi;
import com.olivia.peanut.portal.api.entity.processLine.*;
import com.olivia.peanut.portal.api.impl.listener.ProcessLineImportListener;
import com.olivia.peanut.portal.model.ProcessLine;
import com.olivia.peanut.portal.service.ProcessLineService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 流水线信息(ProcessLine)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:55:20
 */
@RestController
public class ProcessLineApiImpl implements ProcessLineApi {

  private @Autowired ProcessLineService processLineService;

  /****
   * insert
   *
   */
  public @Override ProcessLineInsertRes insert(ProcessLineInsertReq req) {
    this.processLineService.save($.copy(req, ProcessLine.class));
    return new ProcessLineInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ProcessLineDeleteByIdListRes deleteByIdList(ProcessLineDeleteByIdListReq req) {
    processLineService.removeByIds(req.getIdList());
    return new ProcessLineDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ProcessLineQueryListRes queryList(ProcessLineQueryListReq req) {
    return processLineService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ProcessLineUpdateByIdRes updateById(ProcessLineUpdateByIdReq req) {
    processLineService.updateById($.copy(req, ProcessLine.class));
    return new ProcessLineUpdateByIdRes();

  }

  public @Override DynamicsPage<ProcessLineExportQueryPageListInfoRes> queryPageList(ProcessLineExportQueryPageListReq req) {
    return processLineService.queryPageList(req);
  }

  public @Override void queryPageListExport(ProcessLineExportQueryPageListReq req) {
    DynamicsPage<ProcessLineExportQueryPageListInfoRes> page = queryPageList(req);
    List<ProcessLineExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ProcessLineExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ProcessLineExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ProcessLineExportQueryPageListInfoRes.class, listInfoRes, "流水线信息");
  }

  public @Override ProcessLineImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ProcessLineImportReq> reqList = PoiExcelUtil.readData(file, new ProcessLineImportListener(), ProcessLineImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ProcessLine> readList = $.copyList(reqList, ProcessLine.class);
    boolean bool = processLineService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ProcessLineImportRes().setCount(c);
  }

  public @Override ProcessLineQueryByIdListRes queryByIdListRes(ProcessLineQueryByIdListReq req) {
    MPJLambdaWrapper<ProcessLine> q = new MPJLambdaWrapper<ProcessLine>(ProcessLine.class)
        .selectAll(ProcessLine.class).in(ProcessLine::getId, req.getIdList());
    List<ProcessLine> list = this.processLineService.list(q);
    List<ProcessLineQueryByIdListRes.Info> dataList = $.copyList(list, ProcessLineQueryByIdListRes.Info.class);
    return new ProcessLineQueryByIdListRes().setDataList(dataList);
  }
}
