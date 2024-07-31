package com.olivia.peanut.base.api.entity.baseRoleGroup;

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
 * 角色组表(BaseRoleGroup)查询对象返回
 *
 * @author peanut
 * @since 2024-07-31 14:33:37
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleGroupDto extends BaseEntityDto {

  /***
   *  角色组编码
   */
  @NotBlank(message = "角色组编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String roleGroupCode;
  /***
   *  角色组名称
   */
  @NotBlank(message = "角色组名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String roleGroupName;

}


