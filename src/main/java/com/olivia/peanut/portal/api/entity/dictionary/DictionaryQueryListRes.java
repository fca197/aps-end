package com.olivia.peanut.portal.api.entity.dictionary;

import java.time.LocalTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 字典值(Dictionary)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:04
 */
@Accessors(chain = true)
@Getter
@Setter

public class DictionaryQueryListRes {

  /***
   * 返回对象列表
   */
  private List<Info> dataList;


  @Getter
  @Setter
  public static class Info {

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
    /***
     *  所属租户id
     */
    private Long tenantId;
    /***
     *
     */

    /***
     *  创建时间
     */
    private LocalTime createTime;
    /***
     *  创建人id
     */
    private Long createBy;
    /***
     *  更新时间
     */
    private LocalTime updateTime;
    /***
     *  更新人id
     */
    private Long updateBy;
    /***
     *  链路追踪ID
     */
    private String traceId;
    /***
     *  版本号
     */
    private Integer versionNum;

  }
}

