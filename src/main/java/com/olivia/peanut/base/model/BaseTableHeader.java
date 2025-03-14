package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (BaseTableHeader)表实体类
 *
 * @author peanut
 * @since 2024-03-25 14:19:10
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("base_table_header")
public class BaseTableHeader extends BaseEntity {

  private String bizKey;
  private String fieldName;
  private String showName;
  private Integer widthPx;
  private Boolean isPicture;
  private Integer sortIndex;

}

