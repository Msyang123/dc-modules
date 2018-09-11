package com.lhiot.dc.data.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lhiot.dc.data.common.PagerRequestObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
* Description:系统设置分类实体类
* @author yijun
* @date 2018/07/24
*/
@Data
@ToString(callSuper = true)
@ApiModel
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SystemSettingCategory extends PagerRequestObject{

    /**
    *id
    */
    @JsonProperty("id")
    @ApiModelProperty(value = "id", dataType = "Long")
    private Long id;

    /**
    *父类id
    */
    @JsonProperty("parentId")
    @ApiModelProperty(value = "父类id", dataType = "Long")
    private Long parentId;

    /**
    *分类名称
    */
    @JsonProperty("categoriesName")
    @ApiModelProperty(value = "分类名称", dataType = "String")
    private String categoriesName;

}
