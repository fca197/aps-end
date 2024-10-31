package com.olivia.peanut.portal.api.entity.shiftItem;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ShiftItem)根据ID删除多个入参
 *
 * @author peanut
 * @since 2024-04-04 12:10:16
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ShiftItemDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


  public void checkParam() {
  }

}

