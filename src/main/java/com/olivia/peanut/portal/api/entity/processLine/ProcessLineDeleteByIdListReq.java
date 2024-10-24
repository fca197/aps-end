package com.olivia.peanut.portal.api.entity.processLine;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 流水线信息(ProcessLine)根据ID删除多个入参
 *
 * @author makejava
 * @since 2024-03-11 10:55:20
 */
@Accessors(chain = true)
@Getter
@Setter

public class ProcessLineDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


  public void checkParam() {
  }

}

