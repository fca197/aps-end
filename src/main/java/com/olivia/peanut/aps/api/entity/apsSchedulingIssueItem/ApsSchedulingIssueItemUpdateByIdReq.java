package com.olivia.peanut.aps.api.entity.apsSchedulingIssueItem;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 排产下发详情(ApsSchedulingIssueItem)表实体类
 *
 * @author peanut
 * @since 2024-07-20 13:55:55
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingIssueItemUpdateByIdReq extends ApsSchedulingIssueItemDto {


  public void checkParam() {
  }

}
