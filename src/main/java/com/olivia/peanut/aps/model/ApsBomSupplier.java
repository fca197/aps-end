package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 供应商表(ApsBomSupplier)表实体类
 *
 * @author makejava
 * @since 2024-12-15 14:39:45
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_bom_supplier")
public class ApsBomSupplier extends BaseEntity {
  /***
   *  名称
   */
  private String bomSupplierName;
  /***
   *  编号
   */
  private String bomSupplierCode;
  /***
   *  手机
   */
  private String bomSupplierPhone;
  /***
   *  座机
   */
  private String bomSupplierTel;
  /***
   *  邮件
   */
  private String bomSupplierEmail;
  /***
   *  省编码
   */
  private String provinceCode;
  /***
   *  市编码
   */
  private String cityCode;
  /***
   *  县编码
   */
  private String areaCode;
  /***
   *  地址
   */
  private String bomSupplierAddr;
  /***
   *  备注
   */
  private String bomSupplierRemark;
  /***
   *  状态
   */

  private Boolean supplierStatus;


}

