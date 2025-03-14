package com.olivia.peanut.base.api.entity.dictionary;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 字典值(Dictionary)查询对象入参
 *
 * @author makejava
 * @since 2024-03-11 10:44:04
 */
@Accessors(chain = true)
@Getter
@Setter

public class DictionaryExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private DictionaryDto data;


}

