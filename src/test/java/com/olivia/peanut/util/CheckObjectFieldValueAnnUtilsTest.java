package com.olivia.peanut.util;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.api.entity.apsBom.ApsBomInsertReq;
import com.olivia.sdk.dto.ExcelErrorMsg;
import com.olivia.sdk.exception.RunException;
import com.olivia.sdk.utils.$;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
class CheckObjectFieldValueAnnUtilsTest {
  @Test
  void isValid() {
    ApsBomInsertReq obj = new ApsBomInsertReq();
    obj.setGroupName("groupName");
    List<ExcelErrorMsg> errorMsgList = CheckObjectFieldValueUtils.check(obj);
    log.info("errorMsgList {}", JSON.toJSONString(errorMsgList));
    $.assertTrueException(CollUtil.isEmpty(errorMsgList), () -> new RunException(200, "文件上传失败", errorMsgList));

  }
}