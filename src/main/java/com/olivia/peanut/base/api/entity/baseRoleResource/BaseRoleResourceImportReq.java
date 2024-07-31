package com.olivia.peanut.base.api.entity.baseRoleResource;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 角色资源表(BaseRoleResource)查询对象返回
 *
 * @author peanut
 * @since 2024-07-31 14:35:01
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleResourceImportReq extends BaseRoleResourceDto {


  public void checkParam() {
  }

}


