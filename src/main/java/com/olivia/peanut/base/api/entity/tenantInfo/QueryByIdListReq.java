package com.olivia.peanut.base.api.entity.tenantInfo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class QueryByIdListReq {

  @NotNull(message = "请选择操作数据")
  @Size(min = 1, max = 1000, message = "所选条数在{min}-{max}之间")
  private List<Long> idList;

}
