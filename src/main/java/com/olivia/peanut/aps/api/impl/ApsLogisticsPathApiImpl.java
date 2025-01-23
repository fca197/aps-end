package com.olivia.peanut.aps.api.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsLogisticsPathApi;
import com.olivia.peanut.aps.api.entity.apsLogisticsPath.*;
import com.olivia.peanut.aps.api.entity.apsLogisticsPathItem.ApsLogisticsPathItemDto;
import com.olivia.peanut.aps.api.impl.listener.ApsLogisticsPathImportListener;
import com.olivia.peanut.aps.model.ApsLogisticsPath;
import com.olivia.peanut.aps.model.ApsLogisticsPathItem;
import com.olivia.peanut.aps.service.ApsLogisticsPathItemService;
import com.olivia.peanut.aps.service.ApsLogisticsPathService;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 物流路径表(ApsLogisticsPath)表服务实现类
 *
 * @author peanut
 * @since 2024-07-18 13:27:36
 */
@RestController
public class ApsLogisticsPathApiImpl implements ApsLogisticsPathApi {

  @Resource
  ApsLogisticsPathItemService apsLogisticsPathItemService;
  private @Autowired ApsLogisticsPathService apsLogisticsPathService;

  /****
   * insert
   *
   */
  public @Override ApsLogisticsPathInsertRes insert(ApsLogisticsPathInsertReq req) {
//    this.apsLogisticsPathService.save($.copy(req, ApsLogisticsPath.class));
    return this.apsLogisticsPathService.save(req);
//    return new ApsLogisticsPathInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsLogisticsPathDeleteByIdListRes deleteByIdList(ApsLogisticsPathDeleteByIdListReq req) {
    apsLogisticsPathService.removeByIds(req.getIdList());
    return new ApsLogisticsPathDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsLogisticsPathQueryListRes queryList(ApsLogisticsPathQueryListReq req) {
    return apsLogisticsPathService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsLogisticsPathUpdateByIdRes updateById(ApsLogisticsPathUpdateByIdReq req) {
    return apsLogisticsPathService.updateById(req);
//    return new ApsLogisticsPathUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsLogisticsPathExportQueryPageListInfoRes> queryPageList(ApsLogisticsPathExportQueryPageListReq req) {
    return apsLogisticsPathService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsLogisticsPathExportQueryPageListReq req) {
    DynamicsPage<ApsLogisticsPathExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsLogisticsPathExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsLogisticsPathExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsLogisticsPathExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsLogisticsPathExportQueryPageListInfoRes.class, listInfoRes, "物流路径表");
  }

  public @Override ApsLogisticsPathImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsLogisticsPathImportReq> reqList = PoiExcelUtil.readData(file, new ApsLogisticsPathImportListener(), ApsLogisticsPathImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsLogisticsPath> readList = $.copyList(reqList, ApsLogisticsPath.class);
    boolean bool = apsLogisticsPathService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsLogisticsPathImportRes().setCount(c);
  }

  public @Override ApsLogisticsPathQueryByIdListRes queryByIdListRes(ApsLogisticsPathQueryByIdListReq req) {
    MPJLambdaWrapper<ApsLogisticsPath> q = new MPJLambdaWrapper<>(ApsLogisticsPath.class).selectAll(ApsLogisticsPath.class).in(ApsLogisticsPath::getId, req.getIdList());
    List<ApsLogisticsPath> list = this.apsLogisticsPathService.list(q);
    List<ApsLogisticsPathDto> dataList = $.copyList(list, ApsLogisticsPathDto.class);
    this.apsLogisticsPathService.setName(dataList);
    if (CollUtil.isNotEmpty(dataList)) {
      Map<Long, List<ApsLogisticsPathItem>> itemMap = this.apsLogisticsPathItemService.list(
              new LambdaQueryWrapper<ApsLogisticsPathItem>().in(ApsLogisticsPathItem::getLogisticsPathId, dataList.stream().map(BaseEntityDto::getId).collect(Collectors.toList())))
          .stream().collect(Collectors.groupingBy(ApsLogisticsPathItem::getLogisticsPathId));
      dataList.forEach(t -> t.setApsLogisticsPathItemList($.copyList(itemMap.get(t.getId()), ApsLogisticsPathItemDto.class)));
    }

    return new ApsLogisticsPathQueryByIdListRes().setDataList(dataList);
  }
}
