package com.olivia.peanut.aps.api.entity.apsBomGroup;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.ann.CheckObjectFieldValueAnn;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 零件组配置(ApsBomGroup)查询对象返回
 *
 * @author peanut
 * @since 2024-06-19 17:41:24
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsBomGroupDto extends BaseEntityDto {

  /***
   *  组编码
   */
  @ExcelProperty("编码")
  @CheckObjectFieldValueAnn(useValid = true)
  @NotBlank(message = "组编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String groupCode;
  /***
   *  组名称
   */
  @ExcelProperty("组名称")
  @CheckObjectFieldValueAnn(useValid = true)
  @NotBlank(message = "组名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String groupName;
  /***
   *  父级ID
   */

  @ExcelIgnore
  @NotNull(message = "父级ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long parentId;
  @ExcelProperty(value = "上级")
  @CheckObjectFieldValueAnn(errorMessage = "上级不能为空", fieldShowName = "上级")
  private String parentName;
  /***
   *  路径配置
   */
  @ExcelIgnore
//  @NotBlank(message = "路径配置不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String pathId;

}


