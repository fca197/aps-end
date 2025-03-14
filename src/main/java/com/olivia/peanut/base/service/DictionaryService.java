package com.olivia.peanut.base.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.base.api.entity.dictionary.DictionaryExportQueryPageListInfoRes;
import com.olivia.peanut.base.api.entity.dictionary.DictionaryExportQueryPageListReq;
import com.olivia.peanut.base.api.entity.dictionary.DictionaryQueryListReq;
import com.olivia.peanut.base.api.entity.dictionary.DictionaryQueryListRes;
import com.olivia.peanut.base.model.Dictionary;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * 字典值(Dictionary)表服务接口
 *
 * @author makejava
 * @since 2024-03-11 10:44:04
 */
public interface DictionaryService extends MPJBaseService<Dictionary> {

  DictionaryQueryListRes queryList(DictionaryQueryListReq req);

  DynamicsPage<DictionaryExportQueryPageListInfoRes> queryPageList(DictionaryExportQueryPageListReq req);

}

