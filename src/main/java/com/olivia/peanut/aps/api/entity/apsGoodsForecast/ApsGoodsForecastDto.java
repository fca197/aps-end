package com.olivia.peanut.aps.api.entity.apsGoodsForecast;

// import com.alibaba.fastjson2.annotation.JSONField;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 预测表(ApsGoodsForecast)查询对象返回
 *
 * @author makejava
 * @since 2024-12-15 12:47:15
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastDto extends BaseEntityDto {

  /***
   *  商品ID
   */
  //@JSONField(label = "goodsId")
  @NotNull(message = "商品ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Long goodsId;
  /***
   *  预测编码
   */
  @NotBlank(message = "预测编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "forecastNo")

  private String forecastNo;
  /***
   *  预测名称
   */
  @NotBlank(message = "预测名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "forecastName")

  private String forecastName;
  /***
   *  开始时间
   */
  @NotBlank(message = "开始时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "forecastBeginDate")

  private String forecastBeginDate;
  /***
   *  结束时间
   */
  @NotBlank(message = "结束时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "forecastEndDate")

  private String forecastEndDate;
  //  @NotBlank(message = "${column.comment}不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "month")

  private String month;
  //  @NotBlank(message = "${column.comment}不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "months")

  private String months;
  //@JSONField(label = "forecastStatus")
//  @NotNull(message = "${column.comment}不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Integer forecastStatus;

}


