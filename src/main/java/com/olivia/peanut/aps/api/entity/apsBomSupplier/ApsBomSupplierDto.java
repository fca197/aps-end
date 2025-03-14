package com.olivia.peanut.aps.api.entity.apsBomSupplier;

// import com.alibaba.fastjson2.annotation.JSONField;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import com.olivia.sdk.utils.Str;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 供应商表(ApsBomSupplier)查询对象返回
 *
 * @author makejava
 * @since 2024-12-15 14:39:46
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsBomSupplierDto extends BaseEntityDto {

  /***
   *  名称
   */
  @NotBlank(message = "名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "bomSupplierName")

  private String bomSupplierName;
  /***
   *  编号
   */
  @NotBlank(message = "编号不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "bomSupplierCode")

  private String bomSupplierCode;
  /***
   *  手机
   */
  @NotBlank(message = "手机不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "bomSupplierPhone")

  private String bomSupplierPhone;
  /***
   *  座机
   */
  @NotBlank(message = "座机不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "bomSupplierTel")

  private String bomSupplierTel;
  /***
   *  邮件
   */
  @NotBlank(message = "邮件不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "bomSupplierEmail")

  private String bomSupplierEmail;
  /***
   *  省编码
   */
  @NotBlank(message = "省编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "provinceCode")

  private String provinceCode;
  /***
   *  市编码
   */
  @NotBlank(message = "市编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "cityCode")

  private String cityCode;
  /***
   *  县编码
   */
  @NotBlank(message = "县编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "areaCode")

  private String areaCode;
  /***
   *  地址
   */
  @NotBlank(message = "地址不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "bomSupplierAddr")

  private String bomSupplierAddr;
  /***
   *  备注
   */
  @NotBlank(message = "备注不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "bomSupplierRemark")

  private String bomSupplierRemark;
  /***
   *  状态
   */
  @NotBlank(message = "状态不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "supplierStatus")

  private Boolean supplierStatus;
  private String provinceName, cityName, areaName;

  public String getSupplierStatusStr() {
    return Str.booleanToStr(supplierStatus);
  }
}


