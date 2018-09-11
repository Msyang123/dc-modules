package com.lhiot.dc.data.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
* 描述：商品规格表
* @author yijun
* @date 2018-07-21
*/
@Data
@ToString
@ApiModel
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductStandardResult {
    @ApiModelProperty(notes = "套餐商品关联ID", dataType = "Long")
    private String relationId;

    @ApiModelProperty(notes = "规格ID", dataType = "String")
    private String id;

    @ApiModelProperty(notes = "商品ID", dataType = "Integer")
    private Integer productId;

    @JsonProperty("applicationType")
    @ApiModelProperty(notes = "应用类型:APP-视食 WECHAT_MALL-微商城 S_MALL-小程序 FRUIT_DOCTOR-小程序", dataType = "String")
    private String applyType;

    @ApiModelProperty(notes = "商品编码", dataType = "String")
    private String productCode;

    @ApiModelProperty(notes = "商品名称", dataType = "String")
    private String productName;

    @ApiModelProperty(notes = "分类ID", dataType = "String")
    private String groupId;

    @ApiModelProperty(notes = "产地id", dataType = "String")
    private String sourceCode;

    @ApiModelProperty(notes = "商品主图", dataType = "String")
    private String image;

    @ApiModelProperty(notes = "商品小图标", dataType = "String")
    private String smallImage;

    @ApiModelProperty(notes = "大图标", dataType = "String")
    private String largeImage;

    @ApiModelProperty(notes = "商品状态", dataType = "String")
    private String status;

    @ApiModelProperty(notes = "基础单位id", dataType = "String")
    private String baseUnitId;

    @ApiModelProperty(notes = "基础条码", dataType = "String")
    private String baseBarcode;

    @ApiModelProperty(notes = "海鼎skuid", dataType = "String")
    private String hdSkuid;

    @ApiModelProperty(notes = "商品介绍视频地址", dataType = "String")
    private String videoUrl;

    @ApiModelProperty(notes = "视频遮罩图片", dataType = "String")
    private String videoImage;

    @ApiModelProperty(notes = "基础重量，比如1个0.2kg填写0.2", dataType = "BigDecimal")
    private BigDecimal baseQty;

    @ApiModelProperty(notes = "允许销售的最低库存", dataType = "Integer")
    private Integer limitQty;

    @ApiModelProperty(notes = "商品描述", dataType = "String")
    private String productDescription;

    @ApiModelProperty(notes = "商品条码(hd_skuid)", dataType = "String")
    private String barcode;

    @ApiModelProperty(notes = "商品规格", dataType = "String")
    private String specification;

    @ApiModelProperty(notes = "商品数量/重量", dataType = "BigDecimal")
    private BigDecimal standardQty;

    @ApiModelProperty(notes = "计量单位id", dataType = "Integer")
    private Integer unitId;

    @ApiModelProperty(notes = "商品单位", dataType = "String")
    private String productUnit;

    @ApiModelProperty(notes = "商品价格", dataType = "Integer")
    private Integer price;

    @ApiModelProperty(notes = "出售价格", dataType = "Integer")
    private Integer salePrice;

    @ApiModelProperty(notes = "商品排序", dataType = "Integer")
    private Integer rank;

    @ApiModelProperty(notes = "规格描述", dataType = "String")
    private String description;

    @ApiModelProperty(notes = "是否上架 1-上架  0-下架2", dataType = "String")
    private String shelvesStatus;

    @ApiModelProperty(notes = "套餐中，该规格商品的份数", dataType = "Integer")
    private Integer relationCount;
}
