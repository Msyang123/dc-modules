package com.lhiot.dc.data.domain;

import com.lhiot.dc.data.common.PagerRequestObject;
import com.lhiot.dc.data.domain.enums.ApplyEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
* Description:商品分类实体类
* @author Limiaojun
* @date 2018/06/01
*/
@Data
@ToString(callSuper = true)
@ApiModel
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductGroup extends PagerRequestObject {

    /**
    *商品分类ID
    */
    @ApiModelProperty(notes = "商品分类ID", dataType = "Long")
    private Long id;

    /**
    *父级ID
    */
    @ApiModelProperty(notes = "父级ID", dataType = "Long")
    private Long parentId;

    /**
    *分类名
    */
    @ApiModelProperty(notes = "分类名", dataType = "String")
    private String groupName;

    /**
    *应用类型:APP-视食 WECHAT_MALL-微商城 S_MALL-小程序
    */
    @ApiModelProperty(notes = "应用类型:APP-视食 WECHAT_MALL-微商城 S_MALL-小程序", dataType = "ApplyEnum")
    private ApplyEnum applyType;

    /**
    *排序字段
    */
    @ApiModelProperty(notes = "排序字段", dataType = "Integer")
    private Integer rank;

}
