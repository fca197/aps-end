package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsSaleProjectConfig)表实体类
 *
 * @author peanut
 * @since 2024-04-27 16:07:22
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_goods_sale_project_config")
public class ApsGoodsSaleProjectConfig extends BaseEntity {

  private Long goodsId;
  private Long factoryId;
  private Long projectConfigId;
  private String projectConfigName;
  private Long projectConfigParentId;
  private Long quantity;
  private Long saleConfigId;
  private String saleConfigName;
  private Long saleConfigParentId;

  private volatile Long currentIndex;

  public Long getCurrentIndex() {
    return Objects.nonNull(currentIndex) ? currentIndex : 1L;
  }
}

