package com.olivia.peanut.base.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.api.BaseTableHeaderApi;
import com.olivia.peanut.base.api.entity.baseTableHeader.*;
import com.olivia.peanut.base.model.BaseTableHeader;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.portal.api.impl.listener.BaseTableHeaderImportListener;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (BaseTableHeader)表服务实现类
 *
 * @author peanut
 * @since 2024-03-25 14:19:09
 */
@RestController
public class BaseTableHeaderApiImpl implements BaseTableHeaderApi {

  private @Autowired BaseTableHeaderService baseTableHeaderService;

  /****
   * insert
   *
   */
  public @Override BaseTableHeaderInsertRes insert(BaseTableHeaderInsertReq req) {
    this.baseTableHeaderService.save($.copy(req, BaseTableHeader.class));
    return new BaseTableHeaderInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BaseTableHeaderDeleteByIdListRes deleteByIdList(BaseTableHeaderDeleteByIdListReq req) {
    baseTableHeaderService.removeByIds(req.getIdList());
    return new BaseTableHeaderDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BaseTableHeaderQueryListRes queryList(BaseTableHeaderQueryListReq req) {
    return baseTableHeaderService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BaseTableHeaderUpdateByIdRes updateById(BaseTableHeaderUpdateByIdReq req) {
    baseTableHeaderService.updateById($.copy(req, BaseTableHeader.class));
    return new BaseTableHeaderUpdateByIdRes();

  }

  public @Override DynamicsPage<BaseTableHeaderExportQueryPageListInfoRes> queryPageList(BaseTableHeaderExportQueryPageListReq req) {
    return baseTableHeaderService.queryPageList(req);
  }

  public @Override void queryPageListExport(BaseTableHeaderExportQueryPageListReq req) {
    DynamicsPage<BaseTableHeaderExportQueryPageListInfoRes> page = queryPageList(req);
    List<BaseTableHeaderExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BaseTableHeaderExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BaseTableHeaderExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BaseTableHeaderExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override BaseTableHeaderImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BaseTableHeaderImportReq> reqList = PoiExcelUtil.readData(file, new BaseTableHeaderImportListener(), BaseTableHeaderImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BaseTableHeader> readList = $.copyList(reqList, BaseTableHeader.class);
    boolean bool = baseTableHeaderService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BaseTableHeaderImportRes().setCount(c);
  }

  public @Override BaseTableHeaderQueryByIdListRes queryByIdListRes(BaseTableHeaderQueryByIdListReq req) {
    MPJLambdaWrapper<BaseTableHeader> q = new MPJLambdaWrapper<BaseTableHeader>(BaseTableHeader.class)
        .selectAll(BaseTableHeader.class).in(BaseTableHeader::getId, req.getIdList());
    List<BaseTableHeader> list = this.baseTableHeaderService.list(q);
    List<BaseTableHeaderDto> dataList = $.copyList(list, BaseTableHeaderDto.class);
    return new BaseTableHeaderQueryByIdListRes().setDataList(dataList);
  }
}
