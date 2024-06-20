package com.olivia.peanut.aps.api.entity.apsGoodsBomGroup;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 零件组配置(ApsGoodsBomGroup)查询对象返回
 *
 * @author peanut
 * @since 2024-06-19 16:49:04
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsBomGroupDto extends BaseEntityDto {

  /***
   *  组编码
   */
  @NotBlank(message = "组编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String groupCode;
  /***
   *  组名称
   */
  @NotBlank(message = "组名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String groupName;
  /***
   *  父级ID
   */
  @NotNull(message = "父级ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long parentId;
  /***
   *  路径配置
   */
  @NotBlank(message = "路径配置不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String pathId;

}


