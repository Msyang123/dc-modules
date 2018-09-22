package com.lhiot.dc.base.model;

import com.lhiot.dc.base.model.type.ProductStatus;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.Instant;
import java.util.Date;


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
    private ProductStatus status;
    private Date createAt = Date.from(Instant.now());
}
