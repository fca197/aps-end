package com.olivia.peanut.base.api.entity.factory;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class FactoryAddReq {

  /**
   * 工厂名称
   **/
  @NotBlank(message = "工厂名称不能为空")
  private String factoryName;
  /**
   * 工厂编码
   **/
  @NotBlank(message = "工厂编码不能为空")
  @Pattern(regexp = "^[a-zA-Z0-9]{2,10}$", message = "工厂编码2-10位")
  private String factoryCode;
  /**
   * 工厂状态  0  10 ,20 ,30
   **/
  private String factoryStatus;
}


