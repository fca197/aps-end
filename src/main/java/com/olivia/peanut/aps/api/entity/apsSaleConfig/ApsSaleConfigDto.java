package com.olivia.peanut.aps.api.entity.apsSaleConfig;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * (ApsSaleConfig)查询对象返回
 *
 * @author peanut
 * @since 2024-03-29 23:10:39
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSaleConfigDto extends BaseEntityDto {

  @NotNull(message = "父级不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long parentId;
  @NotNull(message = "销售编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String saleCode;
  @NotNull(message = "销售名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String saleName;
  private Integer supplierStatus;
  @NotNull(message = "是否值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer isValue;
  private List<? extends ApsSaleConfigDto> children;

}


