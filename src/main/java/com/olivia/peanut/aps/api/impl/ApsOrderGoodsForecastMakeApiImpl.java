package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsOrderGoodsForecastMakeApi;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsForecastMake.*;
import com.olivia.peanut.aps.api.impl.listener.ApsOrderGoodsForecastMakeImportListener;
import com.olivia.peanut.aps.model.ApsOrderGoodsForecastMake;
import com.olivia.peanut.aps.service.ApsOrderGoodsForecastMakeService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 订单商品节点预测表(ApsOrderGoodsForecastMake)表服务实现类
 *
 * @author peanut
 * @since 2024-06-02 23:11:40
 */
@RestController
public class ApsOrderGoodsForecastMakeApiImpl implements ApsOrderGoodsForecastMakeApi {

  private @Autowired ApsOrderGoodsForecastMakeService apsOrderGoodsForecastMakeService;

  /****
   * insert
   *
   */
  public @Override ApsOrderGoodsForecastMakeInsertRes insert(ApsOrderGoodsForecastMakeInsertReq req) {
    this.apsOrderGoodsForecastMakeService.save($.copy(req, ApsOrderGoodsForecastMake.class));
    return new ApsOrderGoodsForecastMakeInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsOrderGoodsForecastMakeDeleteByIdListRes deleteByIdList(ApsOrderGoodsForecastMakeDeleteByIdListReq req) {
    apsOrderGoodsForecastMakeService.removeByIds(req.getIdList());
    return new ApsOrderGoodsForecastMakeDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsOrderGoodsForecastMakeQueryListRes queryList(ApsOrderGoodsForecastMakeQueryListReq req) {
    return apsOrderGoodsForecastMakeService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsOrderGoodsForecastMakeUpdateByIdRes updateById(ApsOrderGoodsForecastMakeUpdateByIdReq req) {
    apsOrderGoodsForecastMakeService.updateById($.copy(req, ApsOrderGoodsForecastMake.class));
    return new ApsOrderGoodsForecastMakeUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsOrderGoodsForecastMakeExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsForecastMakeExportQueryPageListReq req) {
    return apsOrderGoodsForecastMakeService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsOrderGoodsForecastMakeExportQueryPageListReq req) {
    DynamicsPage<ApsOrderGoodsForecastMakeExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsOrderGoodsForecastMakeExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsOrderGoodsForecastMakeExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsOrderGoodsForecastMakeExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsOrderGoodsForecastMakeExportQueryPageListInfoRes.class, listInfoRes, "订单商品节点预测表");
  }

  public @Override ApsOrderGoodsForecastMakeImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsOrderGoodsForecastMakeImportReq> reqList = PoiExcelUtil.readData(file, new ApsOrderGoodsForecastMakeImportListener(), ApsOrderGoodsForecastMakeImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsOrderGoodsForecastMake> readList = $.copyList(reqList, ApsOrderGoodsForecastMake.class);
    boolean bool = apsOrderGoodsForecastMakeService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsOrderGoodsForecastMakeImportRes().setCount(c);
  }

  public @Override ApsOrderGoodsForecastMakeQueryByIdListRes queryByIdListRes(ApsOrderGoodsForecastMakeQueryByIdListReq req) {
    MPJLambdaWrapper<ApsOrderGoodsForecastMake> q = new MPJLambdaWrapper<ApsOrderGoodsForecastMake>(ApsOrderGoodsForecastMake.class)
        .selectAll(ApsOrderGoodsForecastMake.class).in(ApsOrderGoodsForecastMake::getId, req.getIdList());
    List<ApsOrderGoodsForecastMake> list = this.apsOrderGoodsForecastMakeService.list(q);
    List<ApsOrderGoodsForecastMakeDto> dataList = $.copyList(list, ApsOrderGoodsForecastMakeDto.class);
    this.apsOrderGoodsForecastMakeService.setName(dataList);
    return new ApsOrderGoodsForecastMakeQueryByIdListRes().setDataList(dataList);
  }
}
