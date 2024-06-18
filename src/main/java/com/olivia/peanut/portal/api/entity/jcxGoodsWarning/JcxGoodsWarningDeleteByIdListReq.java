package com.olivia.peanut.portal.api.entity.jcxGoodsWarning;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxGoodsWarning)根据ID删除多个入参
 *
 * @author peanut
 * @since 2024-03-24 14:10:55
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxGoodsWarningDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


  public void checkParam() {
  }

}

