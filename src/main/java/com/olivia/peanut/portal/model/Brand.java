package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.ann.BelongDb;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static com.olivia.sdk.utils.Str.SQLITE;

/**
 * 品牌信息(Brand)表实体类
 *
 * @author makejava
 * @since 2024-03-11 10:44:02
 */
@Accessors(chain = true)
@Getter
@Setter
//
@TableName("t_brand")
@BelongDb(dataSourceNames = {SQLITE})
public class Brand extends BaseEntity {

  /***
   *  所属工厂id
   */
  private Long factoryId = -1L;
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
  private Boolean isUsed;

}

