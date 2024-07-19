package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersion;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 排程版本(ApsSchedulingDayConfigVersion)查询对象返回
 *
 * @author peanut
 * @since 2024-07-19 19:19:56
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionDto extends BaseEntityDto {

  /***
   *  工厂ID
   */
  @NotNull(message = "工厂ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;
  /***
   *  排程版本号
   */
  @NotBlank(message = "排程版本号不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String schedulingDayVersionNo;
  /***
   *  排程日期
   */
  @NotNull(message = "排程日期不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDateTime schedulingDay;
  /***
   *  是否默认 0 否,1 是
   */
  @NotNull(message = "是否默认 0 否,1 是不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Boolean isDefault;

}


