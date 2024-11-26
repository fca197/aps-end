package com.olivia.peanut.util;

import cn.hutool.core.util.ReflectUtil;
import com.olivia.peanut.ann.CheckObjectFieldValueAnn;
import com.olivia.peanut.enums.CheckEnums;
import com.olivia.sdk.dto.ExcelErrorMsg;
import jodd.util.ArraysUtil;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.Arrays;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.lang.Boolean.FALSE;

@Slf4j
public class CheckObjectFieldValueUtils {

  public static List<ExcelErrorMsg> check(Object obj) {
    if (obj == null) {
      return null;
    }

    Field[] fields = ReflectUtil.getFields(obj.getClass(), field -> field.isAnnotationPresent(CheckObjectFieldValueAnn.class));

    if (Arrays.isNullOrEmpty(fields)) {
      return null;
    }
    List<ExcelErrorMsg> errorMsgList = new ArrayList<>();
    for (Field field : fields) {
      Object fieldValue = ReflectUtil.getFieldValue(obj, field);
      CheckObjectFieldValueAnn checkObjectFieldValueAnn = field.getAnnotation(CheckObjectFieldValueAnn.class);
      ExcelErrorMsg excelErrorMsg = checkObjectValue(fieldValue, checkObjectFieldValueAnn);
      if (Objects.nonNull(checkObjectFieldValueAnn)) {
        errorMsgList.add(excelErrorMsg);
      }
    }


    return errorMsgList;
  }

  static Map<String, BiFunction<Object, CheckObjectFieldValueAnn, ExcelErrorMsg>> functionMap = new HashMap<>();

  static {
    // key 为    ExcelCheckEnums // 枚举值
    functionMap.put("Int", CheckObjectFieldValueUtils::checkObjectValueInt);
    functionMap.put("Long", CheckObjectFieldValueUtils::checkObjectValueLong);
    functionMap.put("Str", CheckObjectFieldValueUtils::checkObjectValueString);
  }


  private static ExcelErrorMsg checkObjectValue(Object obj, CheckObjectFieldValueAnn checkObjectFieldValueAnn) {
    if (Objects.isNull(obj)) {
      if (FALSE.equals(checkObjectFieldValueAnn.allowEmpty())) {
        return new ExcelErrorMsg().setColumnName(checkObjectFieldValueAnn.fieldShowName()).setErrMsg("字段未填写");
      } else {
        return null;
      }
    }
    CheckEnums checkEnum = checkObjectFieldValueAnn.checkEnum();
    if (Objects.nonNull(checkEnum)) {
      BiFunction<Object, CheckObjectFieldValueAnn, ExcelErrorMsg> function = functionMap.get(checkEnum.name());
      if (Objects.nonNull(function)) {
        ExcelErrorMsg apply = function.apply(obj, checkObjectFieldValueAnn);
        if (Objects.nonNull(apply)) {
          return apply;
        }
      }
    }
    return null;
  }

  private static ExcelErrorMsg checkObjectValueString(Object objVal, CheckObjectFieldValueAnn checkObjectFieldValueAnn) {
    // 判断数组
    String obj = (String) objVal;
    String[] strValues = checkObjectFieldValueAnn.strValues();
    if (strValues != null && strValues.length > 0) {
      if (!ArraysUtil.contains(strValues, obj)) {
        return new ExcelErrorMsg().setColumnName(checkObjectFieldValueAnn.fieldShowName()).setErrMsg("值[" + obj + "]不在范围" + java.util.Arrays.stream(strValues).toList() + "内");
      }
    }
    // 判断大小
    int valLength = obj.length();
    if (valLength < checkObjectFieldValueAnn.min() || valLength > checkObjectFieldValueAnn.max()) {
      return new ExcelErrorMsg().setColumnName(checkObjectFieldValueAnn.fieldShowName()).setErrMsg("值长度[" + obj + "]不在范围[" + checkObjectFieldValueAnn.min() + "," + checkObjectFieldValueAnn.max() + "]内");
    }

    return null;
  }

  private static ExcelErrorMsg checkObjectValueInt(Object objVal, CheckObjectFieldValueAnn checkObjectFieldValueAnn) {

    int obj = (int) objVal;
    // 判断数组
    int[] ints = checkObjectFieldValueAnn.intValues();
    if (!Arrays.isNullOrEmpty(ints)) {
      if (!ArraysUtil.contains(ints, obj)) {
        return new ExcelErrorMsg().setColumnName(checkObjectFieldValueAnn.fieldShowName()).setErrMsg("值[" + obj + "]不在范围" + Stream.of(ints).toList() + "内");
      }
    }
    // 判断大小
    if (obj < checkObjectFieldValueAnn.min() || obj > checkObjectFieldValueAnn.max()) {
      return new ExcelErrorMsg().setColumnName(checkObjectFieldValueAnn.fieldShowName()).setErrMsg("值[" + obj + "]不在范围[" + checkObjectFieldValueAnn.min() + "," + checkObjectFieldValueAnn.max() + "]内");
    }

    return null;
  }

  private static ExcelErrorMsg checkObjectValueLong(Object objVal, CheckObjectFieldValueAnn checkObjectFieldValueAnn) {

    int obj = (int) objVal;
    // 判断数组
    long[] longValues = checkObjectFieldValueAnn.longValues();
    if (longValues != null && longValues.length > 0) {
      if (!ArraysUtil.contains(longValues, obj)) {
        return new ExcelErrorMsg().setColumnName(checkObjectFieldValueAnn.fieldShowName()).setErrMsg("值[" + obj + "]不在范围" + Stream.of(longValues).toList() + "内");
      }
    }
    // 判断大小
    if (obj < checkObjectFieldValueAnn.min() || obj > checkObjectFieldValueAnn.max()) {
      return new ExcelErrorMsg().setColumnName(checkObjectFieldValueAnn.fieldShowName()).setErrMsg("值[" + obj + "]不在范围[" + checkObjectFieldValueAnn.min() + "," + checkObjectFieldValueAnn.max() + "]内");
    }

    return null;
  }


}
