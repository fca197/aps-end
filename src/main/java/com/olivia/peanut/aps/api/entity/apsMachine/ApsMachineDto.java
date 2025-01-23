package com.olivia.peanut.aps.api.entity.apsMachine;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * aps 生产机器(ApsMachine)查询对象返回
 *
 * @author makejava
 * @since 2024-10-24 16:31:16
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMachineDto extends BaseEntityDto {

  @NotNull(groups = {UpdateCheck.class, InsertCheck.class}, message = "工厂不能为空")
  private Long factoryId;
  private String factoryName;
  /***
   *  机器编号
   */
  @NotBlank(message = "机器编号不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String machineNo;
  /***
   *  机器名称
   */
  @NotBlank(message = "机器名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String machineName;

  @NotNull(message = "排序不能为空")
  private Long sortIndex;
}


