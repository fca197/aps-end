package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 房间信息(Room)表实体类
 *
 * @author makejava
 * @since 2024-03-11 17:20:54
 */
@Accessors(chain = true)
@Getter
@Setter
//
@TableName("t_room")
public class Room extends BaseEntity {

  /***
   *  所属工厂id
   */
  private Long factoryId;
  /***
   *  楼层
   */
  private Long storeyId;
  /***
   *  编号
   */
  private String roomCode;
  /***
   *  楼层
   */
  private String roomName;
  /***
   *  排序
   */
  private Integer roomSort;
}

