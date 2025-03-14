package com.olivia.peanut.base.api.entity.dictionary;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 字典值(Dictionary)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:04
 */
@Accessors(chain = true)
@Getter
@Setter

public class DictionaryQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<Info> dataList;


  @Getter
  @Setter
  public static class Info extends BaseEntityDto {

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
}

