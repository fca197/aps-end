package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig;

import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigItem.ApsSchedulingDayConfigItemDto;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import com.olivia.sdk.utils.Str;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 排程版本表(ApsSchedulingDayConfig)查询对象返回
 *
 * @author peanut
 * @since 2024-07-19 19:19:50
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigDto extends BaseEntityDto {

  /***
   *  工厂ID
   */
  @NotNull(message = "工厂ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;
  private String factoryName;
  /***
   *  工艺路径ID
   */
  @NotNull(message = "工艺路径ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long processId;
  private String processName;
  /***
   *  排程版本号
   */
  @NotBlank(message = "排程版本号不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String schedulingDayNo;
  /***
   *  排程版本名称
   */
  @NotBlank(message = "排程版本名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String schedulingDayName;
  /***
   *  是否默认
   */
  @NotNull(message = "是否默认不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Boolean isDefault;

  @NotNull(message = "排程版本明细不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @Size(min = 1, message = "排程版本明细不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private List<ApsSchedulingDayConfigItemDto> schedulingDayConfigItemDtoList;

  public String getIsDefaultStr() {
    return Str.booleanToStr(isDefault);
  }
}


