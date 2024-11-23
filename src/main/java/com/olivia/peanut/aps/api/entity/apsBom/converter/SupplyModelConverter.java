package com.olivia.peanut.aps.api.entity.apsBom.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.olivia.peanut.aps.api.entity.apsBom.SupplyModelEnum;

import java.util.HashMap;
import java.util.Map;

public class SupplyModelConverter implements Converter<String> {

  public static Map<String, String> supplyModelToMap = new HashMap<>();

  static {
    for (SupplyModelEnum supplyModelEnum : SupplyModelEnum.values()) {
      supplyModelToMap.put(supplyModelEnum.getDesc(), supplyModelEnum.name());
    }
  }

  @Override
  public String convertToJavaData(ReadConverterContext<?> context) throws Exception {
    String supplyModel = context.getReadCellData().getStringValue();
    return supplyModelToMap.get(supplyModel);
  }
}
