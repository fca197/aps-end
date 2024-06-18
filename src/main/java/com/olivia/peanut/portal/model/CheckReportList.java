package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 报表检查记录信息(CheckReportList)表实体类
 *
 * @author makejava
 * @since 2024-03-14 13:31:37
 */
@Accessors(chain = true)
@Getter
@Setter
//
@TableName("t_check_report_list")
public class CheckReportList extends BaseEntity {

  /***
   *  id
   */
  private Long id;
  /***
   *  工厂ID
   */
  private Long factoryId;
  /***
   *  报表编码
   */
  private Long reportId;

  private Long storeyId;
  private Long roomId;
  /***
   *  资产ID
   */
  private Long propertyId;
  /***
   *  备注
   */
  private String remark;

}

