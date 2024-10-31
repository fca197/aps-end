package com.olivia.peanut.aps.api.entity.apsGoodsSaleProjectConfig;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class ApsGoodsSaleProjectConfigSale2ProjectRes {

  private List<Info> dataList;
  private LocalDate bizKey;
  private Long id;

  @Setter
  @Getter
  @Accessors(chain = true)
  public static final class Info {

    private String projectCode;
    private BigDecimal convertCount;
  }
}
