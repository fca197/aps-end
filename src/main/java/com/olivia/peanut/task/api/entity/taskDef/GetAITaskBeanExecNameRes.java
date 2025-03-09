package com.olivia.peanut.task.api.entity.taskDef;

import com.olivia.sdk.model.KVEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class GetAITaskBeanExecNameRes {
  private List<KVEntity> list;
}
