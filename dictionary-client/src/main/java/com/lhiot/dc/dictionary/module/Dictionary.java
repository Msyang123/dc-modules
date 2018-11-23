package com.lhiot.dc.dictionary.module;

import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Leon (234239150@qq.com) created in 11:29 18.10.15
 */
@Data
public class Dictionary {

    private String name;
    private String code;
    private String description;

    private List<Dictionary> children;
    private List<Entry> entries;

    @Data
    public static class Entry {
        private String name;
        private String code;
        private Integer sort;
        private String attach;
    }

    /**
     * 从当前字典获取字典项 （格式为常量命名格式，即全大写 下划线分割）
     * @param code 字典项code
     * @return Optional Entry
     */
    public Optional<Entry> entry(String code) {
        if (Objects.isNull(this.entries)) {
            return Optional.empty();
        }
        return this.entries.parallelStream().filter(entry -> entry.getCode().equalsIgnoreCase(code)).findAny();
    }

    /**
     * 当前字典是否有此字典项 （格式为常量命名格式，即全大写 下划线分割）
     * @param code 字典项code
     * @return bool
     */
    public boolean hasEntry(String code) {
        return this.entry(code).isPresent();
    }
}
