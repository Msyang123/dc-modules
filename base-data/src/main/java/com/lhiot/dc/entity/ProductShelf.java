package com.lhiot.dc.entity;

import com.leon.microx.predefine.OnOff;
import com.lhiot.dc.dictionary.HasEntries;
import com.lhiot.dc.entity.type.ShelfType;
import com.lhiot.dc.util.DictionaryCodes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangfeng create in 15:38 2018/11/8
 */
@Data
@ApiModel
public class ProductShelf {
    @ApiModelProperty(notes = "规格对象", dataType = "ProductSpecification", readOnly = true)
    private ProductSpecification productSpecification;
    @ApiModelProperty(notes = "主键Id", dataType = "Long", readOnly = true)
    private Long id;
    @NotNull(message = "上架名称不能为空")
    @ApiModelProperty(notes = "上架名称", dataType = "String")
    private String name;
    @ApiModelProperty(notes = "规格ID", dataType = "Long")
    private Long specificationId;
    @ApiModelProperty(notes = "上架数量", dataType = "BigDecimal")
    private BigDecimal shelfQty;
    @ApiModelProperty(notes = "特价", dataType = "Integer")
    private Integer price;
    @ApiModelProperty(notes = "原价", dataType = "Integer")
    private Integer originalPrice;
    @ApiModelProperty(notes = "上架图片", dataType = "String")
    private String image;
    @ApiModelProperty(notes = "商品主图", dataType = "String")
    private String productImage;
    @ApiModelProperty(notes = "上架状态：ON-上架，OFF-下架", dataType = "OnOff")
    private OnOff shelfStatus;
    @ApiModelProperty(notes = "上架类型：NORMAL-普通商品,GIFT-赠品", dataType = "ShelfType")
    private ShelfType shelfType;
    @ApiModelProperty(notes = "创建时间", dataType = "Date", readOnly = true)
    private Date createAt;
    @ApiModelProperty(notes = "描述", dataType = "String")
    private String description;
    @ApiModelProperty(notes = "排序字段", dataType = "Integer")
    private Integer sorting;
    @ApiModelProperty(notes = "应用类型", dataType = "String")
    @HasEntries(from = DictionaryCodes.APPLICATION_TYPE, message = "没有找到此应用类型")
    private String applicationType;


}
