package com.olivia.peanut.portal.api.entity.goods;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品信息(Goods)表实体类
 *
 * @author peanut
 * @since 2024-03-10 22:22:32
 */
@Data
@Accessors(chain = true)
public class GoodsDeleteReq {

  @NotNull(message = "删除条数不能为空")
  @Size(min = 1, max = 100, message = "删除条数必须介于 {min} 到 {max} 之间")
  private List<Long> idList;
}


