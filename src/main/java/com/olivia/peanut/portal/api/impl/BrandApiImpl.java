package com.olivia.peanut.portal.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.BrandApi;
import com.olivia.peanut.portal.api.entity.brand.*;
import com.olivia.peanut.portal.api.impl.listener.BrandImportListener;
import com.olivia.peanut.portal.model.Brand;
import com.olivia.peanut.portal.service.BrandService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 品牌信息(Brand)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:44:02
 */
@RestController
public class BrandApiImpl implements BrandApi {

  private @Autowired BrandService brandService;

  /****
   * insert
   *
   */
  public @Override BrandInsertRes insert(BrandInsertReq req) {
    this.brandService.save($.copy(req, Brand.class));
    return new BrandInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BrandDeleteByIdListRes deleteByIdList(BrandDeleteByIdListReq req) {
    brandService.removeByIds(req.getIdList());
    return new BrandDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BrandQueryListRes queryList(BrandQueryListReq req) {
    return brandService.queryList(req);
  }

  /****
   * updateById
   *
   */
  @Transactional
  public @Override BrandUpdateByIdRes updateById(BrandUpdateByIdReq req) {
    brandService.updateById($.copy(req, Brand.class));
    return new BrandUpdateByIdRes();

  }

  public @Override DynamicsPage<BrandExportQueryPageListInfoRes> queryPageList(BrandExportQueryPageListReq req) {
    return brandService.queryPageList(req);
  }

  public @Override void queryPageListExport(BrandExportQueryPageListReq req) {
    DynamicsPage<BrandExportQueryPageListInfoRes> page = queryPageList(req);
    List<BrandExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BrandExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BrandExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BrandExportQueryPageListInfoRes.class, listInfoRes, "品牌信息");
  }

  public @Override BrandImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BrandImportReq> reqList = PoiExcelUtil.readData(file, new BrandImportListener(), BrandImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<Brand> readList = $.copyList(reqList, Brand.class);
    boolean bool = brandService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BrandImportRes().setCount(c);
  }

  public @Override BrandQueryByIdListRes queryByIdListRes(BrandQueryByIdListReq req) {
    MPJLambdaWrapper<Brand> q = new MPJLambdaWrapper<Brand>(Brand.class)
        .selectAll(Brand.class).in(Brand::getId, req.getIdList());
    List<Brand> list = this.brandService.list(q);
    List<BrandDto> dataList = $.copyList(list, BrandDto.class);
    return new BrandQueryByIdListRes().setDataList(dataList);
  }
}
