package com.lhiot.dc.data.domain.enums;

import lombok.Getter;

/**
 * 可拆分状态枚举
 */
public enum SplitStatusEnum {
    SEPARABLE("可拆分"),
    NO_SEPARABLE("不可拆分");

    @Getter
    private String description;

    SplitStatusEnum(String description) {
        this.description = description;
    }
}
