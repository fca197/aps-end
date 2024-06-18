package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 报表信息(CheckReport)表实体类
 *
 * @author makejava
 * @since 2024-03-14 13:31:35
 */
@Accessors(chain = true)
@Getter
@Setter
//
@TableName("t_check_report")
public class CheckReport extends BaseEntity {

  /***
   *  id
   */
  private Long id;
  /***
   *  所属租户id
   */
  private Long tenantId;
  /***
   *  工厂ID
   */
  private Long factoryId;
  /***
   *  报表编码
   */
  private String reportCode;
  /***
   *  报表名称
   */
  private String reportName;

  private Boolean isOver;
}

