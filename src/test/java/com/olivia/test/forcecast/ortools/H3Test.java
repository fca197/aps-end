package com.olivia.test.forcecast.ortools;

import com.uber.h3core.H3Core;
import com.uber.h3core.util.LatLng;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class H3Test {

  @Test
  @SneakyThrows
  void test() {
    H3Core h3Core = H3Core.newInstance();

    // 将一个经纬度坐标和层级转换为h3区域
    // 获得的meshIndex时long类型网格索引
    long meshIndex = h3Core.latLngToCell(20.0, 130.0, 6);
    String code = h3Core.h3ToString(meshIndex);
    System.out.println("meshIndex:" + meshIndex + " " + code);
    // 将网格索引变为六边形区域
    List<LatLng> latLngs = h3Core.cellToBoundary(meshIndex);
    System.out.println("六边形区域：" + latLngs);
    List<LatLng> latLngListAll = h3Core.cellToBoundary(code);
    System.out.println("六边形区域：" + latLngListAll);

    // 在4.1.1中有获取区域的索引，相信用到这个的人挺多
    List<LatLng> areas = new ArrayList<>();
    areas.add(new LatLng(66.66, 55.55));
    areas.add(new LatLng(33.33, 55.55));
    areas.add(new LatLng(33.33, 77.77));
    areas.add(new LatLng(66.66, 77.77));
    // 区域内的网格索引
    List<Long> longs = h3Core.polygonToCells(areas, null, 2);
    // 转换为六边形
    for (Long aLong : longs) {
      // 将网格索引变为六边形区域
      List<LatLng> latLngList = h3Core.cellToBoundary(aLong);
      System.out.println(latLngList);
    }

    // 如何获取某一层级下的全量网格呢？api中只提供了0层级的全量网格，往下看
    // 0层级下的全量网格
    Collection<Long> res0Cells = h3Core.getRes0Cells();
    // 0层级下的网格个数
    long numCells = h3Core.getNumCells(0);
    System.out.println("层级0下的网格索引数：" + numCells);
    // 重点来了，以层级2为例
    int res2IndexNum = 0;
    for (Long res0Cell : res0Cells) {
      // 此时用res0Cell网格索引和子层级2就获取到了子层级下的索引
      // 这里就要注意到h3在存储网格索引时使用的编码结构了，官方叫
      // FaceIJK坐标系，可以参考我帖的链接了解一下
      List<Long> longs1 = h3Core.cellToChildren(res0Cell, 2);
      res2IndexNum = res2IndexNum + longs1.size();
    }
    System.out.println("层级2下的网格索引数：" + res2IndexNum);

  }
}
