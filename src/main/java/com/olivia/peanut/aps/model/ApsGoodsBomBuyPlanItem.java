package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 订单商品零件汇总表(ApsGoodsBomBuyPlanItem)表实体类
 *
 * @author makejava
 * @since 2024-12-15 12:20:14
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_goods_bom_buy_plan_item")
public class ApsGoodsBomBuyPlanItem extends BaseEntity {

  public static final String fieldName = "bomUseDay";
  /***
   *  购买计划ID
   */
  private Long buyPlanId;
  /***
   *  零件ID
   */
  private Long bomId;
  /***
   *  商品零件ID
   */
  private Long goodsBomId;
  /***
   *  bom 编码
   */
  private String bomCode;
  /***
   *  bom 名称
   */
  private String bomName;
  /***
   *  使用量
   */
  private BigDecimal bomUsage;
  /***
   *  规格
   */
  private String bomUnit;
  /***
   *  成本价
   */
  private BigDecimal bomCostPrice;
  /***
   *  规格
   */
  private String bomCostPriceUnit;
  /***
   *  使用工位
   */
  private Long bomUseWorkStation;

  private Integer year;

  /***
   *  使用时间
   */
  private String bomUseDay1;
  /***
   *  使用时间
   */
  private String bomUseDay2;
  /***
   *  使用时间
   */
  private String bomUseDay3;
  /***
   *  使用时间
   */
  private String bomUseDay4;
  /***
   *  使用时间
   */
  private String bomUseDay5;
  /***
   *  使用时间
   */
  private String bomUseDay6;
  /***
   *  使用时间
   */
  private String bomUseDay7;
  /***
   *  使用时间
   */
  private String bomUseDay8;
  /***
   *  使用时间
   */
  private String bomUseDay9;
  /***
   *  使用时间
   */
  private String bomUseDay10;
  /***
   *  使用时间
   */
  private String bomUseDay11;
  /***
   *  使用时间
   */
  private String bomUseDay12;
  /***
   *  使用时间
   */
  private String bomUseDay13;
  /***
   *  使用时间
   */
  private String bomUseDay14;
  /***
   *  使用时间
   */
  private String bomUseDay15;
  /***
   *  使用时间
   */
  private String bomUseDay16;
  /***
   *  使用时间
   */
  private String bomUseDay17;
  /***
   *  使用时间
   */
  private String bomUseDay18;
  /***
   *  使用时间
   */
  private String bomUseDay19;
  /***
   *  使用时间
   */
  private String bomUseDay20;
  /***
   *  使用时间
   */
  private String bomUseDay21;
  /***
   *  使用时间
   */
  private String bomUseDay22;
  /***
   *  使用时间
   */
  private String bomUseDay23;
  /***
   *  使用时间
   */
  private String bomUseDay24;
  /***
   *  使用时间
   */
  private String bomUseDay25;
  /***
   *  使用时间
   */
  private String bomUseDay26;
  /***
   *  使用时间
   */
  private String bomUseDay27;
  /***
   *  使用时间
   */
  private String bomUseDay28;
  /***
   *  使用时间
   */
  private String bomUseDay29;
  /***
   *  使用时间
   */
  private String bomUseDay30;
  /***
   *  使用时间
   */
  private String bomUseDay31;
  /***
   *  使用时间
   */
  private String bomUseDay32;
  /***
   *  使用时间
   */
  private String bomUseDay33;
  /***
   *  使用时间
   */
  private String bomUseDay34;
  /***
   *  使用时间
   */
  private String bomUseDay35;
  /***
   *  使用时间
   */
  private String bomUseDay36;
  /***
   *  使用时间
   */
  private String bomUseDay37;
  /***
   *  使用时间
   */
  private String bomUseDay38;
  /***
   *  使用时间
   */
  private String bomUseDay39;
  /***
   *  使用时间
   */
  private String bomUseDay40;
  /***
   *  使用时间
   */
  private String bomUseDay41;
  /***
   *  使用时间
   */
  private String bomUseDay42;
  /***
   *  使用时间
   */
  private String bomUseDay43;
  /***
   *  使用时间
   */
  private String bomUseDay44;
  /***
   *  使用时间
   */
  private String bomUseDay45;
  /***
   *  使用时间
   */
  private String bomUseDay46;
  /***
   *  使用时间
   */
  private String bomUseDay47;
  /***
   *  使用时间
   */
  private String bomUseDay48;
  /***
   *  使用时间
   */
  private String bomUseDay49;
  /***
   *  使用时间
   */
  private String bomUseDay50;
  /***
   *  使用时间
   */
  private String bomUseDay51;
  /***
   *  使用时间
   */
  private String bomUseDay52;
  /***
   *  使用时间
   */
  private String bomUseDay53;
  /***
   *  使用时间
   */
  private String bomUseDay54;
  /***
   *  使用时间
   */
  private String bomUseDay55;
  /***
   *  使用时间
   */
  private String bomUseDay56;
  /***
   *  使用时间
   */
  private String bomUseDay57;
  /***
   *  使用时间
   */
  private String bomUseDay58;
  /***
   *  使用时间
   */
  private String bomUseDay59;
  /***
   *  使用时间
   */
  private String bomUseDay60;
  /***
   *  使用时间
   */
  private String bomUseDay61;
  /***
   *  使用时间
   */
  private String bomUseDay62;
  /***
   *  使用时间
   */
  private String bomUseDay63;
  /***
   *  使用时间
   */
  private String bomUseDay64;
  /***
   *  使用时间
   */
  private String bomUseDay65;
  /***
   *  使用时间
   */
  private String bomUseDay66;
  /***
   *  使用时间
   */
  private String bomUseDay67;
  /***
   *  使用时间
   */
  private String bomUseDay68;
  /***
   *  使用时间
   */
  private String bomUseDay69;
  /***
   *  使用时间
   */
  private String bomUseDay70;
  /***
   *  使用时间
   */
  private String bomUseDay71;
  /***
   *  使用时间
   */
  private String bomUseDay72;
  /***
   *  使用时间
   */
  private String bomUseDay73;
  /***
   *  使用时间
   */
  private String bomUseDay74;
  /***
   *  使用时间
   */
  private String bomUseDay75;
  /***
   *  使用时间
   */
  private String bomUseDay76;
  /***
   *  使用时间
   */
  private String bomUseDay77;
  /***
   *  使用时间
   */
  private String bomUseDay78;
  /***
   *  使用时间
   */
  private String bomUseDay79;
  /***
   *  使用时间
   */
  private String bomUseDay80;
  /***
   *  使用时间
   */
  private String bomUseDay81;
  /***
   *  使用时间
   */
  private String bomUseDay82;
  /***
   *  使用时间
   */
  private String bomUseDay83;
  /***
   *  使用时间
   */
  private String bomUseDay84;
  /***
   *  使用时间
   */
  private String bomUseDay85;
  /***
   *  使用时间
   */
  private String bomUseDay86;
  /***
   *  使用时间
   */
  private String bomUseDay87;
  /***
   *  使用时间
   */
  private String bomUseDay88;
  /***
   *  使用时间
   */
  private String bomUseDay89;
  /***
   *  使用时间
   */
  private String bomUseDay90;
  /***
   *  使用时间
   */
  private String bomUseDay91;
  /***
   *  使用时间
   */
  private String bomUseDay92;
  /***
   *  使用时间
   */
  private String bomUseDay93;
  /***
   *  使用时间
   */
  private String bomUseDay94;
  /***
   *  使用时间
   */
  private String bomUseDay95;
  /***
   *  使用时间
   */
  private String bomUseDay96;
  /***
   *  使用时间
   */
  private String bomUseDay97;
  /***
   *  使用时间
   */
  private String bomUseDay98;
  /***
   *  使用时间
   */
  private String bomUseDay99;
  /***
   *  使用时间
   */
  private String bomUseDay100;
  /***
   *  使用时间
   */
  private String bomUseDay101;
  /***
   *  使用时间
   */
  private String bomUseDay102;
  /***
   *  使用时间
   */
  private String bomUseDay103;
  /***
   *  使用时间
   */
  private String bomUseDay104;
  /***
   *  使用时间
   */
  private String bomUseDay105;
  /***
   *  使用时间
   */
  private String bomUseDay106;
  /***
   *  使用时间
   */
  private String bomUseDay107;
  /***
   *  使用时间
   */
  private String bomUseDay108;
  /***
   *  使用时间
   */
  private String bomUseDay109;
  /***
   *  使用时间
   */
  private String bomUseDay110;
  /***
   *  使用时间
   */
  private String bomUseDay111;
  /***
   *  使用时间
   */
  private String bomUseDay112;
  /***
   *  使用时间
   */
  private String bomUseDay113;
  /***
   *  使用时间
   */
  private String bomUseDay114;
  /***
   *  使用时间
   */
  private String bomUseDay115;
  /***
   *  使用时间
   */
  private String bomUseDay116;
  /***
   *  使用时间
   */
  private String bomUseDay117;
  /***
   *  使用时间
   */
  private String bomUseDay118;
  /***
   *  使用时间
   */
  private String bomUseDay119;
  /***
   *  使用时间
   */
  private String bomUseDay120;
  /***
   *  使用时间
   */
  private String bomUseDay121;
  /***
   *  使用时间
   */
  private String bomUseDay122;
  /***
   *  使用时间
   */
  private String bomUseDay123;
  /***
   *  使用时间
   */
  private String bomUseDay124;
  /***
   *  使用时间
   */
  private String bomUseDay125;
  /***
   *  使用时间
   */
  private String bomUseDay126;
  /***
   *  使用时间
   */
  private String bomUseDay127;
  /***
   *  使用时间
   */
  private String bomUseDay128;
  /***
   *  使用时间
   */
  private String bomUseDay129;
  /***
   *  使用时间
   */
  private String bomUseDay130;
  /***
   *  使用时间
   */
  private String bomUseDay131;
  /***
   *  使用时间
   */
  private String bomUseDay132;
  /***
   *  使用时间
   */
  private String bomUseDay133;
  /***
   *  使用时间
   */
  private String bomUseDay134;
  /***
   *  使用时间
   */
  private String bomUseDay135;
  /***
   *  使用时间
   */
  private String bomUseDay136;
  /***
   *  使用时间
   */
  private String bomUseDay137;
  /***
   *  使用时间
   */
  private String bomUseDay138;
  /***
   *  使用时间
   */
  private String bomUseDay139;
  /***
   *  使用时间
   */
  private String bomUseDay140;
  /***
   *  使用时间
   */
  private String bomUseDay141;
  /***
   *  使用时间
   */
  private String bomUseDay142;
  /***
   *  使用时间
   */
  private String bomUseDay143;
  /***
   *  使用时间
   */
  private String bomUseDay144;
  /***
   *  使用时间
   */
  private String bomUseDay145;
  /***
   *  使用时间
   */
  private String bomUseDay146;
  /***
   *  使用时间
   */
  private String bomUseDay147;
  /***
   *  使用时间
   */
  private String bomUseDay148;
  /***
   *  使用时间
   */
  private String bomUseDay149;
  /***
   *  使用时间
   */
  private String bomUseDay150;
  /***
   *  使用时间
   */
  private String bomUseDay151;
  /***
   *  使用时间
   */
  private String bomUseDay152;
  /***
   *  使用时间
   */
  private String bomUseDay153;
  /***
   *  使用时间
   */
  private String bomUseDay154;
  /***
   *  使用时间
   */
  private String bomUseDay155;
  /***
   *  使用时间
   */
  private String bomUseDay156;
  /***
   *  使用时间
   */
  private String bomUseDay157;
  /***
   *  使用时间
   */
  private String bomUseDay158;
  /***
   *  使用时间
   */
  private String bomUseDay159;
  /***
   *  使用时间
   */
  private String bomUseDay160;
  /***
   *  使用时间
   */
  private String bomUseDay161;
  /***
   *  使用时间
   */
  private String bomUseDay162;
  /***
   *  使用时间
   */
  private String bomUseDay163;
  /***
   *  使用时间
   */
  private String bomUseDay164;
  /***
   *  使用时间
   */
  private String bomUseDay165;
  /***
   *  使用时间
   */
  private String bomUseDay166;
  /***
   *  使用时间
   */
  private String bomUseDay167;
  /***
   *  使用时间
   */
  private String bomUseDay168;
  /***
   *  使用时间
   */
  private String bomUseDay169;
  /***
   *  使用时间
   */
  private String bomUseDay170;
  /***
   *  使用时间
   */
  private String bomUseDay171;
  /***
   *  使用时间
   */
  private String bomUseDay172;
  /***
   *  使用时间
   */
  private String bomUseDay173;
  /***
   *  使用时间
   */
  private String bomUseDay174;
  /***
   *  使用时间
   */
  private String bomUseDay175;
  /***
   *  使用时间
   */
  private String bomUseDay176;
  /***
   *  使用时间
   */
  private String bomUseDay177;
  /***
   *  使用时间
   */
  private String bomUseDay178;
  /***
   *  使用时间
   */
  private String bomUseDay179;
  /***
   *  使用时间
   */
  private String bomUseDay180;
  /***
   *  使用时间
   */
  private String bomUseDay181;
  /***
   *  使用时间
   */
  private String bomUseDay182;
  /***
   *  使用时间
   */
  private String bomUseDay183;
  /***
   *  使用时间
   */
  private String bomUseDay184;
  /***
   *  使用时间
   */
  private String bomUseDay185;
  /***
   *  使用时间
   */
  private String bomUseDay186;
  /***
   *  使用时间
   */
  private String bomUseDay187;
  /***
   *  使用时间
   */
  private String bomUseDay188;
  /***
   *  使用时间
   */
  private String bomUseDay189;
  /***
   *  使用时间
   */
  private String bomUseDay190;
  /***
   *  使用时间
   */
  private String bomUseDay191;
  /***
   *  使用时间
   */
  private String bomUseDay192;
  /***
   *  使用时间
   */
  private String bomUseDay193;
  /***
   *  使用时间
   */
  private String bomUseDay194;
  /***
   *  使用时间
   */
  private String bomUseDay195;
  /***
   *  使用时间
   */
  private String bomUseDay196;
  /***
   *  使用时间
   */
  private String bomUseDay197;
  /***
   *  使用时间
   */
  private String bomUseDay198;
  /***
   *  使用时间
   */
  private String bomUseDay199;
  /***
   *  使用时间
   */
  private String bomUseDay200;
  /***
   *  使用时间
   */
  private String bomUseDay201;
  /***
   *  使用时间
   */
  private String bomUseDay202;
  /***
   *  使用时间
   */
  private String bomUseDay203;
  /***
   *  使用时间
   */
  private String bomUseDay204;
  /***
   *  使用时间
   */
  private String bomUseDay205;
  /***
   *  使用时间
   */
  private String bomUseDay206;
  /***
   *  使用时间
   */
  private String bomUseDay207;
  /***
   *  使用时间
   */
  private String bomUseDay208;
  /***
   *  使用时间
   */
  private String bomUseDay209;
  /***
   *  使用时间
   */
  private String bomUseDay210;
  /***
   *  使用时间
   */
  private String bomUseDay211;
  /***
   *  使用时间
   */
  private String bomUseDay212;
  /***
   *  使用时间
   */
  private String bomUseDay213;
  /***
   *  使用时间
   */
  private String bomUseDay214;
  /***
   *  使用时间
   */
  private String bomUseDay215;
  /***
   *  使用时间
   */
  private String bomUseDay216;
  /***
   *  使用时间
   */
  private String bomUseDay217;
  /***
   *  使用时间
   */
  private String bomUseDay218;
  /***
   *  使用时间
   */
  private String bomUseDay219;
  /***
   *  使用时间
   */
  private String bomUseDay220;
  /***
   *  使用时间
   */
  private String bomUseDay221;
  /***
   *  使用时间
   */
  private String bomUseDay222;
  /***
   *  使用时间
   */
  private String bomUseDay223;
  /***
   *  使用时间
   */
  private String bomUseDay224;
  /***
   *  使用时间
   */
  private String bomUseDay225;
  /***
   *  使用时间
   */
  private String bomUseDay226;
  /***
   *  使用时间
   */
  private String bomUseDay227;
  /***
   *  使用时间
   */
  private String bomUseDay228;
  /***
   *  使用时间
   */
  private String bomUseDay229;
  /***
   *  使用时间
   */
  private String bomUseDay230;
  /***
   *  使用时间
   */
  private String bomUseDay231;
  /***
   *  使用时间
   */
  private String bomUseDay232;
  /***
   *  使用时间
   */
  private String bomUseDay233;
  /***
   *  使用时间
   */
  private String bomUseDay234;
  /***
   *  使用时间
   */
  private String bomUseDay235;
  /***
   *  使用时间
   */
  private String bomUseDay236;
  /***
   *  使用时间
   */
  private String bomUseDay237;
  /***
   *  使用时间
   */
  private String bomUseDay238;
  /***
   *  使用时间
   */
  private String bomUseDay239;
  /***
   *  使用时间
   */
  private String bomUseDay240;
  /***
   *  使用时间
   */
  private String bomUseDay241;
  /***
   *  使用时间
   */
  private String bomUseDay242;
  /***
   *  使用时间
   */
  private String bomUseDay243;
  /***
   *  使用时间
   */
  private String bomUseDay244;
  /***
   *  使用时间
   */
  private String bomUseDay245;
  /***
   *  使用时间
   */
  private String bomUseDay246;
  /***
   *  使用时间
   */
  private String bomUseDay247;
  /***
   *  使用时间
   */
  private String bomUseDay248;
  /***
   *  使用时间
   */
  private String bomUseDay249;
  /***
   *  使用时间
   */
  private String bomUseDay250;
  /***
   *  使用时间
   */
  private String bomUseDay251;
  /***
   *  使用时间
   */
  private String bomUseDay252;
  /***
   *  使用时间
   */
  private String bomUseDay253;
  /***
   *  使用时间
   */
  private String bomUseDay254;
  /***
   *  使用时间
   */
  private String bomUseDay255;
  /***
   *  使用时间
   */
  private String bomUseDay256;
  /***
   *  使用时间
   */
  private String bomUseDay257;
  /***
   *  使用时间
   */
  private String bomUseDay258;
  /***
   *  使用时间
   */
  private String bomUseDay259;
  /***
   *  使用时间
   */
  private String bomUseDay260;
  /***
   *  使用时间
   */
  private String bomUseDay261;
  /***
   *  使用时间
   */
  private String bomUseDay262;
  /***
   *  使用时间
   */
  private String bomUseDay263;
  /***
   *  使用时间
   */
  private String bomUseDay264;
  /***
   *  使用时间
   */
  private String bomUseDay265;
  /***
   *  使用时间
   */
  private String bomUseDay266;
  /***
   *  使用时间
   */
  private String bomUseDay267;
  /***
   *  使用时间
   */
  private String bomUseDay268;
  /***
   *  使用时间
   */
  private String bomUseDay269;
  /***
   *  使用时间
   */
  private String bomUseDay270;
  /***
   *  使用时间
   */
  private String bomUseDay271;
  /***
   *  使用时间
   */
  private String bomUseDay272;
  /***
   *  使用时间
   */
  private String bomUseDay273;
  /***
   *  使用时间
   */
  private String bomUseDay274;
  /***
   *  使用时间
   */
  private String bomUseDay275;
  /***
   *  使用时间
   */
  private String bomUseDay276;
  /***
   *  使用时间
   */
  private String bomUseDay277;
  /***
   *  使用时间
   */
  private String bomUseDay278;
  /***
   *  使用时间
   */
  private String bomUseDay279;
  /***
   *  使用时间
   */
  private String bomUseDay280;
  /***
   *  使用时间
   */
  private String bomUseDay281;
  /***
   *  使用时间
   */
  private String bomUseDay282;
  /***
   *  使用时间
   */
  private String bomUseDay283;
  /***
   *  使用时间
   */
  private String bomUseDay284;
  /***
   *  使用时间
   */
  private String bomUseDay285;
  /***
   *  使用时间
   */
  private String bomUseDay286;
  /***
   *  使用时间
   */
  private String bomUseDay287;
  /***
   *  使用时间
   */
  private String bomUseDay288;
  /***
   *  使用时间
   */
  private String bomUseDay289;
  /***
   *  使用时间
   */
  private String bomUseDay290;
  /***
   *  使用时间
   */
  private String bomUseDay291;
  /***
   *  使用时间
   */
  private String bomUseDay292;
  /***
   *  使用时间
   */
  private String bomUseDay293;
  /***
   *  使用时间
   */
  private String bomUseDay294;
  /***
   *  使用时间
   */
  private String bomUseDay295;
  /***
   *  使用时间
   */
  private String bomUseDay296;
  /***
   *  使用时间
   */
  private String bomUseDay297;
  /***
   *  使用时间
   */
  private String bomUseDay298;
  /***
   *  使用时间
   */
  private String bomUseDay299;
  /***
   *  使用时间
   */
  private String bomUseDay300;
  /***
   *  使用时间
   */
  private String bomUseDay301;
  /***
   *  使用时间
   */
  private String bomUseDay302;
  /***
   *  使用时间
   */
  private String bomUseDay303;
  /***
   *  使用时间
   */
  private String bomUseDay304;
  /***
   *  使用时间
   */
  private String bomUseDay305;
  /***
   *  使用时间
   */
  private String bomUseDay306;
  /***
   *  使用时间
   */
  private String bomUseDay307;
  /***
   *  使用时间
   */
  private String bomUseDay308;
  /***
   *  使用时间
   */
  private String bomUseDay309;
  /***
   *  使用时间
   */
  private String bomUseDay310;
  /***
   *  使用时间
   */
  private String bomUseDay311;
  /***
   *  使用时间
   */
  private String bomUseDay312;
  /***
   *  使用时间
   */
  private String bomUseDay313;
  /***
   *  使用时间
   */
  private String bomUseDay314;
  /***
   *  使用时间
   */
  private String bomUseDay315;
  /***
   *  使用时间
   */
  private String bomUseDay316;
  /***
   *  使用时间
   */
  private String bomUseDay317;
  /***
   *  使用时间
   */
  private String bomUseDay318;
  /***
   *  使用时间
   */
  private String bomUseDay319;
  /***
   *  使用时间
   */
  private String bomUseDay320;
  /***
   *  使用时间
   */
  private String bomUseDay321;
  /***
   *  使用时间
   */
  private String bomUseDay322;
  /***
   *  使用时间
   */
  private String bomUseDay323;
  /***
   *  使用时间
   */
  private String bomUseDay324;
  /***
   *  使用时间
   */
  private String bomUseDay325;
  /***
   *  使用时间
   */
  private String bomUseDay326;
  /***
   *  使用时间
   */
  private String bomUseDay327;
  /***
   *  使用时间
   */
  private String bomUseDay328;
  /***
   *  使用时间
   */
  private String bomUseDay329;
  /***
   *  使用时间
   */
  private String bomUseDay330;
  /***
   *  使用时间
   */
  private String bomUseDay331;
  /***
   *  使用时间
   */
  private String bomUseDay332;
  /***
   *  使用时间
   */
  private String bomUseDay333;
  /***
   *  使用时间
   */
  private String bomUseDay334;
  /***
   *  使用时间
   */
  private String bomUseDay335;
  /***
   *  使用时间
   */
  private String bomUseDay336;
  /***
   *  使用时间
   */
  private String bomUseDay337;
  /***
   *  使用时间
   */
  private String bomUseDay338;
  /***
   *  使用时间
   */
  private String bomUseDay339;
  /***
   *  使用时间
   */
  private String bomUseDay340;
  /***
   *  使用时间
   */
  private String bomUseDay341;
  /***
   *  使用时间
   */
  private String bomUseDay342;
  /***
   *  使用时间
   */
  private String bomUseDay343;
  /***
   *  使用时间
   */
  private String bomUseDay344;
  /***
   *  使用时间
   */
  private String bomUseDay345;
  /***
   *  使用时间
   */
  private String bomUseDay346;
  /***
   *  使用时间
   */
  private String bomUseDay347;
  /***
   *  使用时间
   */
  private String bomUseDay348;
  /***
   *  使用时间
   */
  private String bomUseDay349;
  /***
   *  使用时间
   */
  private String bomUseDay350;
  /***
   *  使用时间
   */
  private String bomUseDay351;
  /***
   *  使用时间
   */
  private String bomUseDay352;
  /***
   *  使用时间
   */
  private String bomUseDay353;
  /***
   *  使用时间
   */
  private String bomUseDay354;
  /***
   *  使用时间
   */
  private String bomUseDay355;
  /***
   *  使用时间
   */
  private String bomUseDay356;
  /***
   *  使用时间
   */
  private String bomUseDay357;
  /***
   *  使用时间
   */
  private String bomUseDay358;
  /***
   *  使用时间
   */
  private String bomUseDay359;
  /***
   *  使用时间
   */
  private String bomUseDay360;
  /***
   *  使用时间
   */
  private String bomUseDay361;
  /***
   *  使用时间
   */
  private String bomUseDay362;
  /***
   *  使用时间
   */
  private String bomUseDay363;
  /***
   *  使用时间
   */
  private String bomUseDay364;
  /***
   *  使用时间
   */
  private String bomUseDay365;
  /***
   *  使用时间
   */
  private String bomUseDay366;
  /***
   *  工厂ID
   */
  private Long factoryId;
  /***
   *  是否关注
   */
  private Boolean isFollow;

}

