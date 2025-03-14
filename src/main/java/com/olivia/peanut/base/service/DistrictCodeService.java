package com.olivia.peanut.base.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.base.api.entity.districtCode.*;
import com.olivia.peanut.enums.DistrictCodeSelectType;

import com.olivia.peanut.base.model.DistrictCode;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * (DistrictCode)表服务接口
 *
 * @author peanut
 * @since 2024-04-09 13:19:07
 */
public interface DistrictCodeService extends MPJBaseService<DistrictCode> {

  DistrictCodeQueryListRes queryList(DistrictCodeQueryListReq req);

  DynamicsPage<DistrictCodeExportQueryPageListInfoRes> queryPageList(DistrictCodeExportQueryPageListReq req);


  void setName(List<? extends DistrictCodeDto> districtCodeDtoList);

  void setDistrictName(List<?> list, DistrictCodeSelectType... selectTypes);

  Map<String, DistrictCode> getDistrictCodeMap(Collection<String> codeList);
}

