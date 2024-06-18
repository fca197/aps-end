package com.olivia.peanut.aps.api.entity.apsSaleConfig;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSaleConfig)根据ID删除多个入参
 *
 * @author peanut
 * @since 2024-03-29 23:10:39
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSaleConfigDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


  public void checkParam() {
  }

}

