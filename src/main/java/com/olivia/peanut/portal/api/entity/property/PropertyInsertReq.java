package com.olivia.peanut.portal.api.entity.property;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

/**
 * 资产信息(Property)保存入参
 *
 * @author makejava
 * @since 2024-03-11 17:20:52
 */
@Accessors(chain = true)
@Getter
@Setter

public class PropertyInsertReq extends PropertyDto {

  public void checkParam() {
    if (StringUtils.isBlank(this.getPropertyCode())) {
      this.setPropertyCode(IdWorker.getIdStr());
    }
  }
}

