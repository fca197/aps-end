package com.olivia.peanut.aps.api.entity.apsSellerStore;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * aps销售门店(ApsSellerStore)根据ID删除多个入参
 *
 * @author makejava
 * @since 2024-11-15 14:58:58
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSellerStoreDeleteByIdListReq {
  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


}

