package com.olivia.peanut.portal.api.entity.storey;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
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

public class StoreyDto  extends BaseEntityDto {


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



}


