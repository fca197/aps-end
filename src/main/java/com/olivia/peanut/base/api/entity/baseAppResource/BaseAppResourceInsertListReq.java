package com.olivia.peanut.base.api.entity.baseAppResource;

import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
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
public class BaseAppResourceInsertListReq {

  @NotNull(message = "应用ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long appId;
  @NotNull(message = "应用资源列表不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private List<Long> appResourceIdList;
}
