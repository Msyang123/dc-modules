package com.lhiot.dc.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lhiot.dc.data.common.PagerRequestObject;
import com.lhiot.dc.data.domain.enums.ProductStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
* Description:商品信息实体类
* @author Limiaojun
* @date 2018/06/01
*/
@Data
@ToString(callSuper = true)
@ApiModel
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends PagerRequestObject{

    /**
    *商品ID
    */
    @ApiModelProperty(notes = "商品ID", dataType = "Long")
    private Long id;

    /**
    *商品编码
    */
    @ApiModelProperty(notes = "商品编码", dataType = "String")
    private String productCode;

    /**
    *商品名称
    */
    @ApiModelProperty(notes = "商品名称", dataType = "String")
    private String productName;

    /**
    *分类ID
    */
    @ApiModelProperty(notes = "分类ID", dataType = "Long")
    private Long groupId;

    /**
    *产地编码
    */
    @ApiModelProperty(notes = "产地编码", dataType = "String")
    private String sourceCode;

    /**
    *商品主图
    */
    @ApiModelProperty(notes = "商品主图", dataType = "String")
    private String image;

    /**
    *商品小图标
    */
    @ApiModelProperty(notes = "商品小图标", dataType = "String")
    private String smallImage;

    /**
    *大图标
    */
    @ApiModelProperty(notes = "大图标", dataType = "String")
    private String largeImage;

    /**
    *商品状态
    */
    @ApiModelProperty(notes = "商品状态 NORMAL(\"正常\"),STOP_MINING(\"停采\");", dataType = "ProductStatus")
    private ProductStatusEnum status;

    /**
    *基础单位id
    */
    @ApiModelProperty(notes = "基础单位id", dataType = "Long")
    private Long unitId;

    /**
    *基础条码
    */
    @ApiModelProperty(notes = "基础条码", dataType = "String")
    private String baseBarcode;

    /**
    *海鼎skuid
    */
    @ApiModelProperty(notes = "海鼎skuid", dataType = "String")
    private String hdSkuid;

    /**
    *商品介绍视频地址
    */
    @ApiModelProperty(notes = "商品介绍视频地址", dataType = "String")
    private String videoUrl;

    /**
    *视频遮罩图片
    */
    @ApiModelProperty(notes = "视频遮罩图片", dataType = "String")
    private String videoImage;

    /**
    *基础重量，比如1个0.2kg填写0.2
    */
    @ApiModelProperty(notes = "基础重量，比如1个0.2kg填写0.2", dataType = "Double")
    private Double baseQty;

    /**
    *允许销售的最低库存
    */
    @ApiModelProperty(notes = "允许销售的最低库存", dataType = "Integer")
    private Integer limitQty;

    /**
    *商品描述
    */
    @ApiModelProperty(notes = "商品描述", dataType = "String")
    private String description;


    /**
     * 商品基础单位
     * 返回增加字段
     */
    @ApiModelProperty(notes = "商品基础单位", dataType = "String",hidden=true)
    private String unitName;

    /**
     * 商品类别
     * 返回增加字段
     */
    @ApiModelProperty(notes = "商品类别", dataType = "String",hidden=true)
    private String groupName;

    /**
     * 规格ids集合 条件查询参数,不返回
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private List<String> ids;

}
