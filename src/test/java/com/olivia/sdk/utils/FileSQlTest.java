package com.olivia.sdk.utils;

import cn.hutool.core.io.FileUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/***
 *
 */
@Setter
@Slf4j
@Getter
@Accessors(chain = true)
public class FileSQlTest {

  @Test
  public void test() {

    File file = new File("/Users/wangbao/sql");
    File[] list = file.listFiles((dir, name) -> name.endsWith(".sql"));
    List<String> listContents = new ArrayList<>();
    for (File f : list) {
      listContents.addAll(FileUtil.readLines(f, StandardCharsets.UTF_8));
      listContents.add("\n");
    }
//    log.info("{}", listContents);

    FileUtil.writeLines(listContents, "/Users/wangbao/sql/all.sqltmp", StandardCharsets.UTF_8);
  }
}
