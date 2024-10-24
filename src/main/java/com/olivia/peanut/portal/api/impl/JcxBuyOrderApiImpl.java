package com.olivia.peanut.portal.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.JcxBuyOrderApi;
import com.olivia.peanut.portal.api.entity.jcxBuyOrder.*;
import com.olivia.peanut.portal.api.impl.listener.JcxBuyOrderImportListener;
import com.olivia.peanut.portal.model.JcxBuyOrder;
import com.olivia.peanut.portal.service.JcxBuyOrderService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import com.olivia.sdk.utils.RunUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * (JcxBuyOrder)表服务实现类
 *
 * @author peanut
 * @since 2024-03-27 13:51:36
 */
@RestController
public class JcxBuyOrderApiImpl implements JcxBuyOrderApi {

  private @Autowired JcxBuyOrderService jcxBuyOrderService;

  /****
   * insert
   *
   */
  public @Override JcxBuyOrderInsertRes insert(JcxBuyOrderInsertReq req) {
    return this.jcxBuyOrderService.save(req);
  }

  /****
   * deleteByIds
   *
   */
  public @Override JcxBuyOrderDeleteByIdListRes deleteByIdList(JcxBuyOrderDeleteByIdListReq req) {
    RunUtils.noImpl();
    return new JcxBuyOrderDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override JcxBuyOrderQueryListRes queryList(JcxBuyOrderQueryListReq req) {
    return jcxBuyOrderService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override JcxBuyOrderUpdateByIdRes updateById(JcxBuyOrderUpdateByIdReq req) {
    RunUtils.noImpl();
    return new JcxBuyOrderUpdateByIdRes();
  }

  public @Override DynamicsPage<JcxBuyOrderExportQueryPageListInfoRes> queryPageList(JcxBuyOrderExportQueryPageListReq req) {
    return jcxBuyOrderService.queryPageList(req);
  }

  public @Override void queryPageListExport(JcxBuyOrderExportQueryPageListReq req) {
    DynamicsPage<JcxBuyOrderExportQueryPageListInfoRes> page = queryPageList(req);
    List<JcxBuyOrderExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<JcxBuyOrderExportQueryPageListInfoRes> listInfoRes = $.copyList(list, JcxBuyOrderExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(JcxBuyOrderExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override JcxBuyOrderImportRes importData(@RequestParam("file") MultipartFile file) {
    List<JcxBuyOrderImportReq> reqList = PoiExcelUtil.readData(file, new JcxBuyOrderImportListener(), JcxBuyOrderImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<JcxBuyOrder> readList = $.copyList(reqList, JcxBuyOrder.class);
    boolean bool = jcxBuyOrderService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new JcxBuyOrderImportRes().setCount(c);
  }

  public @Override JcxBuyOrderQueryByIdListRes queryByIdListRes(JcxBuyOrderQueryByIdListReq req) {
    MPJLambdaWrapper<JcxBuyOrder> q = new MPJLambdaWrapper<JcxBuyOrder>(JcxBuyOrder.class).selectAll(JcxBuyOrder.class).in(JcxBuyOrder::getId, req.getIdList());
    List<JcxBuyOrder> list = this.jcxBuyOrderService.list(q);
    List<JcxBuyOrderDto> dataList = $.copyList(list, JcxBuyOrderDto.class);
    return new JcxBuyOrderQueryByIdListRes().setDataList(dataList);
  }


  @Override
  public JcxBuyOrderUpdateStatusRes updateStatus(JcxBuyOrderUpdateStatusReq req) {

    this.jcxBuyOrderService.updateStatus(req);

    return new JcxBuyOrderUpdateStatusRes();
  }
}
