package com.lhiot.dc.data.domain.out;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@ApiModel
@NoArgsConstructor
public class SimpleProductStandard  {
    /**
     *规格ID
     */
    @ApiModelProperty(value = "规格ID", dataType = "Long")
    private Long id;

    /**
     *商品价格
     */
    @ApiModelProperty(value = "商品价格", dataType = "Integer")
    private Integer price;

    /**
     *出售价格
     */
    @ApiModelProperty(value = "出售价格", dataType = "Integer")
    private Integer salePrice;

    /**
     * 是否有库存
     * 返回增加字段
     */
    @ApiModelProperty(notes = "是否有库存", dataType = "String",hidden=true)
    private String invEnough;
    /**
     * 库存数量
     */
    @ApiModelProperty(notes = "库存", dataType = "String",hidden=true)
    private Double invNum;

}
