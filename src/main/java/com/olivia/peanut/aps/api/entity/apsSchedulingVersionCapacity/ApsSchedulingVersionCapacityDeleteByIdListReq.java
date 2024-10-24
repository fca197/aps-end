package com.olivia.peanut.aps.api.entity.apsSchedulingVersionCapacity;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersionCapacity)根据ID删除多个入参
 *
 * @author peanut
 * @since 2024-04-18 14:57:34
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionCapacityDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


  public void checkParam() {
  }

}

