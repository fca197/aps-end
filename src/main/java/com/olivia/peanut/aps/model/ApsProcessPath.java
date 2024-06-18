package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsProcessPath)表实体类
 *
 * @author peanut
 * @since 2024-04-01 17:49:18
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_process_path")
public class ApsProcessPath extends BaseEntity {

  private String processPathCode;
  private String processPathName;
  private String processPathRemark;
  private Boolean isDefault;
  private Long factoryId;

}

