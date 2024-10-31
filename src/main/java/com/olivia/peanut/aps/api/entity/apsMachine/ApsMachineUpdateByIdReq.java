package com.olivia.peanut.aps.api.entity.apsMachine;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * aps 生产机器(ApsMachine)表实体类
 *
 * @author makejava
 * @since 2024-10-24 16:31:14
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMachineUpdateByIdReq extends ApsMachineDto {


  public void checkParam() {
  }

}

