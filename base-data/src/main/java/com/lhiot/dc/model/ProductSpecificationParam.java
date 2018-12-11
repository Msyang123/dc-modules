package com.lhiot.dc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leon.microx.predefine.Use;
import com.lhiot.dc.entity.type.InventorySpecification;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * @author xiaojian  created in  2018/11/19 8:40
 */
@ApiModel
@Data
public class ProductSpecificationParam {
    @ApiModelProperty(notes = "商品ID", dataType = "Long")
    private Long productId;
    @ApiModelProperty(notes = "商品条码(多个英文逗号分隔)", dataType = "String")
    private String barCodes;
    @ApiModelProperty(notes = "打包单位", dataType = "String")
    private String packagingUnit;
    @ApiModelProperty(notes = "是否为库存规格：YES-是，NO-否", dataType = "InventorySpecification")
    private InventorySpecification inventorySpecification;
    @ApiModelProperty(notes = "是否可用：ENABLE-可用，DISABLE-不可用", dataType = "Use")
    private Use availableStatus;
    @ApiModelProperty(notes = "起始创建时间", dataType = "Date")
    private Date beginCreateAt;
    @ApiModelProperty(notes = "截止创建时间", dataType = "Date")
    private Date endCreateAt;
    @ApiModelProperty(notes = "名称或条码关键字", dataType = "String")
    private String keyword;
    @ApiModelProperty(notes = "每页查询条数(为空或0不分页查所有)", dataType = "Integer")
    private Integer rows;
    @ApiModelProperty(notes = "当前页", dataType = "Integer")
    private Integer page;

    @ApiModelProperty(hidden = true)
    private Integer startRow;
    @ApiModelProperty(hidden = true)
    private Boolean pageFlag;

    @JsonIgnore
    public Integer getStartRow() {
        if (Objects.nonNull(this.rows) && this.rows > 0) {
            return (Objects.nonNull(this.page) && this.page > 0 ? this.page - 1 : 0) * this.rows;
        }
        return null;
    }

    @JsonIgnore
    public Boolean getPageFlag() {
        return Objects.nonNull(this.page) && Objects.nonNull(this.rows) && this.page > 0 && this.rows > 0;
    }
}
