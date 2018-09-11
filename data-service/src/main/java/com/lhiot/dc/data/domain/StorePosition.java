package com.lhiot.dc.data.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lhiot.dc.data.common.PagerRequestObject;
import com.lhiot.dc.data.domain.enums.ApplicationTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
* Description:门店位置实体类
* @author yijun
* @date 2018/09/11
*/
@Data
@ToString(callSuper = true)
@ApiModel
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StorePosition extends PagerRequestObject {

    /**
    *
    */
    @JsonProperty("id")
    @ApiModelProperty(value = "", dataType = "Long")
    private Long id;

    /**
    *坐标位置（经度）
    */
    @JsonProperty("storeCoordy")
    @ApiModelProperty(value = "坐标位置（经度）", dataType = "Double")
    private Double storeCoordy;

    /**
    *坐标位置（纬度）
    */
    @JsonProperty("storeCoordx")
    @ApiModelProperty(value = "坐标位置（纬度）", dataType = "Double")
    private Double storeCoordx;

    /**
    *应用类型
    */
    @JsonProperty("applicationType")
    @ApiModelProperty(value = "应用类型", dataType = "ApplicationTypeEnum")
    private ApplicationTypeEnum applicationType;

    /**
    *门店编号
    */
    @JsonProperty("storeId")
    @ApiModelProperty(value = "门店编号", dataType = "Long")
    private Long storeId;

    private List<Long> storeIds;

}