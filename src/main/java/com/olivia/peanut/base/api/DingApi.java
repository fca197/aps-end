package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.ding.CodeLoginReq;
import com.olivia.peanut.base.api.entity.ding.CodeLoginRes;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface DingApi {
  @PostMapping("/ding/code/login")
  CodeLoginRes codeLogin(@RequestBody @Valid CodeLoginReq req);

}
