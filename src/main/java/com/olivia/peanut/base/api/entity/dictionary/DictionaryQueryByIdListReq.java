package com.olivia.peanut.base.api.entity.dictionary;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 字典值(Dictionary)查询对象入参
 *
 * @author makejava
 * @since 2024-03-11 10:44:04
 */
@Accessors(chain = true)
@Getter
@Setter

public class DictionaryQueryByIdListReq {

  private List<Long> idList;


}

