package com.olivia.peanut.aps.api.entity.apsSellerStore;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * aps销售门店(ApsSellerStore)查询对象返回
 *
 * @author makejava
 * @since 2024-11-15 14:58:59
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSellerStoreDto extends BaseEntityDto {

  /***
   *  销售门店编码
   */
  @NotBlank(message = "销售门店编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String sellerStoreCode;
  /***
   *  销售门店名称
   */
  @NotBlank(message = "销售门店名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String sellerStoreName;
  /***
   *  销售门店手机号
   */
  @NotBlank(message = "销售门店手机号不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String sellerStorePhone;
  /***
   *  销售门店省编码
   */
  @NotBlank(message = "销售门店省编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String sellerStoreProvinceCode;
  /***
   *  销售门店市编码
   */
  @NotBlank(message = "销售门店市编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String sellerStoreCityCode;
  /***
   *  销售门店区编码
   */
  @NotBlank(message = "销售门店区编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String sellerStoreAreaCode;
  /***
   *  销售门店地址
   */
  @NotBlank(message = "销售门店地址不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String sellerStoreAddr;
  /***
   *  销售门店高的经纬度 如117.500244
   */
  @NotNull(message = "销售门店高的经纬度不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal sellerStoreGdLon;
  /***
   *  销售门店高的经纬度 如 40.417801
   */
  @NotNull(message = "销售门店高的经纬度不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal sellerStoreGdLat;
  private String sellerStoreAreaName;

}


