package com.olivia.peanut.portal.api.entity.storey;

import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 楼层信息(Storey)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 17:20:56
 */
//@Accessors(chain=true)
@Getter
@Setter

public class StoreyDto {


  /***
   *  id
   */
  @ExcelProperty("id")
  private Long id;
  /***
   *  所属租户id
   */
  @ExcelProperty("所属租户id")
  private Long tenantId;
  /***
   *  所属工厂id
   */
  @ExcelProperty("所属工厂id")
  private Long factoryId;

  @ExcelProperty("楼层编码")
  private String storeyCode;
  /***
   *  楼层
   */
  @ExcelProperty("楼层")
  private String storeyName;
  /***
   *  排序
   */
  @ExcelProperty("排序")
  private Integer storeySort;


  /***
   *  创建时间
   */
  @ExcelProperty("创建时间")
  private LocalDateTime createTime;
  /***
   *  创建人id
   */
  @ExcelProperty("创建人id")
  private Long createBy;
  /***
   *  更新时间
   */
  @ExcelProperty("更新时间")
  private LocalDateTime updateTime;
  /***
   *  更新人id
   */
  @ExcelProperty("更新人id")
  private Long updateBy;
  /***
   *  链路追踪ID
   */
  @ExcelProperty("链路追踪ID")
  private String traceId;
  /***
   *  版本号
   */
  @ExcelProperty("版本号")
  private Integer versionNum;

}


