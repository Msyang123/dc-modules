package com.lhiot.dc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Objects;

/**
 * @author Leon (234239150@qq.com) created in 15:58 18.10.15
 */
@Data
@ApiModel
public class SearchParameter {

    private boolean includeChildren;

    private String dictionaryCode;

    private String entryCode;

    private Integer page = 1;

    private Integer rows = 10;

    @JsonIgnore
    public Integer getStartRow() {
        if (Objects.nonNull(this.rows) && this.rows > 0) {
            return (Objects.nonNull(this.page) && this.page > 0 ? this.page - 1 : 0) * this.rows;
        }
        return null;
    }
}
