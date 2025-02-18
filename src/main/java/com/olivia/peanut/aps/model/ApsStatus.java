package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsStatus)表实体类
 *
 * @author peanut
 * @since 2024-04-01 10:50:12
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_status")
public class ApsStatus extends BaseEntity {

  private String statusCode;
  private String statusName;
  private Integer factoryId;
  //  private Boolean isOrderGoodsInit;
  private Long orderStatusId;

}

