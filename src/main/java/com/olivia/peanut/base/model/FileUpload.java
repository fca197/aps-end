package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (FileUpload)表实体类
 *
 * @author peanut
 * @since 2024-03-18 15:22:31
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("t_file_upload")
public class FileUpload extends BaseEntity {

  private String fileName;
  private String fileType;
  private String fileSuffix;
  private Integer fileSize;
  private String localFilePath;
  private String cloudFilePath;
  private String expireTime;

}

