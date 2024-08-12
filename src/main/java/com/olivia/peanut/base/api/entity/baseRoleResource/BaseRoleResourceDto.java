package com.olivia.peanut.base.api.entity.baseRoleResource;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 角色资源表(BaseRoleResource)查询对象返回
 *
 * @author peanut
 * @since 2024-08-09 15:42:37
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleResourceDto extends BaseEntityDto {

  /***
   *  角色ID
   */
  @NotNull(message = "角色ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long roleId;
  /***
   *  菜单ID
   */
//  @NotNull(message = "菜单ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long resourceId;

  private String resourceName;


  @NotNull(message = "资源ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private List<Long> resourceIdList;
}


