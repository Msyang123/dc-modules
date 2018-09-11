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
* Description:套餐商品关联实体类
* @author yijun
* @date 2018/07/24
*/
@Data
@ToString(callSuper = true)
@ApiModel
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductAssortmentRelation extends PagerRequestObject{

    /**
    *
    */
    @JsonProperty("id")
    @ApiModelProperty(value = "", dataType = "Long")
    private Long id;

    /**
    *套餐id
    */
    @JsonProperty("assortmentId")
    @ApiModelProperty(value = "套餐id", dataType = "Long")
    private Long assortmentId;

    /**
    *规格id
    */
    @JsonProperty("standardId")
    @ApiModelProperty(value = "规格id", dataType = "Long")
    private Long standardId;

    /**
    *关联数量
    */
    @JsonProperty("relationCount")
    @ApiModelProperty(value = "关联数量", dataType = "Double")
    private Double relationCount;

}
