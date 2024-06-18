package com.olivia.peanut.aps.api.entity.apsGoodsSaleProjectConfig;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * (ApsGoodsSaleProjectConfig)查询对象返回
 *
 * @author peanut
 * @since 2024-04-27 16:07:23
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsSaleProjectConfigDto extends BaseEntityDto {

  @NotNull(message = "商品不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsId;
  @NotNull(message = "工厂不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;
  @NotNull(message = "项目配置不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long projectConfigId;
  @NotNull(message = "项目配置名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String projectConfigName;
  @NotNull(message = "项目配置父级不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long projectConfigParentId;
  @NotNull(message = "数量不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long quantity;
  @NotNull(message = "销售配置不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long saleConfigId;
  @NotNull(message = "销售配置名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String saleConfigName;
  @NotNull(message = "销售配置父级不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long saleConfigParentId;

}


