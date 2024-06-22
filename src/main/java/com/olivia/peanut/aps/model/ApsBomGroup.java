package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 零件组配置(ApsBomGroup)表实体类
 *
 * @author peanut
 * @since 2024-06-19 17:41:23
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_bom_group")
public class ApsBomGroup extends BaseEntity {

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

