package com.olivia.peanut.aps.api.entity.apsGoodsSaleItem;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsSaleItem)查询对象返回
 *
 * @author peanut
 * @since 2024-03-30 10:38:36
 */
//@Accessors(chain=true)
@Getter
@Setter
@Accessors(chain = true)
@SuppressWarnings("serial")
public class ApsGoodsSaleItemDto extends BaseEntityDto {

  @NotNull(message = "零件不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsId;
  @NotNull(message = "销售配置不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long saleConfigId;
  @NotNull(message = "是否使用预测数据不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer useForecast;
  private Integer supplierStatus;

  private List<? extends ApsGoodsSaleItemDto> children;

}


