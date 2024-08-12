package com.olivia.peanut.base.api.entity.baseRoleGroupResource;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 角色组资源表(BaseRoleGroupResource)查询对象返回
 *
 * @author peanut
 * @since 2024-08-09 15:42:34
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleGroupResourceImportReq extends BaseRoleGroupResourceDto {


  public void checkParam() {
  }

}

