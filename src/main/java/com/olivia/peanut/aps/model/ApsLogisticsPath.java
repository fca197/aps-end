package com.olivia.peanut.aps.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 物流路径表(ApsLogisticsPath)表实体类
 *
 * @author peanut
 * @since 2024-07-18 13:27:36
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_logistics_path")
public class ApsLogisticsPath extends BaseEntity {

  /***
   *  物流路径编码
   */
  private String logisticsPathCode;
  /***
   *  物流路径名称
   */
  private String logisticsPathName;
  /***
   *  备注
   */
  private String logisticsPathRemark;
  /***
   *  是否默认
   */
  private Integer isDefault;
  /***
   *  工厂ID
   */
  private Long factoryId;

}

