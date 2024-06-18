package com.olivia.peanut.aps.api.entity.apsGoodsForecast;

import com.olivia.sdk.dto.ExcelErrorMsg;
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
public class UploadTemplateRes {

  // 300 表格错误
  private Integer subCode;
  private List<ExcelErrorMsg> excelErrorMsgList;

}
