package com.lhiot.dc.data.domain;

import com.lhiot.dc.data.common.PagerRequestObject;
import com.lhiot.dc.data.domain.enums.SplitStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
* Description:商品单位实体类
* @author Limiaojun
* @date 2018/06/01
*/
@Data
@ToString(callSuper = true)
@ApiModel
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductUnit extends PagerRequestObject{

    /**
    *商品单位id
    */
    @ApiModelProperty(notes = "商品单位id", dataType = "Long")
    private Long id;

    /**
    *单位名称
    */
    @ApiModelProperty(notes = "单位名称", dataType = "String")
    private String unitName;

    /**
    *是否可拆分
    */
    @ApiModelProperty(notes = "是否可拆分 SEPARABLE(\"可拆分\"),NO_SEPARABLE(\"不可拆分\");", dataType = "SplitStatusEnum")
    private SplitStatusEnum splitStatus;

}
