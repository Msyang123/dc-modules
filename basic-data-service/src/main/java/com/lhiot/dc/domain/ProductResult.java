package com.lhiot.dc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lhiot.dc.domain.type.AvailableStatus;
import com.lhiot.dc.domain.type.ProductStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author zhangfeng created in 2018/9/20 11:48
 **/
@Data
@ApiModel
public class ProductResult {

    @ApiModelProperty(notes = "规格Id",dataType = "Long")
    private Long specificationId;
    @ApiModelProperty(notes = "规格条码",dataType = "String")
    private String barcode;
    @ApiModelProperty(notes = "打包单位",dataType = "String")
    private String packagingUnit;
    @ApiModelProperty(notes = "单个规格重量",dataType = "BigDecimal")
    private BigDecimal weight;
    @ApiModelProperty(notes = "安全库存",dataType = "Long")
    private Long limitInventory;
    @ApiModelProperty(notes = "原价",dataType = "Long")
    private Long price;
    @ApiModelProperty(notes = "是否是库存规格：YES-是，NO-否",dataType = "InventorySpecification")
    private InventorySpecification inventorySpecification;
    @ApiModelProperty(notes = "规格是否可用：YES-是，NO-否",dataType = "AvailableStatus")
    private AvailableStatus availableStatus;
    @ApiModelProperty(notes = "商品Id",dataType = "Long")
    private Long productId;
    @ApiModelProperty(notes = "商品编码",dataType = "String")
    private String productCode;
    @ApiModelProperty(notes = "商品名称",dataType = "String")
    private String name;
    @ApiModelProperty(notes = "分类Id",dataType = "Long")
    private Long categoryId;
    @ApiModelProperty(notes = "产地编号",dataType = "String")
    private String sourceCode;
    @ApiModelProperty(notes = "商品描述",dataType = "String")
    private String description;
    @ApiModelProperty(notes = "商品状态",dataType = "ProductStatus")
    private ProductStatus status;

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
