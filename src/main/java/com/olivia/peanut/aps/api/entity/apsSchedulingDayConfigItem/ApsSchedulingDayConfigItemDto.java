package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigItem;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 排程版本配置表(ApsSchedulingDayConfigItem)查询对象返回
 *
 * @author peanut
 * @since 2024-07-19 19:19:53
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigItemDto extends BaseEntityDto {

  /***
   *  排程版本ID
   */
  @NotNull(message = "排程版本ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long schedulingDayId;
  /***
   *  工艺路径ID
   */
  @NotNull(message = "工艺路径ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long processId;
  /***
   *  车间ID
   */
  @NotNull(message = "车间ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long roomId;
  /***
   *  状态ID
   */
  @NotNull(message = "状态ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long statusId;
  /***
   *  配置类型 sale,part,bom ,sleep
   */
  @NotBlank(message = "配置类型 sale,project,bom ,sleep不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String configBizType;
  /***
   *  配置业务ID
   */
  @NotNull(message = "配置业务ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long configBizId;
  /***
   *  配置业务名称
   */
  @NotBlank(message = "配置业务名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String configBizName;
  /***
   *  配置业务数量
   */
  @NotNull(message = "配置业务数量不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long configBizNum;
  /***
   *  配置业务耗时(秒)
   */
  @NotNull(message = "配置业务耗时(秒)不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long configBizTime;
  /***
   *  是否默认
   */
  @NotNull(message = "是否默认不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Boolean isDefault;

}


