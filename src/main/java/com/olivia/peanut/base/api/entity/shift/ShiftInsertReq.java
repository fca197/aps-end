package com.olivia.peanut.base.api.entity.shift;

import cn.hutool.core.collection.CollUtil;
import com.olivia.sdk.utils.$;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (Shift)保存入参
 *
 * @author peanut
 * @since 2024-04-04 12:10:15
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ShiftInsertReq extends ShiftDto {

  public void checkParam() {
    $.assertTrueCanIgnoreException(CollUtil.isNotEmpty(this.getShiftItemDtoList()), "排班信息不能为空");
  }
}

