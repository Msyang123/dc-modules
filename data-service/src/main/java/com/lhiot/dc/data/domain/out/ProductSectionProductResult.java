package com.lhiot.dc.data.domain.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lhiot.dc.data.common.PagerRequestObject;
import com.lhiot.dc.data.domain.enums.ApplyEnum;
import com.lhiot.dc.data.domain.ProductStandard;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
* Description:商品板块实体类
* @author Limiaojun
* @date 2018/07/28
*/
@Data
@ToString(callSuper = true)
@ApiModel
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductSectionProductResult extends PagerRequestObject {

    /**
    *主键
    */
    @JsonProperty("id")
    @ApiModelProperty(value = "主键", dataType = "Long")
    private Long id;

    /**
    *父id
    */
    @JsonProperty("parentId")
    @ApiModelProperty(value = "父id", dataType = "Long")
    private Long parentId;

    /**
    *商品板块id
    */
    @JsonProperty("sectionProductId")
    @ApiModelProperty(value = "商品板块id", dataType = "Long")
    private Long sectionProductId;

    /**
    *板块图片url
    */
    @JsonProperty("sectionImg")
    @ApiModelProperty(value = "板块图片url", dataType = "String")
    private String sectionImg;

    /**
    *顺序
    */
    @JsonProperty("rankNo")
    @ApiModelProperty(value = "顺序", dataType = "Integer")
    private Integer rankNo;
    /**
     *版块名称
     */
    @JsonProperty("sectionName")
    @ApiModelProperty(value = "版块名称", dataType = "String")
    private String sectionName;
    /**
    *板块位置英文描述
    */
    @JsonProperty("positionName")
    @ApiModelProperty(value = "板块位置英文描述", dataType = "String")
    private String positionName;

    /**
     *应用类型:APP-视食 WECHAT_MALL-微商城 S_MALL-小程序
     */
    @ApiModelProperty(value = "应用类型:APP-视食 WECHAT_MALL-微商城 S_MALL-小程序 WXSMALL_SHOP 微商城小程序", dataType = "ApplyEnum")
    private ApplyEnum applyType;
    /**
     *子版块
     */
    @JsonProperty("productStandardList")
    @ApiModelProperty(value = "所属商品", dataType = "list")
    private List<ProductStandard> productStandardList ;

    @JsonProperty("parentSectionName")
    @ApiModelProperty(value = "父板块名称", dataType = "String")
    private String parentSectionName;
}
