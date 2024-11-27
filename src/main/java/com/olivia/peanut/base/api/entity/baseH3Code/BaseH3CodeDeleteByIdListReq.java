package com.olivia.peanut.base.api.entity.baseH3Code;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * H3对应的值(BaseH3Code)根据ID删除多个入参
 *
 * @author makejava
 * @since 2024-11-19 16:09:18
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseH3CodeDeleteByIdListReq {
  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


}

