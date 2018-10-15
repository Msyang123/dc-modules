package com.lhiot.dc.base.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ApiModelProperty(value = "当前页,默认值1")
    private Long page = 1L;

    /**
     * 传入-1可不分页
     */
    @JsonIgnore
    @ApiModelProperty(value = "每页显示条数,默认值10")
    private Long rows = 10L;

}
