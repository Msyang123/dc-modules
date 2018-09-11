package com.lhiot.dc.data.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lhiot.dc.data.common.PagerRequestObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
* Description:套餐板块分类实体类
* @author yijun
* @date 2018/07/24
*/
@Data
@ToString(callSuper = true)
@ApiModel
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AssortmentSection extends PagerRequestObject {

    /**
    *
    */
    @JsonProperty("id")
    @ApiModelProperty(value = "", dataType = "Long")
    private Long id;

    /**
    *板块中文名
    */
    @JsonProperty("nameCn")
    @ApiModelProperty(value = "板块中文名", dataType = "String")
    private String nameCn;

    /**
    *板块英文名
    */
    @JsonProperty("nameEn")
    @ApiModelProperty(value = "板块英文名", dataType = "String")
    private String nameEn;

    /**
    *父级编号
    */
    @JsonProperty("parentId")
    @ApiModelProperty(value = "父级编号", dataType = "Long")
    private Long parentId;

    /**
    *创建时间
    */
    @JsonProperty("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间", dataType = "Date")
    private java.util.Date createTime;
    

    /**
    *应用类型
    */
    @JsonProperty("applicationType")
    @ApiModelProperty(value = "应用类型", dataType = "String")
    private String applicationType;

    /**
    *序号
    */
    @JsonProperty("rankNo")
    @ApiModelProperty(value = "序号", dataType = "Integer")
    private Integer rankNo;

    /**
    *banner
    */
    @JsonProperty("banner")
    @ApiModelProperty(value = "banner", dataType = "String")
    private String banner;

    /**
     *板块副标题
     */
    @JsonProperty("subtitle")
    @ApiModelProperty(value = "subtitle", dataType = "String")
    private String subtitle;

    /**
     *套餐板块中的套餐
     */
    @JsonProperty("assortments")
    @ApiModelProperty(value = "assortments", dataType = "List")
    private List<Assortment> assortments;

}
