package com.lhiot.dc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaojian  created in  2018/11/17 9:59
 */
@ApiModel
@Data
public class ProductCategoryParam {
    @ApiModelProperty(notes = "父级ID", dataType = "Long")
    private Long parentId;
    @ApiModelProperty(notes = "分类名", dataType = "String")
    private String groupName;
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
    public Integer getStartRow() {
        if (this.rows != null && this.rows > 0) {
            return (this.pages != null && this.pages > 0 ? this.pages - 1 : 0) * this.rows;
        }
        return null;
    }
}