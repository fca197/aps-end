package com.olivia.peanut.portal.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.JcxGoodsWarningApi;
import com.olivia.peanut.portal.api.entity.jcxGoodsWarning.*;
import com.olivia.peanut.portal.api.impl.listener.JcxGoodsWarningImportListener;
import com.olivia.peanut.portal.model.JcxGoodsWarning;
import com.olivia.peanut.portal.service.JcxGoodsWarningService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * (JcxGoodsWarning)表服务实现类
 *
 * @author peanut
 * @since 2024-03-24 14:10:54
 */
@RestController
public class JcxGoodsWarningApiImpl implements JcxGoodsWarningApi {

  private @Autowired JcxGoodsWarningService jcxGoodsWarningService;

  /****
   * insert
   *
   */
  public @Override JcxGoodsWarningInsertRes insert(JcxGoodsWarningInsertReq req) {
    this.jcxGoodsWarningService.save($.copy(req, JcxGoodsWarning.class));
    return new JcxGoodsWarningInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override JcxGoodsWarningDeleteByIdListRes deleteByIdList(JcxGoodsWarningDeleteByIdListReq req) {
    jcxGoodsWarningService.removeByIds(req.getIdList());
    return new JcxGoodsWarningDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override JcxGoodsWarningQueryListRes queryList(JcxGoodsWarningQueryListReq req) {
    return jcxGoodsWarningService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override JcxGoodsWarningUpdateByIdRes updateById(JcxGoodsWarningUpdateByIdReq req) {
    jcxGoodsWarningService.updateById($.copy(req, JcxGoodsWarning.class));
    return new JcxGoodsWarningUpdateByIdRes();

  }

  public @Override DynamicsPage<JcxGoodsWarningExportQueryPageListInfoRes> queryPageList(JcxGoodsWarningExportQueryPageListReq req) {
    return jcxGoodsWarningService.queryPageList(req);
  }

  public @Override void queryPageListExport(JcxGoodsWarningExportQueryPageListReq req) {
    DynamicsPage<JcxGoodsWarningExportQueryPageListInfoRes> page = queryPageList(req);
    List<JcxGoodsWarningExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<JcxGoodsWarningExportQueryPageListInfoRes> listInfoRes = $.copyList(list, JcxGoodsWarningExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(JcxGoodsWarningExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override JcxGoodsWarningImportRes importData(@RequestParam("file") MultipartFile file) {
    List<JcxGoodsWarningImportReq> reqList = PoiExcelUtil.readData(file, new JcxGoodsWarningImportListener(), JcxGoodsWarningImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<JcxGoodsWarning> readList = $.copyList(reqList, JcxGoodsWarning.class);
    boolean bool = jcxGoodsWarningService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new JcxGoodsWarningImportRes().setCount(c);
  }

  public @Override JcxGoodsWarningQueryByIdListRes queryByIdListRes(JcxGoodsWarningQueryByIdListReq req) {
    MPJLambdaWrapper<JcxGoodsWarning> q = new MPJLambdaWrapper<JcxGoodsWarning>(JcxGoodsWarning.class)
        .selectAll(JcxGoodsWarning.class).in(JcxGoodsWarning::getId, req.getIdList());
    List<JcxGoodsWarning> list = this.jcxGoodsWarningService.list(q);
    List<JcxGoodsWarningDto> dataList = $.copyList(list, JcxGoodsWarningDto.class);
    return new JcxGoodsWarningQueryByIdListRes().setDataList(dataList);
  }
}
