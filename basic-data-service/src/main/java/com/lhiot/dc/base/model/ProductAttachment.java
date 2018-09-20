package com.lhiot.dc.base.model;

import lombok.Data;

/**
 * @Author zhangfeng created in 2018/9/20 11:51
 **/
@Data
public class ProductAttachment {

    private String url;
    private Long productId;
    private Integer sorting;
    private AttachmentType attachmentType;
}
