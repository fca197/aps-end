package com.olivia.peanut.portal.api.entity.districtCode;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (DistrictCode)根据ID删除多个入参
 *
 * @author peanut
 * @since 2024-04-09 13:19:06
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class DistrictCodeDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


  public void checkParam() {
  }

}

