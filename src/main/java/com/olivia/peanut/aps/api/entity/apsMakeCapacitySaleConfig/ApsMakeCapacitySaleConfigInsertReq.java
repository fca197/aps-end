package com.olivia.peanut.aps.api.entity.apsMakeCapacitySaleConfig;

import com.olivia.peanut.aps.api.entity.apsMakeCapacityFactory.MakeCapacityConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsMakeCapacitySaleConfig)保存入参
 *
 * @author peanut
 * @since 2024-04-13 12:05:06
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMakeCapacitySaleConfigInsertReq extends ApsMakeCapacitySaleConfigDto {


  private List<MakeCapacityConfig> makeCapacityConfigList;


}

