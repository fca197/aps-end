package com.olivia.peanut.portal.api.entity.room;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

/**
 * 房间信息(Room)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 17:20:55
 */
//@Accessors(chain=true)
@Getter
@Setter

public class RoomDto extends BaseEntityDto {


  /***
   *  所属工厂id
   */
  @ExcelProperty("所属工厂id")
  private Long factoryId;
  /***
   *  楼层
   */
  @ExcelProperty("楼层")
  private Long storeyId;
  /***
   *  编号
   */
  @ExcelProperty("编号")
  private String roomCode;
  /***
   *  楼层
   */
  @ExcelProperty("楼层")
  private String roomName;
  /***
   *  排序
   */
  @ExcelProperty("排序")
  private Integer roomSort;


}


