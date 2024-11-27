package com.olivia.peanut.aps.api.entity.apsSchedulingIssueItem;

import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

/**
 * 排产下发详情(ApsSchedulingIssueItem)保存入参
 *
 * @author peanut
 * @since 2024-07-20 13:55:54
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingIssueItemInsertReq extends ApsSchedulingIssueItemDto {

  @NotNull(message = "当前日期不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @Size(max = 10, message = "当前日期不能超过{max}个", groups = {InsertCheck.class, UpdateCheck.class})
  private List<LocalDate> scheduledDayList;


}

