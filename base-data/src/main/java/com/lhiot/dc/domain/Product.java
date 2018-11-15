package com.lhiot.dc.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.Instant;
import java.util.Date;
import java.util.List;


/**
 * @Author xiaojian created in 2018/11/12 17:09
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

}
