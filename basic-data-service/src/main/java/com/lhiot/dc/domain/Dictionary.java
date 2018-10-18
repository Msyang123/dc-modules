package com.lhiot.dc.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author Leon (234239150@qq.com) created in 11:29 18.10.15
 */
@Data
@ApiModel
public class Dictionary {
    private Long id;
    private Long parentId;
    private String name;
    private String code;
    private String description;

    private List<Dictionary> children;

}
