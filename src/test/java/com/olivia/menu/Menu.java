package com.olivia.menu;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

/***
 *
 */
@Setter
@Getter
@Slf4j
public class Menu {

  private String path;
  private String name;
  private List<Menu> children;
  private Long id;
  private Long parentId;
  private boolean hidden;


  @Test
  @SneakyThrows
  public void test() {
    String menu = IOUtils.resourceToString(
        "menu.json",
        StandardCharsets.UTF_8, Menu.class.getClassLoader());

    List<Menu> menus = JSON.parseArray(menu, Menu.class);
    setMenuId(menus, 0L);
    log.info("{}", JSON.toJSONString(menus));

    System.out.println("insert into base_resource (id,resource_code,resource_name,resource_url,parent_id ,is_hidden,tenant_id ) values ");

    print(menus);
  }


  private void print(List<Menu> menus) {
    if (CollUtil.isEmpty(menus)) {
      return;
    }
    menus.forEach(t -> {
      System.out.println("('" + String.join("','", t.id + "",   IdWorker.getIdStr(), t.name, t.path, t.parentId + "", Boolean.FALSE.equals(t.hidden) ? "0" : "1", "1001'),"));
      print(t.children);
    });
  }

  private void setMenuId(List<Menu> menus, Long parentId) {
    if (CollUtil.isNotEmpty(menus)) {
      menus.forEach(t -> {
        t.setId(IdWorker.getId());
        t.setParentId(parentId);
        setMenuId(t.children, t.id);
      });
    }
  }
}
