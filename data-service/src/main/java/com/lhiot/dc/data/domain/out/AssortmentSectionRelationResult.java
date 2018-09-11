package com.lhiot.dc.data.domain.out;

import com.lhiot.dc.data.common.PagerRequestObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(description = "套餐关联板块信息结果集")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AssortmentSectionRelationResult extends PagerRequestObject {
    @ApiModelProperty(notes = "套餐关联板块id", dataType = "Long")
    private Long id;

    @ApiModelProperty(notes = "套餐图片", dataType = "String")
    private String assortmentImage;

    @ApiModelProperty(notes = "套餐名称", dataType = "String")
    private String assortmentName;

    @ApiModelProperty(notes = "板块名称", dataType = "String")
    private String nameCn;

    @ApiModelProperty(notes = "套餐价格", dataType = "Integer")
    private Integer price;

    @ApiModelProperty(notes = "排序", dataType = "Integer")
    private Integer rankNo;

    @ApiModelProperty(notes = "板块ID", dataType = "long")
    private Integer sectionId;
}
