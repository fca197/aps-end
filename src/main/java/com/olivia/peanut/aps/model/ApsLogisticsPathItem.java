package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 物流路详情径表(ApsLogisticsPathItem)表实体类
 *
 * @author peanut
 * @since 2024-07-18 13:27:40
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_logistics_path_item")
public class ApsLogisticsPathItem extends BaseEntity {

  /***
   *  物流路径id
   */
  private Long logisticsPathId;
  /***
   *  省编码
   */
  private String provinceCode;
  /***
   *  省名称
   */
  private String provinceName;
  /***
   *  市编码
   */
  private String cityCode;
  /***
   *  市名称
   */
  private String cityName;
  /***
   *  运输天数
   */
  private Integer transportDay;
  /***
   *  是否默认
   */
  private Integer isDefault;
  /***
   *  工厂ID
   */
  private Long factoryId;

}

