package com.olivia.peanut.task.converter;

import com.olivia.peanut.task.api.entity.taskDef.*;
import com.olivia.peanut.task.model.TaskDef;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
    (unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskDefConverter {
  TaskDefConverter INSTANCE = Mappers.getMapper(TaskDefConverter.class);


  //  @Mapping(source = "taskName",target = "taskName")
  TaskDef insertReq(TaskDefInsertReq req);

  TaskDef updateReq(TaskDefUpdateByIdReq req);

  List<TaskDefDto> queryListRes(List<TaskDef> list);

  List<TaskDefExportQueryPageListInfoRes> queryPageListRes(List<TaskDef> list);

  List<TaskDef> importReq(List<TaskDefImportReq> reqList);
}

