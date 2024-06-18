package com.olivia.peanut.portal.api.entity.jcxBuyPlan;

import cn.hutool.core.collection.CollUtil;
import com.olivia.peanut.portal.api.entity.jcxBuyPlanItem.JcxBuyPlanItemDto;
import com.olivia.sdk.exception.CanIgnoreException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

/**
 * (JcxBuyPlan)保存入参
 *
 * @author peanut
 * @since 2024-03-24 20:27:10
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyPlanInsertReq extends JcxBuyPlanDto {


  public void checkParam() {
    List<String> msgList = new ArrayList<>();
    if (CollUtil.isEmpty(this.getJcxBuyPlanItemDtoList())) {
      msgList.add("商品不能为空");
    } else {
      Map<Long, List<JcxBuyPlanItemDto>> goodsMap = this.getJcxBuyPlanItemDtoList().stream().collect(Collectors.groupingBy(JcxBuyPlanItemDto::getGoodsId));
      goodsMap.values().stream().filter(list -> list.size() > 1).forEach(list -> {
        msgList.add("商品id:" + list.get(0).getGoodsName() + "不能重复");
      });
      this.getJcxBuyPlanItemDtoList().stream().filter(t -> StringUtils.isBlank(t.getGoodsName()) || Objects.isNull(t.getGoodsBuyCount()) || t.getGoodsBuyCount() < 1).forEach(t -> {
        msgList.add("商品id:" + t.getGoodsName() + "信息不正确");
      });
    }
    if (CollUtil.isNotEmpty(msgList)) {
      throw new CanIgnoreException(String.join(",", msgList));
    }
  }
}

