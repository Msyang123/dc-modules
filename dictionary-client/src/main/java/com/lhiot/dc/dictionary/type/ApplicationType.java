package com.lhiot.dc.dictionary.type;

import lombok.Getter;

/**
 * 应用类型
 */
public enum ApplicationType {
    APP("视食"),
    WECHAT_MALL("微商城"),
    WECHAT_SMALL_SHOP("小程序"),
    HEALTH_GOOD("鲜果师商城");
    @Getter
    private String description;

    ApplicationType(String description) {
        this.description = description;
    }
}
