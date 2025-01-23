package com.olivia.peanut.aps.api.entity.apsProjectConfig;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * (ApsProjectConfig)查询对象返回
 *
 * @author peanut
 * @since 2024-03-30 16:21:20
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProjectConfigDto extends BaseEntityDto {

  @NotNull(message = "销售编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String saleCode;
  @NotNull(message = "销售名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String saleName;
  //  @NotNull(message = "供应商状态不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer supplierStatus;
  @NotNull(message = "是否值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer isValue;
  @NotNull(message = "父级不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long parentId;
  private List<? extends ApsProjectConfigDto> children;
}


