package com.lhiot.dc.domain;

import com.lhiot.dc.domain.type.ApplicationType;
import lombok.Data;

/**
 * @author zhangfeng create in 11:56 2018/11/9
 */
@Data
public class StoreSearchParam {
    private String storeIds;
    private Double lat;
    private Double lng;
    private ApplicationType applicationType;
    private Integer rows;

}
