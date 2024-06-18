package com.olivia.peanut.portal.api.entity.fileUpload;

import static com.olivia.sdk.utils.Str.SQLITE;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.sdk.ann.BelongDb;
import lombok.Getter;
import lombok.Setter;

/**
 * (FileUpload)查询对象返回
 *
 * @author peanut
 * @since 2024-03-18 15:22:32
 */
//@Accessors(chain=true)
@Getter
@Setter
@BelongDb(SQLITE)
public class FileUploadDto {


  @ExcelProperty("序号")
  private Long id;
  @ExcelProperty("文件名称")
  private String fileName;
  private String fileType;
  @ExcelProperty("文件大小")
  private Integer fileSize;
  @ExcelProperty("本地地址")
  private String localFilePath;
  @ExcelProperty("云地址")
  private String cloudFilePath;
  @ExcelProperty("过期时间")
  private String expireTime;
  @ExcelProperty("创建时间")
  private String createTime;

  @ExcelProperty("更新时间")
  private String updateTime;
  @ExcelProperty("版本")
  private Integer versionNum;

}


