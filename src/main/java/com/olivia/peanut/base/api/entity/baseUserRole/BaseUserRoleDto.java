package com.olivia.peanut.base.api.entity.baseUserRole;

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
 * 用户角色表(BaseUserRole)查询对象返回
 *
 * @author peanut
 * @since 2024-07-31 14:36:03
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseUserRoleDto extends BaseEntityDto {

  /***
   *  用户ID
   */
  @NotNull(message = "用户ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long userId;
  /***
   *  角色ID
   */
  @NotNull(message = "角色ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long roleId;

}


