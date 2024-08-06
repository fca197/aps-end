package com.olivia.peanut.base.api.entity.baseResource;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 资源(BaseResource)查询对象返回
 *
 * @author peanut
 * @since 2024-08-06 17:29:01
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseResourceImportReq extends BaseResourceDto {


  public void checkParam() {
  }

}


