package com.olivia.peanut.aps.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 零件组配置(ApsGoodsBomGroup)表实体类
 *
 * @author peanut
 * @since 2024-06-19 16:49:04
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_goods_bom_group")
public class ApsGoodsBomGroup extends BaseEntity {

  /***
   *  组编码
   */
  private String groupCode;
  /***
   *  组名称
   */
  private String groupName;
  /***
   *  父级ID
   */
  private Long parentId;
  /***
   *  路径配置
   */
  private String pathId;

}

