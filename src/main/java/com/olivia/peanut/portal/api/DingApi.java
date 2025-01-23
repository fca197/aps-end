package com.olivia.peanut.portal.api;

import com.olivia.peanut.portal.api.entity.ding.CodeLoginReq;
import com.olivia.peanut.portal.api.entity.ding.CodeLoginRes;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface DingApi {
  @PostMapping("/ding/code/login")
  CodeLoginRes codeLogin(@RequestBody @Valid CodeLoginReq req);

}
