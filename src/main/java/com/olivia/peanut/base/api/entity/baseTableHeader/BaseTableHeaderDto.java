package com.olivia.peanut.base.api.entity.baseTableHeader;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

/**
 * (BaseTableHeader)查询对象返回
 *
 * @author peanut
 * @since 2024-03-25 14:19:10
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseTableHeaderDto extends BaseEntityDto {


  private String bizKey;
  private String fieldName;
  private String showName;
  private Integer widthPx;
  private Boolean isPicture;
  private Integer sortIndex;
}


