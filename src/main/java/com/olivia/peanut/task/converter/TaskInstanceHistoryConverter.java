package com.olivia.peanut.task.converter;

import com.olivia.peanut.task.api.entity.taskInstanceHistory.*;
import com.olivia.peanut.task.model.TaskInstanceHistory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
    (unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskInstanceHistoryConverter {

  TaskInstanceHistoryConverter INSTANCE = Mappers.getMapper(TaskInstanceHistoryConverter.class);


  TaskInstanceHistory insertReq(TaskInstanceHistoryInsertReq req);

  //  @Mapping(source = "taskOutput", target = "taskOutput")
  TaskInstanceHistory updateReq(TaskInstanceHistoryUpdateByIdReq req);

  List<TaskInstanceHistoryDto> queryListRes(List<TaskInstanceHistory> list);

  List<TaskInstanceHistoryExportQueryPageListInfoRes> queryPageListRes(List<TaskInstanceHistory> list);

  List<TaskInstanceHistory> importReq(List<TaskInstanceHistoryImportReq> reqList);
}

