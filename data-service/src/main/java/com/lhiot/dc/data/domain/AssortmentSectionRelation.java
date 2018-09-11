package com.lhiot.dc.data.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lhiot.dc.data.common.PagerRequestObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
* Description:板块套餐关联实体类
* @author yijun
* @date 2018/07/24
*/
@Data
@ToString(callSuper = true)
@ApiModel
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AssortmentSectionRelation extends PagerRequestObject {

    /**
    *id
    */
    @JsonProperty("id")
    @ApiModelProperty(value = "id", dataType = "Long")
    private Long id;

    /**
    *板块id
    */
    @JsonProperty("sectionId")
    @ApiModelProperty(value = "板块id", dataType = "Long")
    private Long sectionId;

    /**
    *套餐id
    */
    @JsonProperty("assortmentId")
    @ApiModelProperty(value = "套餐id", dataType = "Long")
    private Long assortmentId;

    /**
    *序号
    */
    @JsonProperty("rankNo")
    @ApiModelProperty(value = "序号", dataType = "Integer")
    private Integer rankNo;

}
