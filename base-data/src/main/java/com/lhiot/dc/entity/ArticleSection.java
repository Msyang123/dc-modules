package com.lhiot.dc.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author xiaojian  created in  2018/11/21 15:36
 */
@Data
@ApiModel
public class ArticleSection {
    @ApiModelProperty(notes = "主键Id", dataType = "Long", readOnly = true)
    private Long id;
    @ApiModelProperty(notes = "位置ID", dataType = "Long")
    private Long positionId;
    @NotNull(message = "板块中文名称不能为空")
    @ApiModelProperty(notes = "板块中文名称", dataType = "String")
    private String nameCn;
    @ApiModelProperty(notes = "板块英文名称", dataType = "String")
    private String nameEn;
    @ApiModelProperty(notes = "父级编号", dataType = "Long")
    private Long parentId;
    @ApiModelProperty(notes = "创建时间", dataType = "Date", readOnly = true)
    private Date createAt;
    @ApiModelProperty(notes = "序号", dataType = "Integer")
    private Integer sorting;
    @ApiModelProperty(notes = "应用类型", dataType = "String")
    private String applicationType;

}
