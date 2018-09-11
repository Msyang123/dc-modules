package com.lhiot.dc.data.domain.enums;

import lombok.Getter;

public enum ApplicationTypeEnum {
    APP("视食"),
    WECHAT_MALL("微商城"),
    S_MALL("拼团小程序"),
    WXSMALL_SHOP("微商城小程序"),
    FRUIT_DOCTOR("鲜果师商城");
    @Getter
    private String description;

    ApplicationTypeEnum(String description) {
        this.description = description;
    }
}
