package com.lhiot.dc.domain;

import com.lhiot.dc.domain.type.AttachmentType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author xiaojian created in 2018/11/13 10:51
 **/
@Data
@EqualsAndHashCode(of = "url")
public class ProductAttachment {

    private Long id;
    private String url;
    private Long productId;
    private Integer sorting;
    private AttachmentType attachmentType;

}
