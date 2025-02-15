package com.olivia.peanut.aps.api.entity.apsStatus;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * (ApsStatus)查询对象返回
 *
 * @author peanut
 * @since 2024-04-01 10:50:12
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsStatusDto extends BaseEntityDto {

  @NotBlank(message = "状态编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String statusCode;
  @NotBlank(message = "状态名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String statusName;
//  @NotNull(message = "是否订单商品初始化不能为空", groups = {InsertCheck.class, UpdateCheck.class})
//  private Boolean isOrderGoodsInit;

  private Long orderStatusId;
}


