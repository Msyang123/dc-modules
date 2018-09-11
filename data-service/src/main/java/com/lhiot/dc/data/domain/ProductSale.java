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
* Description:
* @author yijun
* @date 2018/08/09
*/
@Data
@ToString(callSuper = true)
@ApiModel
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductSale extends PagerRequestObject {

    /**
    *
    */
    @JsonProperty("id")
    @ApiModelProperty(value = "", dataType = "Integer")
    private Integer id;

    /**
    *商品规格id
    */
    @JsonProperty("productStandardId")
    @ApiModelProperty(value = "商品规格id", dataType = "Integer")
    private Integer productStandardId;

    /**
    *销售数量
    */
    @JsonProperty("saleCount")
    @ApiModelProperty(value = "销售数量", dataType = "Integer")
    private Integer saleCount;

    /**
    *应用类型
    */
    @JsonProperty("applyType")
    @ApiModelProperty(value = "应用类型", dataType = "String")
    private String applyType;

}
