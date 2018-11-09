package com.lhiot.dc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.Instant;
import java.util.Date;
import java.util.List;


/**
 * @Author zhangfeng created in 2018/9/20 11:14
 **/
@Data
@ApiModel
public class Product {
    private Long id;
    private String code;
    private String name;
    private Long categoryId;
    private String sourceCode;
    private String description;
    private Date createAt = Date.from(Instant.now());
    private List<ProductAttachment> attachments;

    @JsonIgnore
    @ApiModelProperty(value = "当前页,默认值1")
    private Long page = 1L;

    /**
     * 传入-1可不分页
     */
    @JsonIgnore
    @ApiModelProperty(value = "每页显示条数,默认值10")
    private Long rows = 10L;
}
