package com.lhiot.dc.base.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
* Description:字典数据实体类
* @author yijun
* @date 2018/10/12
*/
@Data
@ToString(callSuper = true)
@ApiModel
@NoArgsConstructor
public class CategoryItem{

    /**
    *id
    */
    @JsonProperty("id")
    @ApiModelProperty(value = "id", dataType = "Long")
    private Long id;

    /**
    *字典编码
    */
    @JsonProperty("cgCode")
    @ApiModelProperty(value = "字典编码", dataType = "String")
    private String cgCode;

    /**
    *字典项名称
    */
    @JsonProperty("name")
    @ApiModelProperty(value = "字典项名称", dataType = "String")
    private String name;

    /**
    *字典项编码
    */
    @JsonProperty("code")
    @ApiModelProperty(value = "字典项编码", dataType = "String")
    private String code;

    /**
    *排序序号
    */
    @JsonProperty("sort")
    @ApiModelProperty(value = "排序序号", dataType = "Long")
    private Long sort;

    /**
    *附加参数
    */
    @JsonProperty("attache")
    @ApiModelProperty(value = "附加参数", dataType = "String")
    private String attache;

}
