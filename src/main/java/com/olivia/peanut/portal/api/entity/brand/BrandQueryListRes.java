package com.olivia.peanut.portal.api.entity.brand;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 品牌信息(Brand)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:02
 */
@Accessors(chain = true)
@Getter
@Setter

public class BrandQueryListRes {

  /***
   * 返回对象列表
   */
  private List<Info> dataList;


  @Getter
  @Setter
  public static class Info extends BaseEntityDto {

    /***
     *  id
     */
    private Long id;
    /***
     *  所属租户id
     */
    private Long tenantId;
    /***
     *  所属工厂id
     */
    private Long factoryId;
    /***
     *  品牌编码
     */
    private String brandCode;
    /***
     *  品牌名称
     */
    private String brandName;
    /***
     *  品牌状态
     */
    private String brandStatus;


  }
}

