package com.lhiot.dc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lhiot.dc.entity.type.ApplicationType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaojian  created in  2018/11/21 18:13
 */
@ApiModel
@Data
public class ArticleSectionParam {
    @ApiModelProperty(notes = "位置ID", dataType = "Long")
    private Long positionId;
    @ApiModelProperty(notes = "父级ID", dataType = "Long")
    private Long parentId;
    @ApiModelProperty(notes = "板块中文名称", dataType = "String")
    private String nameCn;
    @ApiModelProperty(notes = "板块英文名称", dataType = "String")
    private String nameEn;
    @ApiModelProperty(notes = "应用类型", dataType = "ApplicationType")
    private ApplicationType[] applicationTypes;
    @ApiModelProperty(notes = "起始创建时间", dataType = "Date")
    private Date beginCreateAt;
    @ApiModelProperty(notes = "截止创建时间", dataType = "Date")
    private Date endCreateAt;
    @ApiModelProperty(notes = "每页查询条数(为空或0不分页查所有)", dataType = "Integer")
    private Integer rows;
    @ApiModelProperty(notes = "当前页", dataType = "Integer")
    private Integer page;

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
            return (this.page != null && this.page > 0 ? this.page - 1 : 0) * this.rows;
        }
        return null;
    }
}