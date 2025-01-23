package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.basePreview.SystemConfigPreviewReq;
import com.olivia.peanut.base.api.entity.basePreview.SystemConfigPreviewRes;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public interface BasePreviewApi {

  @RequestMapping("/basePreview/info")
  SystemConfigPreviewRes systemConfigPreview(@RequestBody @Valid SystemConfigPreviewReq req);
}
