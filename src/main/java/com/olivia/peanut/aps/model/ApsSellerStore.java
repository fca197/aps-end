package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * aps销售门店(ApsSellerStore)表实体类
 *
 * @author makejava
 * @since 2024-11-15 14:58:59
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_seller_store")
public class ApsSellerStore extends BaseEntity {
  /***
   *  销售门店编码
   */
  private String sellerStoreCode;
  /***
   *  销售门店名称
   */
  private String sellerStoreName;
  /***
   *  销售门店手机号
   */
  private String sellerStorePhone;
  /***
   *  销售门店省编码
   */
  private String sellerStoreProvinceCode;
  /***
   *  销售门店市编码
   */
  private String sellerStoreCityCode;
  /***
   *  销售门店区编码
   */
  private String sellerStoreAreaCode;
  /***
   *  销售门店地址
   */
  private String sellerStoreAddr;
  /***
   *  销售门店高的经纬度 如117.500244
   */
  private BigDecimal sellerStoreGdLon;
  /***
   *  销售门店高的经纬度 如 40.417801
   */
  private BigDecimal sellerStoreGdLat;

}

