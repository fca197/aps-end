package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsStatusDate;

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
 * 订单商品状态表(ApsSchedulingGoodsStatusDate)查询对象返回
 *
 * @author peanut
 * @since 2024-06-14 21:35:09
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsStatusDateImportReq extends ApsSchedulingGoodsStatusDateDto {


  public void checkParam() {
  }

}


