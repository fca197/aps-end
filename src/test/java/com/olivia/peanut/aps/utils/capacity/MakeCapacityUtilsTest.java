package com.olivia.peanut.aps.utils.capacity;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.utils.capacity.model.Limit;
import com.olivia.peanut.aps.utils.capacity.model.MakeCapacityResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class MakeCapacityUtilsTest {

  @org.junit.jupiter.api.Test
  void makeCapacity() {
    List<Map<String, Object>> mapList = new ArrayList<>();
    IntStream.range(0, 100).forEach(i -> {
      Map<String, Object> map = new HashMap<>();
      map.put("id", i);
//      map.put("fid", RandomUtil.randomInt(1, 3));
      map.put("gid", RandomUtil.randomInt(1, 3) + "");
      map.put("name", "name" + i);
      mapList.add(map);
    });
    ArrayList<Limit> limitListAll = new ArrayList<>();
    IntStream.range(0, 5).forEach(d -> {
      {
        Limit limit = new Limit();
        limit.setCurrentDate("2021-01-0" + d);
        limit.setFieldName("gid");
        limit.setFieldValue("1");
        limit.setCurrentCount(0);
        limit.setMin(1);
        limit.setMax(2);
        limitListAll.add(limit);
      }
      {
        Limit limit = new Limit();
        limit.setCurrentDate("2021-01-0" + d);
        limit.setFieldName("gid");
        limit.setFieldValue("2");
        limit.setCurrentCount(0);
        limit.setMin(1);
        limit.setMax(2);
        limitListAll.add(limit);
      }
    });

    MakeCapacityResult capacity = MakeCapacityUtils.capacity(mapList, limitListAll);
    log.info("capacity:{}", JSON.toJSONString(capacity));
  }
}
