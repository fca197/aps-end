package com.olivia.peanut.base.api.entity.baseUserRoleGroup;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户角色组表(BaseUserRoleGroup)查询对象返回
 *
 * @author peanut
 * @since 2024-07-31 14:36:04
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseUserRoleGroupImportReq extends BaseUserRoleGroupDto {


  public void checkParam() {
  }

}

