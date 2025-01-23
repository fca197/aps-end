package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoods.*;
import com.olivia.peanut.aps.mapper.ApsGoodsMapper;
import com.olivia.peanut.aps.model.ApsGoods;
import com.olivia.peanut.aps.service.ApsGoodsService;
import com.olivia.peanut.aps.service.ApsProcessPathService;
import com.olivia.peanut.aps.service.ApsProduceProcessService;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (ApsGoods)表服务实现类
 *
 * @author peanut
 * @since 2024-03-29 16:11:23
 */
@Service("apsGoodsService")
@Transactional
public class ApsGoodsServiceImpl extends MPJBaseServiceImpl<ApsGoodsMapper, ApsGoods> implements ApsGoodsService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  ApsProcessPathService apsProcessPathService;
  @Resource
  SetNameService setNameService;

  public @Override ApsGoodsQueryListRes queryList(ApsGoodsQueryListReq req) {

    MPJLambdaWrapper<ApsGoods> q = getWrapper(req.getData());
    List<ApsGoods> list = this.list(q);

    List<ApsGoodsDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsDto.class)).collect(Collectors.toList());

    return new ApsGoodsQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsGoodsExportQueryPageListInfoRes> queryPageList(ApsGoodsExportQueryPageListReq req) {

    DynamicsPage<ApsGoods> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoods> q = getWrapper(req.getData());
    List<ApsGoodsExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoods> list = this.page(page, q);
      IPage<ApsGoodsExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<ApsGoodsExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsExportQueryPageListInfoRes.class);
    //setName(listInfoRes);
    ((ApsGoodsServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  //  @SetUserName
  public @Override void setName(List<? extends ApsGoodsDto> apsGoodsDtoList) {

    setNameService.setName(apsGoodsDtoList,//
        SetNamePojoUtils.OP_USER_NAME
        , SetNamePojoUtils.getSetNamePojo(ApsProduceProcessService.class, "produceProcessName", "produceProcessId", "produceProcessName"),
        SetNamePojoUtils.getSetNamePojo(ApsProcessPathService.class, "processPathName", "processPathId", "processPathName")

    );

//    if (CollUtil.isEmpty(ApsGoodsDtoList)) {
//      return;
//    }
//
//    Set<Long> pathSetId = ApsGoodsDtoList.stream().map(ApsGoodsDto::getProcessPathId).collect(Collectors.toSet());
//    Map<Long, String> pNameMap = apsProcessPathService.list(Wrappers.<ApsProcessPath>lambdaQuery().in(BaseEntity::getId, pathSetId)).stream()
//        .collect(Collectors.toMap(BaseEntity::getId, ApsProcessPath::getProcessPathName));
//    ApsGoodsDtoList.forEach(t -> t.setProcessPathName(pNameMap.get(t.getProcessPathId())));
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ApsGoods> getWrapper(ApsGoodsDto obj) {
    MPJLambdaWrapper<ApsGoods> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), ApsGoods::getId, obj.getId())
          .eq(StringUtils.isNoneBlank(obj.getGoodsName()), ApsGoods::getGoodsName, obj.getGoodsName())
          .eq(StringUtils.isNoneBlank(obj.getGoodsRemark()), ApsGoods::getGoodsRemark, obj.getGoodsRemark())
          .eq(Objects.nonNull(obj.getTenantId()), ApsGoods::getTenantId, obj.getTenantId())
          .eq(Objects.nonNull(obj.getVersionNum()), ApsGoods::getVersionNum, obj.getVersionNum())

      ;
    }
    q.orderByDesc(ApsGoods::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoods> page) {

    ServiceComment.header(page, "ApsGoodsService#queryPageList");

  }


}

