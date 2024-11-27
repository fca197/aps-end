package com.olivia.peanut.aps.api.entity.apsLogisticsPath;

import com.olivia.peanut.aps.api.entity.apsLogisticsPathItem.ApsLogisticsPathItemDto;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 物流路径表(ApsLogisticsPath)查询对象返回
 *
 * @author peanut
 * @since 2024-07-18 13:27:37
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsLogisticsPathDto extends BaseEntityDto {

  /***
   *  物流路径编码
   */
  @NotBlank(message = "物流路径编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String logisticsPathCode;
  /***
   *  物流路径名称
   */
  @NotBlank(message = "物流路径名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String logisticsPathName;
  /***
   *  备注
   */
  @NotBlank(message = "备注不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String logisticsPathRemark;
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
  private String factoryName;

  @NotNull(message = "物流路径明细不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @Size(min = 1, max = 100, message = "物流路径明细不能超过100条", groups = {InsertCheck.class, UpdateCheck.class})
  private List<ApsLogisticsPathItemDto> apsLogisticsPathItemList;
}


