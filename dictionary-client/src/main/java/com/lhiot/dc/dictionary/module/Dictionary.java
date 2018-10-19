package com.lhiot.dc.dictionary.module;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Objects;

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

    @Nullable
    public Entry entry(String code) {
        if (Objects.isNull(this.entries)){
            return null;
        }
        return this.entries.parallelStream().filter(entry -> Objects.equals(entry.getCode(), code)).findAny().orElse(null);
    }
}
