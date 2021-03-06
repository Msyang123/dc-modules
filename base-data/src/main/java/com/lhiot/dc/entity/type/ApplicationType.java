package com.lhiot.dc.entity.type;

import com.leon.microx.util.StringUtils;
import lombok.Getter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 应用类型
 */
public enum ApplicationType {
    APP("视食"),
    WECHAT_MALL("微商城"),
    S_MALL("小程序"),
    FRUIT_DOCTOR("鲜果师商城"),
    WXSMALL_SHOP("微商城小程序");
    @Getter
    private String description;

    ApplicationType(String description) {
        this.description = description;
    }

    public static ApplicationType[] convert(String string) {
        String[] array = StringUtils.tokenizeToStringArray(string, ",");
        return Stream.of(array).filter(StringUtils::hasLength).map(ApplicationType::valueOf).toArray(ApplicationType[]::new);
    }

    public static String convert(ApplicationType... types) {
        return types != null ? Stream.of(types).map(ApplicationType::name).collect(Collectors.joining(",")) : null;
    }
}
