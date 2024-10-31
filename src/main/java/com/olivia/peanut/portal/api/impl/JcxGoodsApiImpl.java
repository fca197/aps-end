package com.olivia.peanut.portal.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.JcxGoodsApi;
import com.olivia.peanut.portal.api.entity.goods.*;
import com.olivia.peanut.portal.api.impl.listener.GoodsImportListener;
import com.olivia.peanut.portal.model.JcxGoods;
import com.olivia.peanut.portal.service.JcxGoodsService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商品信息(Goods)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:44:05
 */
@RestController
public class JcxGoodsApiImpl implements JcxGoodsApi {

  private @Autowired JcxGoodsService jcxGoodsService;

  /****
   * insert
   *
   */
  public @Override GoodsInsertRes insert(JcxGoodsInsertReq req) {
    this.jcxGoodsService.save($.copy(req, JcxGoods.class));
    return new GoodsInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override GoodsDeleteByIdListRes deleteByIdList(GoodsDeleteByIdListReq req) {
    jcxGoodsService.removeByIds(req.getIdList());
    return new GoodsDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override GoodsQueryListRes queryList(GoodsQueryListReq req) {
    return jcxGoodsService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override GoodsUpdateByIdRes updateById(JcxGoodsUpdateByIdReq req) {
    jcxGoodsService.updateById($.copy(req, JcxGoods.class));
    return new GoodsUpdateByIdRes();

  }

  public @Override DynamicsPage<JcxGoodsExportQueryPageListInfoRes> queryPageList(GoodsExportQueryPageListReq req) {
    return jcxGoodsService.queryPageList(req);
  }

  public @Override void queryPageListExport(GoodsExportQueryPageListReq req) {
    DynamicsPage<JcxGoodsExportQueryPageListInfoRes> page = queryPageList(req);
    List<JcxGoodsExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<JcxGoodsExportQueryPageListInfoRes> listInfoRes = $.copyList(list, JcxGoodsExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(JcxGoodsExportQueryPageListInfoRes.class, listInfoRes, "商品信息");
  }

  public @Override GoodsImportRes importData(@RequestParam("file") MultipartFile file) {
    List<JcxGoodsImportReq> reqList = PoiExcelUtil.readData(file, new GoodsImportListener(), JcxGoodsImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<JcxGoods> readList = $.copyList(reqList, JcxGoods.class);
    boolean bool = jcxGoodsService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new GoodsImportRes().setCount(c);
  }

  public @Override GoodsQueryByIdListRes queryByIdListRes(GoodsQueryByIdListReq req) {
    MPJLambdaWrapper<JcxGoods> q = new MPJLambdaWrapper<JcxGoods>(JcxGoods.class)
        .selectAll(JcxGoods.class).in(JcxGoods::getId, req.getIdList());
    List<JcxGoods> list = this.jcxGoodsService.list(q);
    List<JcxGoodsDto> dataList = $.copyList(list, JcxGoodsDto.class);
    return new GoodsQueryByIdListRes().setDataList(dataList);
  }
}
