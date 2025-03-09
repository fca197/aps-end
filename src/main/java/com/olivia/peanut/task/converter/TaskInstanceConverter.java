package com.olivia.peanut.task.converter;

import com.olivia.peanut.task.api.entity.taskInstance.*;
import com.olivia.peanut.task.model.TaskInstance;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskInstanceConverter {
  TaskInstanceConverter INSTANCE = Mappers.getMapper(TaskInstanceConverter.class);

  TaskInstance insertReq(TaskInstanceInsertReq req);

  TaskInstance updateReq(TaskInstanceUpdateByIdReq req);

  List<TaskInstanceDto> queryListRes(List<TaskInstance> list);

  List<TaskInstanceExportQueryPageListInfoRes> queryPageListRes(List<TaskInstance> list);

  List<TaskInstance> importReq(List<TaskInstanceImportReq> reqList);
}

