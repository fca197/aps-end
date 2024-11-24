package com.olivia.peanut.aps.api.entity.apsBom.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.olivia.peanut.aps.api.entity.apsBom.SupplyModelEnum;
import org.apache.commons.lang3.StringUtils;

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
    String v = supplyModelToMap.get(supplyModel);
    return StringUtils.firstNonEmpty(v, SupplyModelEnum.UNK.name());
  }
}
