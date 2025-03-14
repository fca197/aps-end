package com.olivia.peanut.base.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.base.api.entity.shift.*;
import com.olivia.peanut.base.model.Shift;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (Shift)表服务接口
 *
 * @author peanut
 * @since 2024-04-04 12:10:15
 */
public interface ShiftService extends MPJBaseService<Shift> {

  ShiftQueryListRes queryList(ShiftQueryListReq req);

  DynamicsPage<ShiftExportQueryPageListInfoRes> queryPageList(ShiftExportQueryPageListReq req);


  void setName(List<? extends ShiftDto> shiftDtoList);

  ShiftInsertRes save(ShiftInsertReq req);

  ShiftUpdateByIdRes updateById(ShiftUpdateByIdReq req);

}

