package com.lhiot.dc.model;

import com.lhiot.dc.entity.type.StoreType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangfeng create in 11:56 2018/11/9
 */
@ApiModel
@Data
public class StoreSearchParam {
    @ApiModelProperty(notes = "门店Id集合",dataType = "String")
    private String storeIds;
    private String name;
    private String code;

    private String applicationType;
    @ApiModelProperty(notes = "距离",dataType = "Double")
    private Double distance = 0D;
    @ApiModelProperty(notes = "纬度",dataType = "Double")
    private Double lat;
    @ApiModelProperty(notes = "经度",dataType = "Double")
    private Double lng;
    @ApiModelProperty(notes = "门店类型：ORDINARY_STORE(\"普通门店\"),FLAGSHIP_STORE (\"旗舰店\");", dataType = "StoreTypeEnum")
    private StoreType storeType;
    @ApiModelProperty(notes = "所属区域", dataType = "String")
    private String area;
    @ApiModelProperty(notes = "查询条数",dataType = "Integer")
    private Integer rows;
    @ApiModelProperty(notes = "当前页",dataType = "Integer")
    private Integer page;

}
