package com.olivia.peanut.base.api.entity.baseAppResource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 资源(BaseAppResource)查询对象返回
 *
 * @author peanut
 * @since 2024-08-06 17:30:28
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseAppResourceExportQueryPageListInfoRes extends BaseAppResourceDto {


  private String resourceName;
  private String resourceUrl;
  private Long parentId;
  private Integer sortIndex;
}


