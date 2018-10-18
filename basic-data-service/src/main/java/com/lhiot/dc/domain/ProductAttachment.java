package com.lhiot.dc.domain;

import com.lhiot.dc.domain.type.AttachmentType;
import lombok.Data;

/**
 * @Author zhangfeng created in 2018/9/20 11:51
 **/
@Data
public class ProductAttachment {

    private Long id;
    private String url;
    private Long productId;
    private Integer sorting;
    private AttachmentType attachmentType;
}
