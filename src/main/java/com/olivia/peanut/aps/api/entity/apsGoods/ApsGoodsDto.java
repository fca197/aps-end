package com.olivia.peanut.aps.api.entity.apsGoods;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * (ApsGoods)查询对象返回
 *
 * @author peanut
 * @since 2024-03-29 16:11:23
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsDto extends BaseEntityDto {

  @NotNull(message = "工厂不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;
  @NotBlank(message = "商品编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String goodsName;
  @NotNull(message = "商品备注不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String goodsRemark;

  //  @NotNull(message = "加工路径不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long processPathId;
  private String processPathName;

  //  @NotNull(message = "制造路径不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long produceProcessId;
  private String produceProcessName;

}


