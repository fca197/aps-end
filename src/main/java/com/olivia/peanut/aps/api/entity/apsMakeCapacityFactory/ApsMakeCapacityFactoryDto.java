package com.olivia.peanut.aps.api.entity.apsMakeCapacityFactory;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * (ApsMakeCapacityFactory)查询对象返回
 *
 * @author peanut
 * @since 2024-04-13 12:05:05
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMakeCapacityFactoryDto extends BaseEntityDto {

  @NotNull(message = "工厂不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;

  private String factoryName;
  //  @NotNull(message = "容量不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long makeCapacityQuantity;
  private Integer year;
  private Integer month;
  private Integer dayMin1;
  private Integer dayMax1;
  private Integer dayMin2;
  private Integer dayMax2;
  private Integer dayMin3;
  private Integer dayMax3;
  private Integer dayMin4;
  private Integer dayMax4;
  private Integer dayMin5;
  private Integer dayMax5;
  private Integer dayMin6;
  private Integer dayMax6;
  private Integer dayMin7;
  private Integer dayMax7;
  private Integer dayMin8;
  private Integer dayMax8;
  private Integer dayMin9;
  private Integer dayMax9;
  private Integer dayMin10;
  private Integer dayMax10;
  private Integer dayMin11;
  private Integer dayMax11;
  private Integer dayMin12;
  private Integer dayMax12;
  private Integer dayMin13;
  private Integer dayMax13;
  private Integer dayMin14;
  private Integer dayMax14;
  private Integer dayMin15;
  private Integer dayMax15;
  private Integer dayMin16;
  private Integer dayMax16;
  private Integer dayMin17;
  private Integer dayMax17;
  private Integer dayMin18;
  private Integer dayMax18;
  private Integer dayMin19;
  private Integer dayMax19;
  private Integer dayMin20;
  private Integer dayMax20;
  private Integer dayMin21;
  private Integer dayMax21;
  private Integer dayMin22;
  private Integer dayMax22;
  private Integer dayMin23;
  private Integer dayMax23;
  private Integer dayMin24;
  private Integer dayMax24;
  private Integer dayMin25;
  private Integer dayMax25;
  private Integer dayMin26;
  private Integer dayMax26;
  private Integer dayMin27;
  private Integer dayMax27;
  private Integer dayMin28;
  private Integer dayMax28;
  private Integer dayMin29;
  private Integer dayMax29;
  private Integer dayMin30;
  private Integer dayMax30;
  private Integer dayMin31;
  private Integer dayMax31;

  @NotNull(message = "容量配置不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private List<MakeCapacityConfig> makeCapacityConfigList;

}


