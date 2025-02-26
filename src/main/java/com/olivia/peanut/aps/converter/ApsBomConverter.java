package com.olivia.peanut.aps.converter;

import com.olivia.peanut.aps.api.entity.apsBom.*;
import com.olivia.peanut.aps.model.ApsBom;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApsBomConverter {
  ApsBomConverter INSTANCE = Mappers.getMapper(ApsBomConverter.class);

  ApsBom insertReq(ApsBomInsertReq req);

  ApsBom updateReq(ApsBomUpdateByIdReq req);

  List<ApsBomDto> queryListRes(List<ApsBom> list);

  List<ApsBomExportQueryPageListInfoRes> queryPageListRes(List<ApsBom> list);

  List<ApsBom> importReq(List<ApsBomImportReq> reqList);
}

