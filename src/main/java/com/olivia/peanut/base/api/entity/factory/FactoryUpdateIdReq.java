package com.olivia.peanut.base.api.entity.factory;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 工段信息(Factory)表实体类
 *
 * @author peanut
 * @since 2023-12-28 17:29:29
 */
@Data
@Accessors(chain = true)
public class FactoryUpdateIdReq {

  @NotNull(message = "修改信息不能为空")
  private Long id;
  /**
   * 工厂名称
   **/
  private String factoryName;
  /**
   * 工厂状态
   **/
  private String factoryStatus;
}


