package com.olivia.peanut.aps.api.entity.apsGoodsBomGroup;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 零件组配置(ApsGoodsBomGroup)查询对象返回
 *
 * @author peanut
 * @since 2024-06-19 16:49:04
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsBomGroupImportReq extends ApsGoodsBomGroupDto {


  public void checkParam() {
  }

}


