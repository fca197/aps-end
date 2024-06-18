package com.olivia.peanut.portal.api.entity.jcxBuyPlan;

import com.olivia.sdk.utils.$;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class JcxBuyPlanUpdateStatusReq {

  @NotNull(message = "id不能为空")
  private Long id;
  @NotNull(message = "status不能为空")
  private String planStatus;
  @NotNull(message = "versionNum不能为空")
  private Integer versionNum;

  public void checkParam() {
    $.requireNonNullCanIgnoreException(JcxBuyPlanStatusEnum.codeMsg.containsKey(planStatus), "status值错误");
  }
}
