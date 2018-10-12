package com.lhiot.dc.base.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
* Description:字典实体类
* @author yijun
* @date 2018/10/12
*/
@Data
@ToString(callSuper = true)
@ApiModel
@NoArgsConstructor
public class Category{

    /**
    *id
    */
    @JsonProperty("id")
    @ApiModelProperty(value = "id", dataType = "Long")
    private Long id;

    /**
    *字典名称
    */
    @JsonProperty("name")
    @ApiModelProperty(value = "字典名称", dataType = "String")
    private String name;

    /**
    *字典编码
    */
    @JsonProperty("code")
    @ApiModelProperty(value = "字典编码", dataType = "String")
    private String code;

    /**
    *父级id
    */
    @JsonProperty("pid")
    @ApiModelProperty(value = "父级id", dataType = "Long")
    private Long pid;

    /**
    *描述
    */
    @JsonProperty("desc")
    @ApiModelProperty(value = "描述", dataType = "String")
    private String desc;

    /**
     *字典项
     */
    @JsonProperty("categoryItemList")
    @ApiModelProperty(value = "字典项", dataType = "List")
    private List<CategoryItem> categoryItemList;

}
