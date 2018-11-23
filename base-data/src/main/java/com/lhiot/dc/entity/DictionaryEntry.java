package com.lhiot.dc.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Leon (234239150@qq.com) created in 14:57 18.10.15
 */
@Data
@ApiModel
public class DictionaryEntry {
    private Long id;
    private String dictCode;
    private String name;
    private String code;
    private Integer sort;
    private String attach;
}
