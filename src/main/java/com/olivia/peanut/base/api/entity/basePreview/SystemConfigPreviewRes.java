package com.olivia.peanut.base.api.entity.basePreview;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class SystemConfigPreviewRes {

  private List<Info> dataList;

  @Setter
  @Getter
  @Accessors(chain = true)
  public static class Info {
    Long refId;
    String name;
    Long value;
    String desc;
    List<Info> children;
  }
}
