package com.olivia.peanut.ann;

import com.olivia.peanut.enums.CheckEnums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckObjectFieldValueAnn {
  boolean allowEmpty() default false;

  CheckEnums checkEnum() default CheckEnums.Str;

  long min() default 0;

  long max() default Integer.MAX_VALUE;

  long[] longValues() default {};

  int[] intValues() default {};

  String[] strValues() default {};

  String message() default "";

  String fieldShowName() default "未知";
}
