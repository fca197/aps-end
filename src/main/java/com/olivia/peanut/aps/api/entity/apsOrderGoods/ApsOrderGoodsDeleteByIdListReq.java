package com.olivia.peanut.aps.api.entity.apsOrderGoods;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrderGoods)根据ID删除多个入参
 *
 * @author peanut
 * @since 2024-04-09 13:19:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


  public void checkParam() {
  }

}

