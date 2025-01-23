package com.olivia.peanut.aps.api.entity.apsSchedulingConstraints;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * (ApsSchedulingConstraints)查询对象返回
 *
 * @author peanut
 * @since 2024-04-13 21:32:13
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingConstraintsDto extends BaseEntityDto {

  @NotBlank(message = "约束编码不能为空", groups = {InsertCheck.class})
  private String constraintsNo;
  @NotBlank(message = "约束类型不能为空", groups = {InsertCheck.class})
  private String constraintsName;
  private String constraintsContext;
  private String constraintsRemark;

}


