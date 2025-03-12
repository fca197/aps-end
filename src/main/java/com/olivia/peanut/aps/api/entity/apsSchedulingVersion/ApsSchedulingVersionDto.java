package com.olivia.peanut.aps.api.entity.apsSchedulingVersion;

import com.olivia.peanut.aps.api.entity.apsSchedulingVersionLimit.ApsSchedulingVersionLimitDto;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * (ApsSchedulingVersion)查询对象返回
 *
 * @author peanut
 * @since 2024-04-13 21:32:14
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionDto extends BaseEntityDto {


  @NotBlank(message = "版本号不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String schedulingVersionNo;
  @NotBlank(message = "版本名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String schedulingVersionName;
  @NotNull(message = "约束名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long schedulingConstraintsId;
  //  @NotBlank(message = "约束名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String schedulingConstraintsName;


  private LocalDate startDate;
  private String headerList;
  private String capacityHeaderList;
  private String capacityDateList;
  @NotNull(message = "排产天数不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer schedulingDayCount;
  //  @NotNull(message = "版本步骤不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer versionStep;

  /**
   * bom 统计截止日期
   */
//  @NotNull(message = "零件统计截止日期不能为空", groups = {InsertCheck.class, UpdateCheck.class})
//  private LocalDate bomTotalEndDate;
  private List<ApsSchedulingVersionLimitDto> limitDtoList;


  /***
   * 使用工厂产能约束
   */
//  //@JSONField(serializeUsing = Boolean2StrFeature.class, deserializeUsing = Str2BooleanConverter.class)
  private Boolean useFactoryMakeCapacity;
  /***
   *  使用产品产能约束
   */
//  //@JSONField(serializeUsing = Boolean2StrFeature.class, deserializeUsing = Str2BooleanConverter.class)
  private Boolean useGoodsMakeCapacity;
  /****
   * 使用销售配置产能约束
   */
//  //@JSONField(serializeUsing = Boolean2StrFeature.class, deserializeUsing = Str2BooleanConverter.class)
  private Boolean useSaleConfigMakeCapacity;
  /****
   * 使用工程配置产能约束
   */
//  //@JSONField(serializeUsing = Boolean2StrFeature.class, deserializeUsing = Str2BooleanConverter.class)
  private Boolean useProjectConfigMakeCapacity;
}


