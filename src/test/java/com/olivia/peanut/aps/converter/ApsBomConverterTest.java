package com.olivia.peanut.aps.converter;

import com.olivia.sdk.utils.JSON;
import com.olivia.peanut.aps.api.entity.apsBom.ApsBomInsertReq;
import com.olivia.peanut.aps.model.ApsBom;
import org.junit.jupiter.api.Test;

class ApsBomConverterTest {

  @Test
  public void test() {
    ApsBomInsertReq apsBomInsertReq = new ApsBomInsertReq();
    apsBomInsertReq.setApsBomSupplierId(1L);
    ApsBom apsBom = ApsBomConverter.INSTANCE.insertReq(apsBomInsertReq);
//    System.out.println(JSON.toJSONString(apsBom, JSONWriter.Feature.WriteLongAsString));
  }
}