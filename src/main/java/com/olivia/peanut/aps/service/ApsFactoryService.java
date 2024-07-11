package com.olivia.peanut.aps.service;

import com.olivia.peanut.aps.service.pojo.FactoryConfigReq;
import com.olivia.peanut.aps.service.pojo.FactoryConfigRes;

public interface ApsFactoryService {

  FactoryConfigRes getFactoryConfig(FactoryConfigReq req);
}
