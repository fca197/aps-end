package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.baseSupplier.*;
import com.olivia.peanut.base.mapper.BaseSupplierMapper;
import com.olivia.peanut.base.model.BaseSupplier;
import com.olivia.peanut.base.service.BaseSupplierService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (BaseSupplier)表服务实现类
 *
 * @author peanut
 * @since 2024-03-28 15:35:38
 */
@Service("baseSupplierService")
@Transactional
public class BaseSupplierServiceImpl extends MPJBaseServiceImpl<BaseSupplierMapper, BaseSupplier> implements BaseSupplierService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override BaseSupplierQueryListRes queryList(BaseSupplierQueryListReq req) {

    MPJLambdaWrapper<BaseSupplier> q = getWrapper(req.getData());
    List<BaseSupplier> list = this.list(q);

    List<BaseSupplierDto> dataList = list.stream().map(t -> $.copy(t, BaseSupplierDto.class)).collect(Collectors.toList());

    return new BaseSupplierQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<BaseSupplierExportQueryPageListInfoRes> queryPageList(BaseSupplierExportQueryPageListReq req) {

    DynamicsPage<BaseSupplier> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<BaseSupplier> q = getWrapper(req.getData());
    List<BaseSupplierExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<BaseSupplier> list = this.page(page, q);
      IPage<BaseSupplierExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BaseSupplierExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BaseSupplierExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<BaseSupplierExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BaseSupplierExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }


  @SetUserName
  public @Override void setName(List<? extends BaseSupplierDto> BaseSupplierDtoList) {

  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<BaseSupplier> getWrapper(BaseSupplierDto obj) {
    MPJLambdaWrapper<BaseSupplier> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), BaseSupplier::getId, obj.getId())
          .likeRight(StringUtils.isNoneBlank(obj.getSupplierName()), BaseSupplier::getSupplierName, obj.getSupplierName())
          .eq(StringUtils.isNoneBlank(obj.getSupplierCode()), BaseSupplier::getSupplierCode, obj.getSupplierCode())
          .likeRight(StringUtils.isNoneBlank(obj.getSupplierPhone()), BaseSupplier::getSupplierPhone, obj.getSupplierPhone())
          .eq(StringUtils.isNoneBlank(obj.getSupplierEmail()), BaseSupplier::getSupplierEmail, obj.getSupplierEmail())
          .eq(StringUtils.isNoneBlank(obj.getSupplierAddr()), BaseSupplier::getSupplierAddr, obj.getSupplierAddr())
          .eq(StringUtils.isNoneBlank(obj.getSupplierRemark()), BaseSupplier::getSupplierRemark, obj.getSupplierRemark())
          .eq(Objects.nonNull(obj.getSupplierStatus()), BaseSupplier::getSupplierStatus, obj.getSupplierStatus())
          .eq(Objects.nonNull(obj.getVersionNum()), BaseSupplier::getVersionNum, obj.getVersionNum())

      ;
    }
    q.orderByDesc(BaseSupplier::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BaseSupplier> page) {

    ServiceComment.header(page, "BaseSupplierService#queryPageList");

  }


}

