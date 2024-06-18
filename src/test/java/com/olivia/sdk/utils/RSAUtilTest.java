package com.olivia.sdk.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class RSAUtilTest {

  JSONObject map = JSON.parseObject(
      "{\"RSAPublicKey\":\"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApxyx2RMyHoMGLGy9LdKgIGVN7/6qQLzj5qa2TNRO1zYzQ4dIGr809fIWOPqc/DIuoZvr50dgMKg77kdO+6SUBdpuVeOPPMRU8txla8b33NmqnMykSARGHBiZ+jdLfA4s/S/IL+Z2E5DTjisbFHG1x3dKF66AKhAlxwte34aybZ5DSwornDlFk6ihNdZcpgd3yNC8oAxtTj4sOHu7Rwo/IP75MqiHBreGcvGF1j5te6GaVgIHQXEAJyVn9JgUrA639kikRC8e9ITN9BsW2halCa0WSaNeOnRWDze99yr2pDQjI4GW6MnEzjGRVmndtdePEJMAlUH31OMjcuNKf9gm+wIDAQAB\",\"eq\":\"true\",\"RSAPrivateKey\":\"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCnHLHZEzIegwYsbL0t0qAgZU3v/qpAvOPmprZM1E7XNjNDh0gavzT18hY4+pz8Mi6hm+vnR2AwqDvuR077pJQF2m5V4488xFTy3GVrxvfc2aqczKRIBEYcGJn6N0t8Diz9L8gv5nYTkNOOKxsUcbXHd0oXroAqECXHC17fhrJtnkNLCiucOUWTqKE11lymB3fI0LygDG1OPiw4e7tHCj8g/vkyqIcGt4Zy8YXWPm17oZpWAgdBcQAnJWf0mBSsDrf2SKRELx70hM30GxbaFqUJrRZJo146dFYPN733KvakNCMjgZboycTOMZFWad21148QkwCVQffU4yNy40p/2Cb7AgMBAAECggEAE2zsUY7/2E3OcGiS1G+eSdXCKbU2kOru1QzTtnpG0JE42U8xobD7VOGiFVfbMB0ckDsB652/lThhKxF39sbf/A7dnOH5xbcO0Y0EGcivCamgbF9aHhL9HAlSEM96WKkvL/PszICjgM0Tgnxem/xHL1tzA0NiG5b1RT4m9ksqlOpMz3jVT1lYDDUld2oHvflRh9xmp0gpK0yNIHxkqPjAxK5XmBpAUt9c7KHybwgIIj3XbN5rf3hf0UGJXl7WYMCPHkl4Hjl0YvoBlZo+4KSa6URoZDcFedB5HtWOQsjxtt1KtD6v/PzoZCNUuMvMyxF9/nLfPEt+2MK4oDppqEApgQKBgQDJ5r7TMpQzoQ9QC/hpWN2+i/JsPC9lZZikFoMo+UVNiqb+x2MArbTTvOR/iptCdKkZBmh6yTM0ZihCxrxJxEPO0eZr5WKzJlF5pW1lbMmqQx+lJKlL/0rC6IppeoNBO/1C+LR3IBYN6y3as2OUQjby/u//j8h0exiHdyxEgN3wgQKBgQDT45voJD30V0UFfbyfDP0dQPKu31pL74Ldwn7+S16bMDTgE1AhGcMCPZAYJTwiDSkHdMg3wJyVMv1FFWNUxJmrJ1rMA3/60JFM8Fde933dpqKIxmmvisFCY/+MyM5KoD+mSfM6jgcLfoUtVpjuaAexbRgeWKuM1GMocCDyXDkZewKBgQCOblqmMSM9vNxJkvg+kTN1TdLKKjchZ+ejskH68MbEKhEe4VflTaWoTbRAiW54FkaIYe3o2PFngL3cRcjknTFcwNS1vw++5ERL9bjT+UfW9h1n6hIIi+OY1mV2fbgEmCZVTZA6DYehcAxcIXsZRxDuoYx7lFpMWd53CkpPVT28gQKBgEN5qf5k/C3BVMOI5PYpZrlYI3A4xEKGRUBlXZeRXNFCqrPoso237HfcLvThdqLJWhV7xP6sIbEJicnkI7us19q/L5TkUG8I2ByONOSTH8vj8sJnnOQ/5YdE3O5yS+9L1LkZt5EgBXJNmg90EkNpcXm46gNP7WFtiR4phvRnFe/dAoGAWokf8hTFsNj6kY1WvO7yqfSlgjHEfvVuq4+C0uKi4xf/AO94TWnvMWNYE/qoHjwDmR1aahIzaub05EMT8kJXnWnhyfA+H9pS/ckHyY41TVMKvnHZEGW4Oc/U7NcxKSKvUxVQEAfECu+/0KkY8WsPew4Q3DcmMlShy5IXQ+TL76o=\",\"RSAPublicKey2\":\"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApxyx2RMyHoMGLGy9LdKgIGVN7/6qQLzj5qa2TNRO1zYzQ4dIGr809fIWOPqc/DIuoZvr50dgMKg77kdO+6SUBdpuVeOPPMRU8txla8b33NmqnMykSARGHBiZ+jdLfA4s/S/IL+Z2E5DTjisbFHG1x3dKF66AKhAlxwte34aybZ5DSwornDlFk6ihNdZcpgd3yNC8oAxtTj4sOHu7Rwo/IP75MqiHBreGcvGF1j5te6GaVgIHQXEAJyVn9JgUrA639kikRC8e9ITN9BsW2halCa0WSaNeOnRWDze99yr2pDQjI4GW6MnEzjGRVmndtdePEJMAlUH31OMjcuNKf9gm+wIDAQAB\"}\n");

  String rsaPublicKey = "RSAPublicKey";
  String rsaPrivateKey = "RSAPrivateKey";

  @Test
  void encrypt() {
    String encrypt = RSAUtil.encryptByPublicKey("123", map.getString(rsaPublicKey));
    String v = RSAUtil.decryptByPrivateKey(encrypt, map.getString("RSAPrivateKey"));
    log.info("{} {}", v, encrypt);
  }

  @Test
  void encryptByPublicKey() {
    String encrypt = RSAUtil.encryptByPrivateKey("123xxx", map.getString(rsaPrivateKey));
    String v = RSAUtil.decryptByPublicKey(encrypt, map.getString(rsaPublicKey));
    log.info("{} {}", v, encrypt);
  }

  @Test
  void generateKeyPair() {
    Map<String, String> map = RSAUtil.generateKeyPair();
    log.info("{}", JSON.toJSONString(map));
  }
}
