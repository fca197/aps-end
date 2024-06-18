package com.olivia.peanut.portal.api;

import com.olivia.peanut.portal.api.entity.jcxInventory.GoodsInventoryRes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/***
 *
 */
public interface JcxInventoryApi {

  @GetMapping("/jcx/goods/inventory/{type}/{tenantId}")
  GoodsInventoryRes goodsInventory(@PathVariable Long type, @PathVariable("tenantId") Long tenantId);
}
