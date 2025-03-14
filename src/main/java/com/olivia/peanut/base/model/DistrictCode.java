package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (DistrictCode)表实体类
 *
 * @author peanut
 * @since 2024-04-09 13:19:07
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("t_district_code")
public class DistrictCode extends BaseEntity {

  private String code;
  private String name;
  private String parentCode;
  // 0国,1省,2市,3区
  private Integer level;
  private String path;

  // 子节点
  @TableField(exist = false)
  private List<DistrictCode> children;
}

