package com.olivia.peanut.portal.api.entity.jcxGoodsWarning;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxGoodsWarning)查询对象入参
 *
 * @author peanut
 * @since 2024-03-24 14:10:55
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxGoodsWarningExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private JcxGoodsWarningDto data;


  public void checkParam() {
  }

}

