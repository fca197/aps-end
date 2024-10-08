package com.olivia.test.forcecast.ortools;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class TestMain {

  public static String[] chars = new String[]{
      "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
      "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
      "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
      "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
      "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


  public static String generateShortUuid() {
    StringBuffer shortBuffer = new StringBuffer();
    String uuid = IdWorker.getIdStr();
    for (int i = 0; i < 4; i++) {
      String str = uuid.substring(i * 4, i * 4 + 4);
      int x = Integer.parseInt(str, 36);
      shortBuffer.append(chars[x % 0x3E]);
    }
    return shortBuffer.toString();

  }

  @Test
  public void test() {
    System.out.println(generateShortUuid());
  }
}
