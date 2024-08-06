package com.olivia.peanut.aps.api.entity.apsLogisticsPathItem;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 物流路详情径表(ApsLogisticsPathItem)查询对象返回
 *
 * @author peanut
 * @since 2024-07-18 13:27:41
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsLogisticsPathItemDto extends BaseEntityDto {

  /***
   *  物流路径id
   */
  @NotNull(message = "物流路径id不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long logisticsPathId;
  /***
   *  省编码
   */
  @NotBlank(message = "省编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String provinceCode;
  /***
   *  省名称
   */
  @NotBlank(message = "省名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String provinceName;
  /***
   *  市编码
   */
  @NotBlank(message = "市编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String cityCode;
  /***
   *  市名称
   */
  @NotBlank(message = "市名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String cityName;
  /***
   *  运输天数
   */
  @NotNull(message = "运输天数不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer transportDay;
  /***
   *  是否默认
   */
  @NotNull(message = "是否默认不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer isDefault;
  /***
   *  工厂ID
   */
  @NotNull(message = "工厂ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;

}


