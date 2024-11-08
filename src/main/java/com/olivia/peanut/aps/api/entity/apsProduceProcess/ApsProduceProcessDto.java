package com.olivia.peanut.aps.api.entity.apsProduceProcess;

import com.olivia.peanut.aps.api.entity.apsProduceProcessItem.ApsProduceProcessItemDto;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import com.olivia.sdk.utils.Str;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * aps 生产路径(ApsProduceProcess)查询对象返回
 *
 * @author makejava
 * @since 2024-10-24 17:00:20
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProduceProcessDto extends BaseEntityDto {


  @NotNull(groups = {UpdateCheck.class, InsertCheck.class}, message = "工厂不能为空")
  private Long factoryId;
  private String factoryName;
  /***
   *  生产路径编码
   */
  @NotBlank(message = "生产路径编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String produceProcessNo;
  /***
   *  生产路径名称
   */
  @NotBlank(message = "生产路径名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String produceProcessName;
  @NotNull(message = "是否默认不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Boolean isDefault;

  @NotNull(message = "流程配置不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private List<ApsProduceProcessItemDto> produceProcessItemDtoList;

  public String getIsDefaultStr() {
    return Str.booleanToStr(this.isDefault);
  }
}


