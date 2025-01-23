package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 操作日志(BaseOplog)表实体类
 *
 * @author makejava
 * @since 2024-11-30 16:01:02
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("base_oplog")
public class BaseOplog extends BaseEntity {
  /***
   *  操作内容
   */
  private String content;
  /***
   *  业务类型
   */
  private String businessType;
  /***
   *  业务Key
   */
  private String businessKey;
  /***
   *  请求地址
   */
  private String url;
  /***
   *  耗时
   */
  private String useTime;
  /***
   *  参数名 参数1,参数2
   */
  private String paramName;
  /***
   *  请求入参
   */
  private String reqBody;
  /***
   *  请求入参
   */
  private String resultStr;
  /***
   *  备注
   */
  private String remark;
  private String traceId;

  private String methodName;
}

