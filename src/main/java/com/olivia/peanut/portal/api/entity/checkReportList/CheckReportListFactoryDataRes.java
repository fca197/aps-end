package com.olivia.peanut.portal.api.entity.checkReportList;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class CheckReportListFactoryDataRes {

  private List<Info> dataList;

  @Setter
  @Getter
  @Accessors(chain = true)
  public static class Info {

    private Long checkId;
    private Integer roomSort;
    private Integer storeySort;
    private Long roomId;
    private String storeyName;
    private String roomName;
    private Long checkCount;
    private Long allCount;
    private String checkResult;
  }
}
