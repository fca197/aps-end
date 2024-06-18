package com.olivia.peanut.aps.model.sub.constrained;

import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.utils.constrained.model.sub.OperatorEnum;
import com.olivia.peanut.aps.utils.constrained.model.sub.constrained.ConstrainedField;
import com.olivia.peanut.aps.utils.constrained.model.sub.constrained.FieldConfig;
import com.olivia.peanut.aps.utils.constrained.model.sub.constrained.Operator;
import com.olivia.peanut.aps.utils.constrained.model.sub.constrained.ValueType;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ConstrainedFieldObjTest {

  @Test
  public void test() {

    ConstrainedField constrainedField = new ConstrainedField();
    constrainedField.setName("销售值");
    FieldConfig fieldConfig = new FieldConfig();
    fieldConfig.setFieldName("createTime");
    fieldConfig.setShowName("创建时间");
    fieldConfig.setOperator(List.of(new Operator().setValue(OperatorEnum.BETWEEN.getValue()).setName(OperatorEnum.BETWEEN.getName()),
        new Operator().setValue(OperatorEnum.NE.getValue()).setName(OperatorEnum.NE.getName()),
        new Operator().setValue(OperatorEnum.EQ.getValue()).setName(OperatorEnum.EQ.getName())));
    fieldConfig.setValueType(ValueType.TEXT);
    constrainedField.setValues(List.of(fieldConfig));

    log.info("parsedObject:{}", JSON.toJSONString(constrainedField));
  }
}
