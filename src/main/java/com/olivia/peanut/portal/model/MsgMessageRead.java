package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (MsgMessageRead)表实体类
 *
 * @author peanut
 * @since 2024-03-23 19:17:47
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("msg_message_read")
public class MsgMessageRead extends BaseEntity {

  private Long userId;
  private LocalDateTime lastReadTime;

}

