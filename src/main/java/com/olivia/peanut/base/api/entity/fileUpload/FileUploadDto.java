package com.olivia.peanut.base.api.entity.fileUpload;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
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
public class FileUploadDto extends BaseEntityDto {

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

}


