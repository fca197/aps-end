package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 楼层信息(Storey)表实体类
 *
 * @author makejava
 * @since 2024-03-11 17:20:55
 */
@Accessors(chain = true)
@Getter
@Setter
//
@TableName("t_storey")
public class Storey extends BaseEntity {

  /***
   *  id
   */
  private Long id;
  /***
   *  楼层
   */

  private String storeyCode;
  private String storeyName;
  /***
   *  排序
   */
  private Integer storeySort;
  private Long factoryId;
}

