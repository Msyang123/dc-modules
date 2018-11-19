package com.lhiot.dc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lhiot.dc.domain.type.ApplicationType;
import com.lhiot.dc.domain.type.ShelfStatus;
import com.lhiot.dc.domain.type.ShelfType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaojian  created in  2018/11/16 11:20
 */
@ApiModel
@Data
public class ProductShelfParam {
    @ApiModelProperty(notes = "版块ID", dataType = "Long")
    private Long sectionId;
    @ApiModelProperty(notes = "上架名称", dataType = "String")
    private String name;
    @ApiModelProperty(notes = "上架状态：ON-上架，OFF-下架", dataType = "ShelfStatus")
    private ShelfStatus shelfStatus;
    @ApiModelProperty(notes = "上架类型：NORMAL-普通商品,GIFT-赠品", dataType = "ShelfType")
    private ShelfType shelfType;
    @ApiModelProperty(notes = "应用类型", dataType = "ApplicationType")
    private ApplicationType[] applicationTypes;
    @ApiModelProperty(notes = "最小特价", dataType = "Integer")
    private Integer minPrice;
    @ApiModelProperty(notes = "最大特价", dataType = "Integer")
    private Integer maxPrice;
    @ApiModelProperty(notes = "最小原价", dataType = "Integer")
    private Integer minOriginalPrice;
    @ApiModelProperty(notes = "最大原价", dataType = "Integer")
    private Integer maxOriginalPrice;
    @ApiModelProperty(notes = "起始创建时间", dataType = "Date")
    private Date beginCreateAt;
    @ApiModelProperty(notes = "截止创建时间", dataType = "Date")
    private Date endCreateAt;
    @ApiModelProperty(notes = "查询条数", dataType = "Integer")
    private Integer rows;
    @ApiModelProperty(notes = "当前页", dataType = "Integer")
    private Integer pages;

    @ApiModelProperty(hidden = true)
    private Integer startRow;


    @JsonIgnore
    public String getApplications() {
        return ApplicationType.convert(applicationTypes);
    }

    public void setApplications(String applicationTypes) {
        this.applicationTypes = ApplicationType.convert(applicationTypes);
    }

    @JsonIgnore
    public Integer getStartRow() {
        if (this.rows != null && this.rows > 0) {
            return (this.pages != null && this.pages > 0 ? this.pages - 1 : 0) * this.rows;
        }
        return null;
    }

}
