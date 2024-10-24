package com.olivia.peanut.portal.api.entity.factory;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工段信息(Factory)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:05
 */
@Accessors(chain = true)
@Getter
@Setter

public class FactoryQueryListRes {

  /***
   * 返回对象列表
   */
  private List<Info> dataList;


  @Getter
  @Setter
  public static class Info extends BaseEntityDto {

    /***
     *  所属租户id
     */
    private Long tenantId;
    /***
     *  工厂名称
     */
    private String factoryName;
    /***
     *  工厂编码
     */
    private String factoryCode;
    /***
     *  工厂状态
     */
    private String factoryStatus;


  }
}

