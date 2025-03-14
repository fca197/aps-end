package com.olivia.peanut.base.api.entity.msgMessage;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

/**
 * (MsgMessage)查询对象返回
 *
 * @author peanut
 * @since 2024-03-23 19:05:40
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class MsgMessageDto extends BaseEntityDto {


  @ExcelProperty("是否全部")
  private Boolean isAll;
  @ExcelProperty("标题")
  private String messageTitle;
  @ExcelProperty("内容")
  private String messageContext;
  private String messageJsonData;
}


