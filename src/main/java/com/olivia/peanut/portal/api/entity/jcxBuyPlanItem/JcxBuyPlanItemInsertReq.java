package com.olivia.peanut.portal.api.entity.jcxBuyPlanItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxBuyPlanItem)保存入参
 *
 * @author peanut
 * @since 2024-03-24 20:27:11
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyPlanItemInsertReq extends JcxBuyPlanItemDto {

  public void checkParam() {

  }
}

