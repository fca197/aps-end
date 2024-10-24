package com.olivia.peanut.aps.utils.constrained;

import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.utils.constrained.model.ConstrainedResult;
import com.olivia.peanut.aps.utils.constrained.model.sub.*;

import java.util.*;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ConstrainedContentUtilsTest {

  @Test
  public void test() {
    List<Map<String, Object>> mapList = new ArrayList<>();
    IntStream.range(0, 100).forEach(i -> {
      Map<String, Object> map = new HashMap<>();
      map.put("orderTotalPrice", i);
      mapList.add(map);
    });
    String str = "[{\"filterList\":[{\"filterFieldType\":\"ORDER\",\"fieldName\":\"orderTotalPrice\",\"operator\":\"BETWEEN\",\"valueList\":[\"30\",\"60\"],\"id\":\"1S79b8nr2u5mo3icg3TofwMSedt6fsUYUsq1gs\"}],\"children\":[{\"filterList\":[{\"filterFieldType\":\"TEXT\",\"fieldName\":\"orderTotalPrice\",\"operator\":\"GE\",\"valueList\":[\"50\"],\"id\":\"1dH1asdgE4n4CuLOqhELSle4NfKjzImEMIIaBo\",\"showName\":\"订单-总价\"}],\"children\":[],\"orderBy\":[{\"fieldName\":\"orderTotalPrice\",\"orderType\":\"ASC\"}],\"id\":\"BwCwSlFhYZNm1WrCkyYLgu1M7hsv8lh7lasW1k\"}],\"orderBy\":[{\"orderFieldName\":\"orderTotalPrice\",\"orderBy\":\"ASC\",\"fieldName\":\"orderTotalPrice\",\"orderType\":\"DSC\"}],\"id\":\"0CRr2sa2KrFQF5usOcS6jaUhVy5yBO8DUlgRfU\"},{\"filterList\":[{\"filterFieldType\":\"TEXT\",\"fieldName\":\"orderTotalPrice\",\"operator\":\"GE\",\"valueList\":[\"90\"],\"showName\":\"订单-总价\",\"id\":\"8kzRcsiSPSKDVsVlMvPEoZBsj2SJLvr74w12uk\"}],\"children\":[],\"orderBy\":[{\"orderFieldName\":\"orderTotalPrice\",\"orderBy\":\"DSC\",\"fieldName\":\"orderTotalPrice\",\"orderType\":\"DSC\"}],\"id\":\"6gLf2Rd3kteU7sNPFUqcIr4dT9Bpqr7JwSZFdI\"},{\"filterList\":[{\"filterFieldType\":\"TEXT\",\"fieldName\":\"orderTotalPrice\",\"operator\":\"LT\",\"valueList\":[\"33\"],\"showName\":\"订单-总价\",\"id\":\"DqYpZ3krRZhIpSEaY26PTLsOfN11fHm5JPhrzH\"}],\"children\":[],\"orderBy\":[{\"orderFieldName\":\"orderTotalPrice\",\"orderBy\":\"ASC\",\"fieldName\":\"orderTotalPrice\",\"orderType\":\"DSC\"}],\"id\":\"Y8lZ5d1nmjuR2tVpFDrL5fikgWM10l767T1vDt\"}]";
    ConstrainedResult constrained = ConstrainedContentUtils.constrained(mapList, JSON.parseArray(str, ConstrainedContent.class));
    log.info("{}", JSON.toJSONString(constrained));
  }

  @Test
  public void test3() {
    List<Map<String, Object>> mapList = new ArrayList<>();
    IntStream.range(0, 100).forEach(i -> {
      Map<String, Object> map = new HashMap<>();
      map.put("orderTotalPrice", i);
      mapList.add(map);
    });
    mapList.sort(Comparator.nullsLast(Comparator.comparing((Map<String, Object> o) -> ((Number) o.get("orderTotalPrice")).intValue()).reversed()));
    log.info("{}", JSON.toJSONString(mapList));
  }

  @Test
  public void test2() {
    List<Map<String, Object>> mapList = new ArrayList<>();
    IntStream.range(0, 100).forEach(i -> {
      Map<String, Object> map = new HashMap<>();
      map.put("price", i);
      mapList.add(map);
    });
    List<ConstrainedContent> constrainedList = new ArrayList<>();
    constrainedList.add(new ConstrainedContent().setFilterList(List.of(new Filter().setFieldName("price").setValueList(List.of("30", "50")).setOperator(OperatorEnum.BETWEEN)))
        .setChildren(List.of(new ConstrainedContent().setFilterList(List.of(new Filter().setFieldName("price").setValueList(List.of("35", "45")).setOperator(OperatorEnum.BETWEEN)))
            .setOrderBy(List.of(new OrderBy().setFieldName("price").setOrderType(OrderByEnum.DSC))))));
    constrainedList.add(new ConstrainedContent().setFilterList(List.of(new Filter().setFieldName("price").setValueList(List.of("80")).setOperator(OperatorEnum.GE)))
        .setOrderBy(List.of(new OrderBy().setFieldName("price").setOrderType(OrderByEnum.DSC))));
    constrainedList.add(new ConstrainedContent().setFilterList(List.of(new Filter().setFieldName("price").setValueList(List.of("25")).setOperator(OperatorEnum.LE)))
        .setOrderBy(List.of(new OrderBy().setFieldName("price").setOrderType(OrderByEnum.DSC))));
    ConstrainedResult constrained = ConstrainedContentUtils.constrained(mapList, constrainedList);
    log.info("{}", JSON.toJSONString(constrained));
  }
}
