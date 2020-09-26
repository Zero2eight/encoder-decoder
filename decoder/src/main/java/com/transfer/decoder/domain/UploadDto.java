package com.transfer.decoder.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("解码json对象")
public class UploadDto {
    @ApiModelProperty("解码后储存在服务器中的文件名称")
    private String fileName;
    @ApiModelProperty("编码后的文件字符串")
    private String content;
}
