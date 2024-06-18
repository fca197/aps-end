package com.olivia.test.forcecast;

import com.google.ortools.Loader;
import com.olivia.peanut.aps.utils.forecast.OrToolsUtils;
import com.olivia.peanut.aps.utils.forecast.model.OrToolsComputeRes;
import com.olivia.peanut.aps.utils.forecast.model.SaleItemConfig;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class OrToolsUtilsTest {

  List<List<SaleItemConfig>> saleItemConfigList = List.of(List.of(new SaleItemConfig().setSaleCode("A1").setTarget(0.45), new SaleItemConfig().setSaleCode("A2").setTarget(0.55))
//      , List.of(new SaleItemConfig().setSaleCode("B1").setTarget(0.36), new SaleItemConfig().setSaleCode("B2").setTarget(0.64))
//     , List.of(new SaleItemConfig().setSaleCode("C1").setTarget(0.22), new SaleItemConfig().setSaleCode("D2").setTarget(0.78))
//    ,  List.of(new SaleItemConfig().setSaleCode("D1").setTarget(0.2), new SaleItemConfig().setSaleCode("D2").setTarget(0.8))
//    ,  List.of(new SaleItemConfig().setSaleCode("E1").setTarget(0.3), new SaleItemConfig().setSaleCode("E2").setTarget(0.7))
    ,  List.of(new SaleItemConfig().setSaleCode("F1").setTarget(0.9), new SaleItemConfig().setSaleCode("F2").setTarget(0.1))
      , List.of(new SaleItemConfig().setSaleCode("G1").setTarget(0.4), new SaleItemConfig().setSaleCode("G2").setTarget(0.6)));
  int count = 160;

  {
    Loader.loadNativeLibraries();
  }

  public static String biggestRedundantSubstring(String str) {
    return IntStream.range(0, str.length()) // generating starting indices
        .boxed()
        .flatMap(start -> IntStream.rangeClosed(start + 1, str.length())
            .mapToObj(end -> str.substring(start, end))) // generating ending indices and turning each combination of `start` and `end` into a substring
        .collect(Collectors.toMap(   // creating an intermediate map Map<String, Boolean>
            Function.identity(),
            s -> false,              // is repeated substring = false, because element has been encountered for the first time
            (v1, v2) -> true         // substring has been encountered more than once, i.e. is proved to be repeated
        ))
        .entrySet().stream()
        .filter(Map.Entry::getValue) // filtering the repeated substrings
        .max(Map.Entry.comparingByKey(Comparator.comparingInt(String::length))) // returns Optional<Map.Entry<String, Boolean>>
        .map(Map.Entry::getKey)      // returns Optional<String>
        .orElse("");                 // if Optional is empty return an empty string

  }

  @Test
  public void compute() {

    //{
    //	"A1_B2_D2": [70.0, 0.7],
    //	"A1_B2_C1": [20.0, 0.2],
    //	"A1_B1_C1": [20.0, 0.2],
    //	"A1_B1_D2": [30.0, 0.3]
    //}

    try {
      List<OrToolsComputeRes> resList = OrToolsUtils.compute(saleItemConfigList, count);
    } catch (Exception e) {
      log.error("e", e);
//      count = count + 1;
//      compute();
    }

  }

  @Test
  public void test2() {

    List<String> set = new ArrayList<>();
    List<ParentSaleItemConfig> list = new ArrayList<>();
    list.add(new ParentSaleItemConfig().setKey("A")
        .setList(new ArrayList<>(List.of(new SaleItemConfig().setSaleCode("A1").setTarget(5), new SaleItemConfig().setSaleCode("A2").setTarget(2),
            new SaleItemConfig().setSaleCode("A3").setTarget(3)))));
    list.add(new ParentSaleItemConfig().setKey("B")
        .setList(new ArrayList<>(List.of(new SaleItemConfig().setSaleCode("B1").setTarget(2), new SaleItemConfig().setSaleCode("B2").setTarget(7),
            new SaleItemConfig().setSaleCode("B3").setTarget(1)))));
    list.add(new ParentSaleItemConfig().setKey("C")
        .setList(new ArrayList<>(List.of(new SaleItemConfig().setSaleCode("C1").setTarget(3),
            new SaleItemConfig().setSaleCode("C2").setTarget(1),
            new SaleItemConfig().setSaleCode("C3").setTarget(2), new SaleItemConfig().setSaleCode("C4").setTarget(2)
            , new SaleItemConfig().setSaleCode("C5").setTarget(1), new SaleItemConfig().setSaleCode("C6").setTarget(1), new SaleItemConfig().setSaleCode("C7").setTarget(1)
        ))));
    list.add(new ParentSaleItemConfig().setKey("D")
        .setList(new ArrayList<>(List.of(new SaleItemConfig().setSaleCode("D1").setTarget(9), new SaleItemConfig().setSaleCode("D2").setTarget(1)))));
    list.add(new ParentSaleItemConfig().setKey("E")
        .setList(new ArrayList<>(List.of(new SaleItemConfig().setSaleCode("E1").setTarget(4), new SaleItemConfig().setSaleCode("E2").setTarget(6)))));
    list.add(new ParentSaleItemConfig().setKey("F")
        .setList(new ArrayList<>(List.of(new SaleItemConfig().setSaleCode("F1").setTarget(1), new SaleItemConfig().setSaleCode("F2").setTarget(1),
            new SaleItemConfig().setSaleCode("F3").setTarget(1), new SaleItemConfig().setSaleCode("F4").setTarget(1), new SaleItemConfig().setSaleCode("F5").setTarget(6)))));
    list.add(new ParentSaleItemConfig().setKey("G")
        .setList(new ArrayList<>(List.of(new SaleItemConfig().setSaleCode("G1").setTarget(6), new SaleItemConfig().setSaleCode("G2").setTarget(4)))));
    list.add(new ParentSaleItemConfig().setKey("H")
        .setList(new ArrayList<>(List.of(new SaleItemConfig().setSaleCode("H").setTarget(7), new SaleItemConfig().setSaleCode("H2").setTarget(3)))));
    list.add(new ParentSaleItemConfig().setKey("J")
        .setList(new ArrayList<>(List.of(new SaleItemConfig().setSaleCode("J1").setTarget(1), new SaleItemConfig().setSaleCode("J2").setTarget(1)
            , new SaleItemConfig().setSaleCode("J3").setTarget(1), new SaleItemConfig().setSaleCode("J4").setTarget(3), new SaleItemConfig().setSaleCode("J5").setTarget(1)
            , new SaleItemConfig().setSaleCode("J6").setTarget(2), new SaleItemConfig().setSaleCode("J7").setTarget(1), new SaleItemConfig().setSaleCode("J8").setTarget(1)
        ))));
    list.add(new ParentSaleItemConfig().setKey("L")
        .setList(new ArrayList<>(List.of(new SaleItemConfig().setSaleCode("L1").setTarget(10), new SaleItemConfig().setSaleCode("L2").setTarget(0)))));
    list.add(new ParentSaleItemConfig().setKey("M")
        .setList(new ArrayList<>(List.of(new SaleItemConfig().setSaleCode("M1").setTarget(0), new SaleItemConfig().setSaleCode("M2").setTarget(10)))));
    AtomicInteger currendex = new AtomicInteger(0);
    Map<String, Integer> currentMap = new HashMap<>();
    list.forEach(t -> {
          List<SaleItemConfig> tml = new ArrayList<>();
          List<SaleItemConfig> itemConfigList = t.getList();
          itemConfigList.forEach(tt -> {
            int c = (int) tt.getTarget();
            for (int i = 0; i < c; i++) {
              tml.add(tt);
            }
          });
          t.setList(tml);
        }

    );
    while (true) {
      List<String> ar = new ArrayList<>();
      list.forEach(t -> {
        int sum = t.getList().size();
        Integer ci = currentMap.getOrDefault(t.getKey(), 0);
        ci = Objects.isNull(ci) ? 0 : ci;
        ci = ci < sum ? ci : 0;
//        log.info(" {} ci {} sum {}", t.key, ci, sum);
        List<SaleItemConfig> itemConfigList = t.getList();
        if (Objects.equals(ci, 0)) {
          ar.add(itemConfigList.get(0).getSaleCode());
          ci++;
        } else {
//          AtomicInteger tm = new AtomicInteger();
          SaleItemConfig saleItemConfig = itemConfigList.get(ci);
          ar.add(saleItemConfig.getSaleCode());
          ci++;
        }
        currentMap.put(t.key, ci);

      });
      String s = String.join(",", ar);
      if (currendex.get() >= 220) {
        break;
      }
      set.add(s);
      currendex.getAndIncrement();
    }
    String join = String.join("--", set);
    log.info("{}", join);

    String s = biggestRedundantSubstring(join);
    log.info("{}", s);
  }

  @Setter
  @Getter
  @Accessors(chain = true)
  class ParentSaleItemConfig {

    List<SaleItemConfig> list = new ArrayList<>();
    private String key;
  }
}
