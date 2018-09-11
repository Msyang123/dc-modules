package com.lhiot.dc.data.domain.out;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(description = "商品的分类信息")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductGroupResult {
    @ApiModelProperty(notes = "分类id", dataType = "Long")
    private Long id;

    @ApiModelProperty(notes = "分类节点的父id", dataType = "Long")
    private Long parentId;

    @ApiModelProperty(notes = "分类名称", dataType = "String")
    private String groupName;

    @ApiModelProperty(notes = "父节点分类名称", dataType = "String")
    private String parentGroupName;

    @ApiModelProperty(notes = "节点排序", dataType = "String")
    private String levels;
}
