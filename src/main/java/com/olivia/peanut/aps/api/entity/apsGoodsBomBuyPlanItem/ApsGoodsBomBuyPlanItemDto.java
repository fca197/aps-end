package com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlanItem;

// import com.alibaba.fastjson2.annotation.JSONField;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 订单商品零件汇总表(ApsGoodsBomBuyPlanItem)查询对象返回
 *
 * @author makejava
 * @since 2024-12-15 12:20:16
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsBomBuyPlanItemDto extends BaseEntityDto {

  /***
   *  购买计划ID
   */
  //@JSONField(label = "buyPlanId")
  @NotNull(message = "购买计划ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Long buyPlanId;
  /***
   *  零件ID
   */
  //@JSONField(label = "bomId")
  @NotNull(message = "零件ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Long bomId;
  /***
   *  商品零件ID
   */
  //@JSONField(label = "goodsBomId")
  @NotNull(message = "商品零件ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Long goodsBomId;
  /***
   *  bom 编码
   */
  @NotBlank(message = "bom 编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "bomCode")

  private String bomCode;
  /***
   *  bom 名称
   */
  @NotBlank(message = "bom 名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "bomName")

  private String bomName;
  /***
   *  使用量
   */
  //@JSONField(label = "bomUsage")
  @NotNull(message = "使用量不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal bomUsage;
  /***
   *  规格
   */
  @NotBlank(message = "规格不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "bomUnit")

  private String bomUnit;
  /***
   *  成本价
   */
  //@JSONField(label = "bomCostPrice")
  @NotNull(message = "成本价不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal bomCostPrice;
  /***
   *  规格
   */
  @NotBlank(message = "规格不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "bomCostPriceUnit")

  private String bomCostPriceUnit;
  /***
   *  使用工位
   */
  //@JSONField(label = "bomUseWorkStation")
  @NotNull(message = "使用工位不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Long bomUseWorkStation;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay1")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay1;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay2")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay2;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay3")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay3;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay4")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay4;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay5")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay5;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay6")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay6;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay7")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay7;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay8")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay8;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay9")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay9;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay10")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay10;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay11")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay11;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay12")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay12;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay13")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay13;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay14")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay14;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay15")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay15;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay16")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay16;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay17")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay17;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay18")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay18;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay19")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay19;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay20")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay20;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay21")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay21;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay22")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay22;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay23")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay23;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay24")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay24;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay25")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay25;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay26")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay26;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay27")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay27;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay28")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay28;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay29")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay29;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay30")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay30;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay31")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay31;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay32")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay32;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay33")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay33;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay34")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay34;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay35")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay35;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay36")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay36;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay37")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay37;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay38")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay38;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay39")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay39;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay40")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay40;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay41")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay41;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay42")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay42;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay43")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay43;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay44")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay44;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay45")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay45;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay46")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay46;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay47")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay47;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay48")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay48;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay49")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay49;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay50")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay50;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay51")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay51;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay52")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay52;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay53")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay53;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay54")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay54;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay55")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay55;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay56")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay56;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay57")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay57;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay58")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay58;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay59")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay59;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay60")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay60;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay61")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay61;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay62")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay62;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay63")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay63;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay64")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay64;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay65")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay65;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay66")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay66;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay67")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay67;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay68")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay68;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay69")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay69;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay70")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay70;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay71")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay71;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay72")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay72;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay73")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay73;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay74")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay74;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay75")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay75;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay76")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay76;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay77")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay77;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay78")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay78;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay79")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay79;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay80")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay80;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay81")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay81;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay82")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay82;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay83")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay83;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay84")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay84;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay85")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay85;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay86")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay86;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay87")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay87;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay88")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay88;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay89")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay89;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay90")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay90;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay91")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay91;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay92")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay92;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay93")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay93;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay94")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay94;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay95")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay95;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay96")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay96;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay97")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay97;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay98")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay98;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay99")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay99;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay100")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay100;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay101")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay101;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay102")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay102;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay103")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay103;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay104")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay104;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay105")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay105;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay106")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay106;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay107")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay107;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay108")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay108;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay109")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay109;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay110")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay110;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay111")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay111;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay112")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay112;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay113")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay113;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay114")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay114;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay115")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay115;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay116")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay116;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay117")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay117;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay118")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay118;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay119")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay119;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay120")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay120;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay121")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay121;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay122")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay122;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay123")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay123;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay124")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay124;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay125")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay125;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay126")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay126;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay127")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay127;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay128")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay128;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay129")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay129;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay130")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay130;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay131")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay131;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay132")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay132;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay133")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay133;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay134")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay134;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay135")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay135;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay136")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay136;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay137")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay137;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay138")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay138;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay139")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay139;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay140")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay140;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay141")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay141;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay142")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay142;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay143")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay143;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay144")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay144;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay145")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay145;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay146")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay146;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay147")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay147;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay148")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay148;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay149")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay149;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay150")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay150;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay151")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay151;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay152")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay152;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay153")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay153;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay154")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay154;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay155")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay155;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay156")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay156;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay157")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay157;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay158")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay158;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay159")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay159;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay160")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay160;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay161")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay161;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay162")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay162;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay163")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay163;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay164")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay164;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay165")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay165;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay166")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay166;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay167")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay167;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay168")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay168;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay169")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay169;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay170")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay170;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay171")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay171;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay172")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay172;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay173")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay173;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay174")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay174;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay175")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay175;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay176")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay176;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay177")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay177;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay178")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay178;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay179")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay179;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay180")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay180;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay181")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay181;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay182")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay182;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay183")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay183;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay184")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay184;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay185")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay185;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay186")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay186;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay187")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay187;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay188")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay188;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay189")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay189;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay190")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay190;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay191")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay191;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay192")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay192;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay193")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay193;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay194")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay194;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay195")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay195;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay196")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay196;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay197")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay197;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay198")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay198;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay199")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay199;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay200")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay200;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay201")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay201;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay202")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay202;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay203")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay203;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay204")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay204;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay205")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay205;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay206")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay206;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay207")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay207;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay208")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay208;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay209")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay209;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay210")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay210;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay211")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay211;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay212")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay212;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay213")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay213;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay214")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay214;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay215")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay215;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay216")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay216;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay217")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay217;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay218")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay218;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay219")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay219;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay220")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay220;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay221")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay221;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay222")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay222;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay223")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay223;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay224")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay224;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay225")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay225;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay226")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay226;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay227")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay227;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay228")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay228;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay229")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay229;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay230")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay230;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay231")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay231;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay232")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay232;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay233")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay233;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay234")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay234;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay235")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay235;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay236")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay236;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay237")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay237;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay238")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay238;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay239")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay239;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay240")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay240;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay241")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay241;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay242")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay242;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay243")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay243;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay244")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay244;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay245")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay245;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay246")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay246;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay247")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay247;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay248")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay248;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay249")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay249;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay250")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay250;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay251")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay251;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay252")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay252;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay253")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay253;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay254")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay254;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay255")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay255;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay256")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay256;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay257")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay257;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay258")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay258;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay259")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay259;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay260")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay260;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay261")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay261;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay262")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay262;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay263")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay263;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay264")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay264;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay265")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay265;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay266")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay266;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay267")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay267;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay268")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay268;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay269")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay269;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay270")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay270;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay271")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay271;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay272")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay272;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay273")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay273;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay274")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay274;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay275")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay275;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay276")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay276;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay277")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay277;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay278")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay278;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay279")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay279;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay280")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay280;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay281")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay281;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay282")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay282;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay283")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay283;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay284")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay284;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay285")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay285;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay286")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay286;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay287")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay287;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay288")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay288;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay289")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay289;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay290")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay290;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay291")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay291;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay292")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay292;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay293")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay293;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay294")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay294;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay295")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay295;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay296")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay296;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay297")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay297;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay298")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay298;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay299")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay299;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay300")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay300;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay301")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay301;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay302")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay302;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay303")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay303;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay304")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay304;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay305")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay305;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay306")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay306;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay307")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay307;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay308")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay308;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay309")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay309;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay310")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay310;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay311")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay311;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay312")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay312;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay313")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay313;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay314")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay314;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay315")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay315;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay316")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay316;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay317")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay317;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay318")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay318;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay319")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay319;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay320")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay320;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay321")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay321;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay322")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay322;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay323")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay323;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay324")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay324;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay325")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay325;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay326")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay326;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay327")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay327;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay328")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay328;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay329")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay329;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay330")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay330;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay331")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay331;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay332")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay332;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay333")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay333;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay334")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay334;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay335")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay335;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay336")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay336;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay337")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay337;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay338")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay338;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay339")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay339;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay340")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay340;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay341")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay341;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay342")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay342;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay343")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay343;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay344")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay344;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay345")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay345;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay346")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay346;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay347")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay347;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay348")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay348;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay349")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay349;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay350")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay350;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay351")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay351;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay352")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay352;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay353")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay353;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay354")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay354;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay355")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay355;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay356")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay356;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay357")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay357;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay358")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay358;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay359")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay359;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay360")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay360;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay361")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay361;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay362")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay362;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay363")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay363;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay364")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay364;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay365")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay365;
  /***
   *  使用时间
   */
  //@JSONField(label = "bomUseDay366")
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private String bomUseDay366;
  /***
   *  工厂ID
   */
  //@JSONField(label = "factoryId")
  @NotNull(message = "工厂ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Long factoryId;
  /***
   *  是否关注
   */
  //@JSONField(label = "isFollow")
  @NotNull(message = "是否关注不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Boolean isFollow;

}


