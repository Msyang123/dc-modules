package com.lhiot.dc.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiaojian  created in  2018/11/22 10:30
 */
@Data
@ApiModel
public class ArticleSectionRelation {
    @ApiModelProperty(notes = "主键Id", dataType = "Long", readOnly = true)
    private Long id;
    @ApiModelProperty(notes = "板块id", dataType = "Long")
    private Long sectionId;
    @ApiModelProperty(notes = "文章id", dataType = "Long")
    private Long articleId;
    @ApiModelProperty(notes = "序号", dataType = "Integer")
    private Integer sorting;
}
