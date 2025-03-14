package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 字典值(Dictionary)表实体类
 *
 * @author makejava
 * @since 2024-03-11 10:44:04
 */
@Accessors(chain = true)
@Getter
@Setter
//
@TableName("t_dictionary")
public class Dictionary extends BaseEntity {

  /***
   *  id
   */
  private Long id;
  /***
   *  字典组
   */
  private String dictionaryGroup;
  /***
   *  字典值
   */
  private String dictionaryValue;
  /***
   *  排序
   */
  private Integer dictionarySort;
  /***
   *  而外信息
   */
  private String dictionaryExt;

}

