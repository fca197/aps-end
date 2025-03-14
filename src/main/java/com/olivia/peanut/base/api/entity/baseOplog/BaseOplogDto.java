package com.olivia.peanut.base.api.entity.baseOplog;

// import com.alibaba.fastjson2.annotation.JSONField;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 操作日志(BaseOplog)查询对象返回
 *
 * @author makejava
 * @since 2024-11-30 16:01:02
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseOplogDto extends BaseEntityDto {

  /***
   *  操作内容
   */
  @NotBlank(message = "操作内容不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "content")

  private String content;
  /***
   *  业务类型
   */
  @NotBlank(message = "业务类型不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "businessType")

  private String businessType;
  /***
   *  业务Key
   */
  @NotBlank(message = "业务Key不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "businessKey")

  private String businessKey;
  /***
   *  请求地址
   */
  @NotBlank(message = "请求地址不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "url")

  private String url;
  /***
   *  耗时
   */
  @NotBlank(message = "耗时不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "useTime")

  private String useTime;
  /***
   *  参数名 参数1,参数2
   */
  @NotBlank(message = "参数名 参数1,参数2不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "paramName")

  private String paramName;
  /***
   *  请求入参
   */
  @NotBlank(message = "请求入参不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "reqBody")

  private String reqBody;
  /***
   *  请求入参
   */
  @NotBlank(message = "请求入参不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "resultStr")

  private String resultStr;
  /***
   *  备注
   */
  @NotBlank(message = "备注不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  //@JSONField(label = "remark")

  private String remark;
  private String methodName;

  private String traceId;

  //@JSONField(serializeUsing = Boolean2StrFeature.class)
  private Boolean isSuccess;

}


