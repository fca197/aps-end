package com.olivia.peanut.aps.api.entity.apsRollingForecastOrderItem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 滚动预测订单节点表(ApsRollingForecastOrderItem)查询对象返回
 *
 * @author peanut
 * @since 2024-07-16 10:31:19
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRollingForecastOrderItemImportReq extends ApsRollingForecastOrderItemDto {


  public void checkParam() {
  }

}


